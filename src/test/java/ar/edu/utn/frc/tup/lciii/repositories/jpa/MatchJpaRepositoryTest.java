package ar.edu.utn.frc.tup.lciii.repositories.jpa;


import ar.edu.utn.frc.tup.lciii.entities.MatchEntity;
import ar.edu.utn.frc.tup.lciii.helpers.MatchHelper;
import ar.edu.utn.frc.tup.lciii.helpers.PlayerHelper;
import ar.edu.utn.frc.tup.lciii.models.Match;
import ar.edu.utn.frc.tup.lciii.models.MatchStatus;
import ar.edu.utn.frc.tup.lciii.models.Player;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class MatchJpaRepositoryTest {

    MatchJpaRepository matchJpaRepository;

    @Test
    void getAllByPlayerIdAndMatchStatusTest() {
        // TODO: Completar el Test para validar que el metodo
        //  getAllByPlayerIdAndMatchStatus(Long playerId, MatchStatus matchStatus)
        //  de la clase MatchJpaRepository funciona.
        Player player = PlayerHelper.getPlayer(PlayerHelper.EMAIL_OK, PlayerHelper.BALANCE_INIT);
        Match match = MatchHelper.getMatch(player, MatchStatus.PLAYING, MatchHelper.ROUNDS_EMPTY);
        Optional<List<MatchEntity>> optionalMatchList = matchJpaRepository.getAllByPlayerIdAndMatchStatus(1L, MatchStatus.PLAYING);
        for (MatchEntity matchEntity : optionalMatchList.get()) {
            assertEquals(matchEntity.getPlayer().getId(), matchEntity.getPlayer().getId());
        }

        fail();
    }
}