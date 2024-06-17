package ar.edu.utn.frc.tup.lciii.dtos.round;


import ar.edu.utn.frc.tup.lciii.models.*;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoundResponseDTO {

    private Long id;

    @JsonProperty("match_id")
    private Long matchId;

    @JsonProperty("player_cards")
    private List<Card> playerCards;

    @JsonProperty("player_hand_status")
    private RoundHandStatus roundHandStatusPlayer;

    @JsonProperty("app_cards")
    private List<Card> appCards;

    @JsonProperty("app_hand_status")
    private RoundHandStatus roundHandStatusApp;

    @JsonProperty("chips_in_play")
    private BigDecimal chipsInPlay;

    @JsonProperty("winner")
    private RoundWinner winner;
}
