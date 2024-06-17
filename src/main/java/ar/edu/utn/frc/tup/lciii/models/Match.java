package ar.edu.utn.frc.tup.lciii.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    private Long id;
    private List<Round> rounds;
    private Player player;
    private MatchStatus matchStatus;
}
