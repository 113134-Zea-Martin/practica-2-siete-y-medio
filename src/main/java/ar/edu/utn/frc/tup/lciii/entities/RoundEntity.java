package ar.edu.utn.frc.tup.lciii.entities;

import ar.edu.utn.frc.tup.lciii.models.Card;
import ar.edu.utn.frc.tup.lciii.models.Deck;
import ar.edu.utn.frc.tup.lciii.models.RoundHandStatus;
import ar.edu.utn.frc.tup.lciii.models.RoundWinner;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "rounds")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private MatchEntity match;

    @Lob
    @Convert(converter = DeckConverter.class)
    @Column
    private Deck deck;

    @Column
    private Integer deckIndexPosition;

    @Lob
    @Convert(converter = CardsConverter.class)
    @Column
    private List<Card> playerCards;

    @Column
    private BigDecimal playerCardsValue;

    @Column
    @Enumerated(EnumType.STRING)
    private RoundHandStatus roundHandStatusPlayer;

    @Lob
    @Convert(converter = CardsConverter.class)
    @Column
    private List<Card> appCards;

    @Column
    private BigDecimal appCardsValue;

    @Column
    @Enumerated(EnumType.STRING)
    private RoundHandStatus roundHandStatusApp;

    @Column
    private BigDecimal chipsInPlay;

    @Column
    @Enumerated(EnumType.STRING)
    private RoundWinner winner;

}
