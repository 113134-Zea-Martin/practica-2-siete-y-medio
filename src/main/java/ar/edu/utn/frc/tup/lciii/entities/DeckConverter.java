package ar.edu.utn.frc.tup.lciii.entities;

import ar.edu.utn.frc.tup.lciii.models.Card;
import ar.edu.utn.frc.tup.lciii.models.CardSuit;
import ar.edu.utn.frc.tup.lciii.models.Deck;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

@Converter
public class DeckConverter implements AttributeConverter<Deck, String> {

    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(Deck attribute) {
        if(attribute != null) {
            return attribute.getCards().stream().map(Card::toString).collect(Collectors.joining(SPLIT_CHAR));
        } else {
            return "";
        }
    }

    @Override
    public Deck convertToEntityAttribute(String dbData) {
        return new Deck(Arrays.stream(dbData.split(SPLIT_CHAR)).map(s -> {
            String [] cardMap = s.split("_");
            return new Card(
                    CardSuit.valueOf(cardMap[0]),
                    Integer.valueOf(cardMap[1]),
                    new BigDecimal(cardMap[2]));
        }).collect(Collectors.toList()));
    }
}
