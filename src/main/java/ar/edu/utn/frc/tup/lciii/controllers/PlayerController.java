package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lciii.dtos.match.MatchResponseDTO;
import ar.edu.utn.frc.tup.lciii.dtos.player.NewPlayerRequestDTO;
import ar.edu.utn.frc.tup.lciii.dtos.player.PlayerResponseDTO;
import ar.edu.utn.frc.tup.lciii.services.MatchService;
import ar.edu.utn.frc.tup.lciii.services.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private MatchService matchService;

    @Operation(
            summary = "Get a player by id",
            description = "Return a player by they id. If the player doesn't exist return 404")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content =
                @Content(schema = @Schema(implementation = PlayerResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content =
                @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content =
                @Content(schema = @Schema(implementation = ErrorApi.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.getPlayerResponseDTOById(id));
    }

    @Operation(
            summary = "Create a new player",
            description = "Return the player created with your id. If a player exist with the username or email, then return 400. " +
                    "Additionally the email must be valid.")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content =
            @Content(schema = @Schema(implementation = PlayerResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Username or email already exists", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "400", description = "The email format it's not valid", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content =
                @Content(schema = @Schema(implementation = ErrorApi.class)))
    })
    @PostMapping("")
    public ResponseEntity<PlayerResponseDTO> postPlayer(@RequestBody @Valid NewPlayerRequestDTO newPlayerRequestDTO) throws IllegalAccessException {
        return ResponseEntity.ok( playerService.createNewPlayer(newPlayerRequestDTO));
    }

    @Operation(
            summary = "Get all matches of a player",
            description = "Return all matches belonging to a player.")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content =
            @Content(schema = @Schema(implementation = PlayerResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content =
            @Content(schema = @Schema(implementation = ErrorApi.class)))
    })
    @GetMapping("/{id}/matches")
    public ResponseEntity<List<MatchResponseDTO>> getMatchesOfPlayer(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.getMatchesByPlayer(id));
    }
}
