package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.match.MatchResponseDTO;
import ar.edu.utn.frc.tup.lciii.dtos.player.NewPlayerRequestDTO;
import ar.edu.utn.frc.tup.lciii.dtos.player.PlayerResponseDTO;
import ar.edu.utn.frc.tup.lciii.helpers.MatchHelper;
import ar.edu.utn.frc.tup.lciii.helpers.PlayerHelper;
import ar.edu.utn.frc.tup.lciii.helpers.RoundHelper;
import ar.edu.utn.frc.tup.lciii.models.MatchStatus;
import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.models.Round;
import ar.edu.utn.frc.tup.lciii.services.MatchService;
import ar.edu.utn.frc.tup.lciii.services.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlayerController.class)
class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @MockBean
    private MatchService matchService;

    @Autowired
    private ObjectMapper objectMapper;

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    void getById() throws Exception {
        PlayerResponseDTO playerResponseDTO = modelMapper.map(PlayerHelper.getPlayer(PlayerHelper.EMAIL_OK, PlayerHelper.BALANCE_INIT), PlayerResponseDTO.class);

        when(playerService.getPlayerResponseDTOById(1L)).thenReturn(playerResponseDTO);
        this.mockMvc.perform(get("/players/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.user_name").value(PlayerHelper.USER_NAME))
                .andExpect(jsonPath("$.balance").value(PlayerHelper.BALANCE_INIT))
                .andExpect(jsonPath("$.avatar").value(PlayerHelper.AVATAR))
        ;
    }

    @Test
    void getById_NotFound() throws Exception {
        PlayerResponseDTO playerResponseDTO = modelMapper.map(PlayerHelper.getPlayer(PlayerHelper.EMAIL_OK, PlayerHelper.BALANCE_INIT), PlayerResponseDTO.class);

        when(playerService.getPlayerResponseDTOById(1L)).thenThrow(new EntityNotFoundException("The player id 1 do not exist"));
        this.mockMvc.perform(get("/players/1")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("The player id 1 do not exist"))
        ;
    }

    @Test
    void postPlayer() throws Exception {
        NewPlayerRequestDTO newPlayerRequestDTO = modelMapper.map(PlayerHelper.getPlayer(PlayerHelper.EMAIL_OK, PlayerHelper.BALANCE_INIT), NewPlayerRequestDTO.class);
        PlayerResponseDTO playerResponseDTO = modelMapper.map(PlayerHelper.getPlayer(PlayerHelper.EMAIL_OK, PlayerHelper.BALANCE_INIT), PlayerResponseDTO.class);

        when(playerService.createNewPlayer(newPlayerRequestDTO)).thenReturn(playerResponseDTO);
        this.mockMvc.perform(post("/players").contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(newPlayerRequestDTO)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.user_name").value(PlayerHelper.USER_NAME))
                .andExpect(jsonPath("$.balance").value(PlayerHelper.BALANCE_INIT))
                .andExpect(jsonPath("$.avatar").value(PlayerHelper.AVATAR))
        ;
    }

    @Test
    void postPlayer_wrongEmail() throws Exception {
        NewPlayerRequestDTO newPlayerRequestDTO = modelMapper.map(PlayerHelper.getPlayer(PlayerHelper.EMAIL_NOT_OK, PlayerHelper.BALANCE_INIT), NewPlayerRequestDTO.class);

        this.mockMvc.perform(post("/players").contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(newPlayerRequestDTO)))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("The email format it's not valid"))
        ;
    }

    @Test
    void postPlayer_duplicatedEmail() throws Exception {
        NewPlayerRequestDTO newPlayerRequestDTO = modelMapper.map(PlayerHelper.getPlayer(PlayerHelper.EMAIL_OK, PlayerHelper.BALANCE_INIT), NewPlayerRequestDTO.class);

        when(playerService.createNewPlayer(newPlayerRequestDTO)).thenThrow(new IllegalArgumentException("The user_name or email already exists"));
        this.mockMvc.perform(post("/players").contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(newPlayerRequestDTO)))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("The user_name or email already exists"))
        ;
    }

    @Test
    void getMatchesOfPlayer() throws Exception {
        Player player = PlayerHelper.getPlayer(PlayerHelper.EMAIL_OK, PlayerHelper.BALANCE_INIT);
        List<Round> rounds = Arrays.asList(RoundHelper.getRoundAppWins());
        List<MatchResponseDTO> response = Arrays.asList(MatchHelper.getMatchResponseDTO(player, MatchStatus.FINISH, rounds));
        when(matchService.getMatchesByPlayer(1L)).thenReturn(response);

        this.mockMvc.perform(get("/players/1/matches")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(response.get(0).getId()))
                .andExpect(jsonPath("$[0].rounds[0].id").value(response.get(0).getRounds().get(0).getId()))
                .andExpect(jsonPath("$[0].rounds[0].match_id").value(response.get(0).getRounds().get(0).getMatchId()))
                .andExpect(jsonPath("$[0].rounds[0].player_cards[0].suit").value(response.get(0).getRounds().get(0).getPlayerCards().get(0).getCardSuit().name()))
                .andExpect(jsonPath("$[0].rounds[0].player_cards[0].number").value(response.get(0).getRounds().get(0).getPlayerCards().get(0).getValue()))
                .andExpect(jsonPath("$[0].rounds[0].player_cards[1].suit").value(response.get(0).getRounds().get(0).getPlayerCards().get(1).getCardSuit().name()))
                .andExpect(jsonPath("$[0].rounds[0].player_cards[1].number").value(response.get(0).getRounds().get(0).getPlayerCards().get(1).getValue()))
        ;
    }

    @Test
    void getMatchesOfPlayer_NotFound() throws Exception {
        Player player = PlayerHelper.getPlayer(PlayerHelper.EMAIL_OK, PlayerHelper.BALANCE_INIT);
        List<Round> rounds = Arrays.asList(RoundHelper.getRoundAppWins());
        List<MatchResponseDTO> response = Arrays.asList(MatchHelper.getMatchResponseDTO(player, MatchStatus.PLAYING, rounds));
        when(matchService.getMatchesByPlayer(1L)).thenThrow(new EntityNotFoundException("The player do not have matches finished"));

        this.mockMvc.perform(get("/players/1/matches")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("The player do not have matches finished"))
        ;
    }

}