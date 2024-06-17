package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.models.Round;
import ar.edu.utn.frc.tup.lciii.models.RoundWinner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoundService {

    void forceEndOfRound(Long id);

    Round getRoundById(Long id);

    void endRound(Long id, RoundWinner roundWinner);

    List<Round> getUnfinishedRounds(Long matchId);

    Round saveRound(Round round);
}
