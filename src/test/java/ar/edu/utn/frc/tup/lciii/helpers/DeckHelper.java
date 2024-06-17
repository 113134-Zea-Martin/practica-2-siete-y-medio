package ar.edu.utn.frc.tup.lciii.helpers;

import ar.edu.utn.frc.tup.lciii.models.Card;
import ar.edu.utn.frc.tup.lciii.models.CardSuit;
import ar.edu.utn.frc.tup.lciii.models.Deck;

import java.math.BigDecimal;
import java.util.ArrayList;

public class DeckHelper {

    public static Card OROS_1 = new Card(CardSuit.OROS, 1, BigDecimal.valueOf(1));
    public static Card OROS_2 = new Card(CardSuit.OROS, 2, BigDecimal.valueOf(2));
    public static Card OROS_3 = new Card(CardSuit.OROS, 3, BigDecimal.valueOf(3));
    public static Card OROS_4 = new Card(CardSuit.OROS, 4, BigDecimal.valueOf(4));
    public static Card OROS_5 = new Card(CardSuit.OROS, 5, BigDecimal.valueOf(5));
    public static Card OROS_6 = new Card(CardSuit.OROS, 6, BigDecimal.valueOf(6));
    public static Card OROS_7 = new Card(CardSuit.OROS, 7, BigDecimal.valueOf(7));
    public static Card OROS_10 = new Card(CardSuit.OROS, 10, BigDecimal.valueOf(0.5));
    public static Card OROS_11 = new Card(CardSuit.OROS, 11, BigDecimal.valueOf(0.5));
    public static Card OROS_12 = new Card(CardSuit.OROS, 12, BigDecimal.valueOf(0.5));
    public static Card ESPADAS_1 = new Card(CardSuit.ESPADAS, 1, BigDecimal.valueOf(1));
    public static Card ESPADAS_2 = new Card(CardSuit.ESPADAS, 2, BigDecimal.valueOf(2));
    public static Card ESPADAS_3 = new Card(CardSuit.ESPADAS, 3, BigDecimal.valueOf(3));
    public static Card ESPADAS_4 = new Card(CardSuit.ESPADAS, 4, BigDecimal.valueOf(4));
    public static Card ESPADAS_5 = new Card(CardSuit.ESPADAS, 5, BigDecimal.valueOf(5));
    public static Card ESPADAS_6 = new Card(CardSuit.ESPADAS, 6, BigDecimal.valueOf(6));
    public static Card ESPADAS_7 = new Card(CardSuit.ESPADAS, 7, BigDecimal.valueOf(7));
    public static Card ESPADAS_10 = new Card(CardSuit.ESPADAS, 10, BigDecimal.valueOf(0.5));
    public static Card ESPADAS_11 = new Card(CardSuit.ESPADAS, 11, BigDecimal.valueOf(0.5));
    public static Card ESPADAS_12 = new Card(CardSuit.ESPADAS, 12, BigDecimal.valueOf(0.5));
    public static Card COPAS_1 = new Card(CardSuit.COPAS, 1, BigDecimal.valueOf(1));
    public static Card COPAS_2 = new Card(CardSuit.COPAS, 2, BigDecimal.valueOf(2));
    public static Card COPAS_3 = new Card(CardSuit.COPAS, 3, BigDecimal.valueOf(3));
    public static Card COPAS_4 = new Card(CardSuit.COPAS, 4, BigDecimal.valueOf(4));
    public static Card COPAS_5 = new Card(CardSuit.COPAS, 5, BigDecimal.valueOf(5));
    public static Card COPAS_6 = new Card(CardSuit.COPAS, 6, BigDecimal.valueOf(6));
    public static Card COPAS_7 = new Card(CardSuit.COPAS, 7, BigDecimal.valueOf(7));
    public static Card COPAS_10 = new Card(CardSuit.COPAS, 10, BigDecimal.valueOf(0.5));
    public static Card COPAS_11 = new Card(CardSuit.COPAS, 11, BigDecimal.valueOf(0.5));
    public static Card COPAS_12 = new Card(CardSuit.COPAS, 12, BigDecimal.valueOf(0.5));
    public static Card BASTOS_1 = new Card(CardSuit.BASTOS, 1, BigDecimal.valueOf(1));
    public static Card BASTOS_2 = new Card(CardSuit.BASTOS, 2, BigDecimal.valueOf(2));
    public static Card BASTOS_3 = new Card(CardSuit.BASTOS, 3, BigDecimal.valueOf(3));
    public static Card BASTOS_4 = new Card(CardSuit.BASTOS, 4, BigDecimal.valueOf(4));
    public static Card BASTOS_5 = new Card(CardSuit.BASTOS, 5, BigDecimal.valueOf(5));
    public static Card BASTOS_6 = new Card(CardSuit.BASTOS, 6, BigDecimal.valueOf(6));
    public static Card BASTOS_7 = new Card(CardSuit.BASTOS, 7, BigDecimal.valueOf(7));
    public static Card BASTOS_10 = new Card(CardSuit.BASTOS, 10, BigDecimal.valueOf(0.5));
    public static Card BASTOS_11 = new Card(CardSuit.BASTOS, 11, BigDecimal.valueOf(0.5));
    public static Card BASTOS_12 = new Card(CardSuit.BASTOS, 12, BigDecimal.valueOf(0.5));

    public static Deck getDeck() {
        Deck deck = new Deck(new ArrayList<>());
        for(CardSuit cardSuit :CardSuit.values()) {
            for(int i = 1; i < 13; i++) {
                if(i == 8 || i == 9) {
                    break;
                } else if(i > 9) {
                    deck.getCards().add(new Card(cardSuit, i, BigDecimal.valueOf(0.5)));
                } else {
                    deck.getCards().add(new Card(cardSuit, i, BigDecimal.valueOf(i)));
                }
            }
        }
        return deck;
    }

}
