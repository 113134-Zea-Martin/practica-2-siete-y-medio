package ar.edu.utn.frc.tup.lciii.entities;

import ar.edu.utn.frc.tup.lciii.models.Card;
import ar.edu.utn.frc.tup.lciii.models.CardSuit;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class CardsConverter implements AttributeConverter<List<Card>, String> {

    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(List<Card> attribute) {
        if(attribute != null) {
            return attribute.stream().map(Card::toString).collect(Collectors.joining(SPLIT_CHAR));
        } else {
            return "";
        }
    }

    @Override
    public List<Card> convertToEntityAttribute(String dbData) {
        return Arrays.stream(dbData.split(SPLIT_CHAR)).map(s -> {
            String [] cardMap = s.split("_");
            return new Card(
                    CardSuit.valueOf(cardMap[0]),
                    Integer.valueOf(cardMap[1]),
                    new BigDecimal(cardMap[2]));
        }).collect(Collectors.toList());
    }
}
