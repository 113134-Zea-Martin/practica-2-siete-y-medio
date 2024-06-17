package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lciii.dtos.match.MatchResponseDTO;
import ar.edu.utn.frc.tup.lciii.dtos.match.NewMatchRequestDTO;
import ar.edu.utn.frc.tup.lciii.dtos.round.NewMatchRoundRequestDTO;
import ar.edu.utn.frc.tup.lciii.dtos.round.RoundPlayDTO;
import ar.edu.utn.frc.tup.lciii.dtos.round.RoundResponseDTO;
import ar.edu.utn.frc.tup.lciii.services.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Operation(
            summary = "Create a new match",
            description = "Return the match created with they id. If a player has a PLAYING match, then return that match. " +
                    "If the player do not exist, then return 404 not found")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content =
            @Content(schema = @Schema(implementation = MatchResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "The player id {playerId} do not exist", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content =
            @Content(schema = @Schema(implementation = ErrorApi.class)))
    })
    @PostMapping("")
    public ResponseEntity<MatchResponseDTO> postMatch(@RequestBody @Valid NewMatchRequestDTO newMatchRequestDTO) {
        MatchResponseDTO matchCreated = matchService.createMatch(newMatchRequestDTO);
        if(Objects.isNull(matchCreated)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has an error");
        } else {
            return ResponseEntity.ok(matchCreated);
        }
    }

    @Operation(
            summary = "Get a match by id",
            description = "Return a match by they id. If the match doesn't exist return 404")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content =
            @Content(schema = @Schema(implementation = MatchResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content =
            @Content(schema = @Schema(implementation = ErrorApi.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<MatchResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.getMatchResponseDTOById(id));
    }

    @Operation(
            summary = "Create a new Round in a match",
            description = "Return the round created in the match if the player do not have " +
                    "unfinished rounds, otherwise return the unfinished round." +
                    "If the match do not exist, then return 404 not found" +
    "If the match to not belong to the player id received in the request.body. then return 400 bad request")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content =
            @Content(schema = @Schema(implementation = RoundResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "The match id {matchId} does not belong to player {playerId}", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient balance", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "404", description = "The match id {matchId} not found", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content =
            @Content(schema = @Schema(implementation = ErrorApi.class)))
    })
    @PostMapping("/{id}/round/")
    public ResponseEntity<RoundResponseDTO> postRoundMatch(@PathVariable Long id, @RequestBody @Valid NewMatchRoundRequestDTO playRequest) {
        return ResponseEntity.ok(matchService.createRoundMatch(id, playRequest));
    }

    @Operation(
            summary = "Manage a round update (the play of the player)",
            description = "Return the round updated based on the player decision." +
                    "If the round or match do not exist, then return 404 not found" +
                    "If the match to not belong to the player id received in the request.body then return 400 bad request" +
                    "If the round to not belong to the match then return 400 bad request")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content =
            @Content(schema = @Schema(implementation = RoundResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "The match id {matchId} does not belong to player {playerId}", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "400", description = "The round id {roundId} does not belong to match {matchId}", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "403", description = "Round is end", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "404", description = "The match id {matchId} not found", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "404", description = "The round id {roundId} not found", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content =
            @Content(schema = @Schema(implementation = ErrorApi.class)))
    })
    @PutMapping("/{matchId}/round/{roundId}")
    public ResponseEntity<RoundResponseDTO> putRoundMatch(@PathVariable Long matchId, @PathVariable Long roundId, @RequestBody @Valid RoundPlayDTO roundPlayDTO) {
        return ResponseEntity.ok(matchService.playRoundMatch(matchId, roundId, roundPlayDTO));
    }
}
