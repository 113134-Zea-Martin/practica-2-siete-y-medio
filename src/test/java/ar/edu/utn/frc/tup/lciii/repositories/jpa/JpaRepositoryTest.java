package ar.edu.utn.frc.tup.lciii.repositories.jpa;

import ar.edu.utn.frc.tup.lciii.entities.MatchEntity;
import ar.edu.utn.frc.tup.lciii.entities.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.entities.RoundEntity;
import ar.edu.utn.frc.tup.lciii.helpers.DeckHelper;
import ar.edu.utn.frc.tup.lciii.helpers.MatchHelper;
import ar.edu.utn.frc.tup.lciii.helpers.PlayerHelper;
import ar.edu.utn.frc.tup.lciii.helpers.RoundHelper;
import ar.edu.utn.frc.tup.lciii.models.MatchStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class JpaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RoundJpaRepository roundJpaRepository;

    @Autowired
    private PlayerJpaRepository playerJpaRepository;

    @Autowired
    private MatchJpaRepository matchJpaRepository;

    @BeforeEach
    public void setUp() {
        PlayerEntity playerEntity = PlayerHelper.getPlayerEntity(PlayerHelper.EMAIL_OK, PlayerHelper.BALANCE_INIT);
        playerEntity = entityManager.persist(playerEntity);
        MatchEntity matchEntity = MatchHelper.getMatchEntity(playerEntity, MatchStatus.PLAYING, MatchHelper.ROUNDS_ENTITY_EMPTY);
        matchEntity = entityManager.persist(matchEntity);
        RoundEntity roundEntity = RoundHelper.getRoundEntity(matchEntity);
        entityManager.persist(roundEntity);
        entityManager.flush();
    }

    @Test
    public void getPlayer() {
        PlayerEntity playerEntity = playerJpaRepository.getReferenceById(1L);
        assertEquals(PlayerHelper.EMAIL_OK, playerEntity.getEmail());
    }

    @Test
    public void getMatch() {
        MatchEntity matchEntity = matchJpaRepository.getReferenceById(2L);
        assertEquals(PlayerHelper.EMAIL_OK, matchEntity.getPlayer().getEmail());
    }

    @Test
    public void getRound() {
        RoundEntity roundEntity = roundJpaRepository.getReferenceById(3L);
        assertEquals(PlayerHelper.EMAIL_OK, roundEntity.getMatch().getPlayer().getEmail());
        assertEquals(Arrays.asList(DeckHelper.ESPADAS_2, DeckHelper.BASTOS_3), roundEntity.getPlayerCards());
        assertEquals(Arrays.asList(DeckHelper.OROS_1, DeckHelper.ESPADAS_5, DeckHelper.COPAS_10), roundEntity.getAppCards());
        assertEquals(DeckHelper.getDeck(), roundEntity.getDeck());
    }
}