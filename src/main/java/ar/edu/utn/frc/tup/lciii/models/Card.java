package ar.edu.utn.frc.tup.lciii.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @JsonProperty("suit")
    private CardSuit cardSuit;

    private Integer number;
    private BigDecimal value;

    @Override
    public String toString() {
        return cardSuit.toString() + "_" + number + "_" + value;
    }
}
