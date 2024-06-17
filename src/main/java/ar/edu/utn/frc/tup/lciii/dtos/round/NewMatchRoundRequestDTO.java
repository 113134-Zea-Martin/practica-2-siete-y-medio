package ar.edu.utn.frc.tup.lciii.dtos.round;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewMatchRoundRequestDTO {

    @NotNull
    @JsonProperty("player_id")
    private Long playerId;
}
