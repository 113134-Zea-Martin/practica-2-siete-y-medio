package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.match.MatchResponseDTO;
import ar.edu.utn.frc.tup.lciii.dtos.match.NewMatchRequestDTO;
import ar.edu.utn.frc.tup.lciii.dtos.round.NewMatchRoundRequestDTO;
import ar.edu.utn.frc.tup.lciii.dtos.round.RoundPlayDTO;
import ar.edu.utn.frc.tup.lciii.dtos.round.RoundResponseDTO;
import ar.edu.utn.frc.tup.lciii.models.Match;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MatchService {

    List<MatchResponseDTO> getMatchesByPlayer(Long playerId);

    MatchResponseDTO createMatch(NewMatchRequestDTO newMatchRequestDTO);

    Match getMatchById(Long id);

    MatchResponseDTO getMatchResponseDTOById(Long id);

    RoundResponseDTO createRoundMatch(Long matchId, NewMatchRoundRequestDTO newMatchRoundRequestDTO);

    RoundResponseDTO playRoundMatch(Long matchId, Long roundId, RoundPlayDTO roundPlayDTO);

    Optional<Match> getPlayingMatch(Long playerId);

    MatchResponseDTO finishMatch(Long id);
}
