package ar.edu.utn.frc.tup.lciii.helpers;

import ar.edu.utn.frc.tup.lciii.entities.MatchEntity;
import ar.edu.utn.frc.tup.lciii.entities.RoundEntity;
import ar.edu.utn.frc.tup.lciii.models.Round;
import ar.edu.utn.frc.tup.lciii.models.RoundHandStatus;
import ar.edu.utn.frc.tup.lciii.models.RoundWinner;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Arrays;

public class RoundHelper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Round getRoundAppWins() {
        Round round = new Round();
        round.setId(1L);
        round.setDeck(DeckHelper.getDeck());
        round.setAppCards(Arrays.asList(DeckHelper.OROS_1, DeckHelper.ESPADAS_5, DeckHelper.COPAS_10));
        round.setRoundHandStatusApp(RoundHandStatus.IN_GAME);
        round.setPlayerCards(Arrays.asList(DeckHelper.ESPADAS_2, DeckHelper.BASTOS_3));
        round.setRoundHandStatusPlayer(RoundHandStatus.IN_GAME);
        round.setChipsInPlay(BigDecimal.valueOf(20));
        round.setMatchId(1L);
        round.setDeckIndexPosition(4);
        round.setWinner(RoundWinner.APP);
        return round;
    }

    public static RoundEntity getRoundEntity(MatchEntity matchEntity) {
        RoundEntity round = new RoundEntity();
        round.setDeck(DeckHelper.getDeck());
        round.setAppCards(Arrays.asList(DeckHelper.OROS_1, DeckHelper.ESPADAS_5, DeckHelper.COPAS_10));
        round.setRoundHandStatusApp(RoundHandStatus.IN_GAME);
        round.setPlayerCards(Arrays.asList(DeckHelper.ESPADAS_2, DeckHelper.BASTOS_3));
        round.setRoundHandStatusPlayer(RoundHandStatus.IN_GAME);
        round.setChipsInPlay(BigDecimal.valueOf(20));
        round.setMatch(matchEntity);
        round.setDeckIndexPosition(4);
        round.setWinner(RoundWinner.APP);
        return round;
    }
}
