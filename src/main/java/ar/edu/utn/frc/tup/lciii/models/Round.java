package ar.edu.utn.frc.tup.lciii.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Round {

    private Long id;
    private Long matchId;
    private Deck deck;
    private Integer deckIndexPosition;
    private List<Card> playerCards;
    private BigDecimal playerCardsValue;
    private RoundHandStatus roundHandStatusPlayer;
    private List<Card> appCards;
    private BigDecimal appCardsValue;
    private RoundHandStatus roundHandStatusApp;
    private BigDecimal chipsInPlay;
    private RoundWinner winner;
}
