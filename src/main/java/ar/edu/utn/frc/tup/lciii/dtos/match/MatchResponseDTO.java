package ar.edu.utn.frc.tup.lciii.dtos.match;

import ar.edu.utn.frc.tup.lciii.dtos.player.PlayerResponseDTO;
import ar.edu.utn.frc.tup.lciii.dtos.round.RoundResponseDTO;
import ar.edu.utn.frc.tup.lciii.models.Card;
import ar.edu.utn.frc.tup.lciii.models.MatchStatus;
import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.models.Round;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchResponseDTO {

    private Long id;
    private List<RoundResponseDTO> rounds;
    private PlayerResponseDTO player;
    private MatchStatus matchStatus;
}
