package ar.edu.utn.frc.tup.lciii.helpers;

import ar.edu.utn.frc.tup.lciii.dtos.match.MatchResponseDTO;
import ar.edu.utn.frc.tup.lciii.entities.MatchEntity;
import ar.edu.utn.frc.tup.lciii.entities.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.entities.RoundEntity;
import ar.edu.utn.frc.tup.lciii.models.Match;
import ar.edu.utn.frc.tup.lciii.models.MatchStatus;
import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.models.Round;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatchHelper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static final List<Round> ROUNDS_EMPTY = new ArrayList<>();
    public static final List<Round> ROUNDS = Arrays.asList(
            new Round(), new Round()
    );

    public static final List<RoundEntity> ROUNDS_ENTITY_EMPTY = new ArrayList<>();
    public static final List<RoundEntity> ROUNDS_ENTITY = Arrays.asList(
            new RoundEntity(), new RoundEntity()
    );

    public static Match getMatch(Player player, MatchStatus matchStatus, List<Round> rounds) {
        Match match = new Match();
        match.setId(1L);
        match.setMatchStatus(matchStatus);
        match.setPlayer(player);
        match.setRounds(rounds);
        return match;
    }

    public static MatchEntity getMatchEntity(PlayerEntity player, MatchStatus matchStatus, List<RoundEntity> rounds) {
        MatchEntity match = new MatchEntity();
        match.setMatchStatus(matchStatus);
        match.setPlayer(player);
        match.setRounds(rounds);
        return match;
    }

    public static MatchResponseDTO getMatchResponseDTO(Player player, MatchStatus matchStatus, List<Round> rounds) {
        MatchResponseDTO matchResponseDTO = modelMapper.map(getMatch(player, matchStatus, rounds), MatchResponseDTO.class);
        return matchResponseDTO;
    }

}
