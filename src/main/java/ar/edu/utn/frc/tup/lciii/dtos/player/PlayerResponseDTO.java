package ar.edu.utn.frc.tup.lciii.dtos.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResponseDTO {

    private Long id;

    @JsonProperty("user_name")
    private String userName;

    private String avatar;
    private BigDecimal balance;
}
