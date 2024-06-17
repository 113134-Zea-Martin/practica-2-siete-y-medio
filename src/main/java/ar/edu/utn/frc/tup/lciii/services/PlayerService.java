package ar.edu.utn.frc.tup.lciii.services;


import ar.edu.utn.frc.tup.lciii.dtos.player.NewPlayerRequestDTO;
import ar.edu.utn.frc.tup.lciii.dtos.player.PlayerResponseDTO;
import ar.edu.utn.frc.tup.lciii.models.Player;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface PlayerService {

    PlayerResponseDTO getPlayerResponseDTOById(Long id);

    Player getPlayerById(Long id);

    Player updatePlayerBalance(Long playerId, BigDecimal balanceChipsImpact);

    PlayerResponseDTO createNewPlayer(NewPlayerRequestDTO newPlayerRequestDTO) throws IllegalAccessException;
}
