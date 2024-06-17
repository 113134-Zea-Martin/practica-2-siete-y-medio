package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.dtos.player.NewPlayerRequestDTO;
import ar.edu.utn.frc.tup.lciii.dtos.player.PlayerResponseDTO;
import ar.edu.utn.frc.tup.lciii.entities.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.PlayerJpaRepository;
import ar.edu.utn.frc.tup.lciii.services.PlayerService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private static final BigDecimal INITIAL_BALANCE = BigDecimal.valueOf(1000);
    private static final String DEFAULT_AVATAR = "/avatars/default/default_1.png";

    @Autowired
    private PlayerJpaRepository playerJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Player getPlayerById(Long id) {
        PlayerEntity playerEntity = playerJpaRepository.getReferenceById(id);
        if (Objects.isNull(playerEntity.getUserName())) {
            throw new EntityNotFoundException(String.format("The player id %s do not exist", id));
        }
        return modelMapper.map(playerEntity, Player.class);
    }

    @Override
    public PlayerResponseDTO getPlayerResponseDTOById(Long id) {
        PlayerEntity playerEntity = playerJpaRepository.getReferenceById(id);
        if (Objects.isNull(playerEntity.getUserName())) {
            throw new EntityNotFoundException(String.format("The player id %s do not exist", id));
        }
        return modelMapper.map(playerEntity, PlayerResponseDTO.class);
    }

    @Override
    public Player updatePlayerBalance(Long playerId, BigDecimal balanceChipsImpact) {
        // TODO: Implementar el método de manera tal que impacte en el usuario el balancImpact pasado por parametro.DONE
        //  Como resultado del guardado debe retornar el usuario nuevamente con el balance actualizado.
        //  Es decir que...->  nuevoBalance = actualBalance + balanceChipsImpact
        PlayerEntity playerEntity = playerJpaRepository.getReferenceById(playerId);
        playerEntity.setBalanceChips(playerEntity.getBalanceChips().add(balanceChipsImpact));
        playerJpaRepository.save(playerEntity);

        return modelMapper.map(playerEntity, Player.class);
    }

    @Override
    public PlayerResponseDTO createNewPlayer(NewPlayerRequestDTO newPlayerRequestDTO) throws IllegalAccessException {
        // TODO: Implementar el método de manera tal que cree un nuevo usuario con los datos recibidos por parametro
        //  y asigne por unica vez 1000 fichas de regalo en el balance. El metodo debe validar que no exista
        //  otro usuario con el mismo user_name o email; si existe, debe retornar una exepcion del
        //  tipo IllegalArgumentException con el mensaje "The user_name or email already exists"
        //  Ayuda: Usar el metodo userAvailable()
        if (userAvailable(newPlayerRequestDTO.getEmail(), newPlayerRequestDTO.getUserName())) {
            PlayerEntity playerEntity = new PlayerEntity();
            // TODO: Completar aquí
            playerEntity.setBalanceChips(INITIAL_BALANCE);
            return modelMapper.map(playerEntity, PlayerResponseDTO.class);
        } else {
            throw new IllegalArgumentException("The user_name or email already exists");
        }
    }

    private Boolean userAvailable(String email, String userName) {
        // TODO: Implementar el método de manera tal que valide contra la base de datos que no exista un jugador
        //  con el email o el userName recibidos por paarmetros.DONE
        Optional<PlayerEntity> playerEntityFinded = playerJpaRepository.findByUserNameOrEmail(userName, email);
        if (playerEntityFinded.isPresent()) {
            return false;
        }
        return true;
    }
}
