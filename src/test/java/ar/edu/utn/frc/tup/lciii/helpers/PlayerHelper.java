package ar.edu.utn.frc.tup.lciii.helpers;

import ar.edu.utn.frc.tup.lciii.entities.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.models.Player;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

public class PlayerHelper {

    public static final String EMAIL_OK = "email@email.com";
    public static final String EMAIL_NOT_OK = "email_email.com";
    public static final String USER_NAME = "tuplciii";
    public static final String PASSWORD = "tuplciii";
    public static final String AVATAR = "tuplciii";
    public static final BigDecimal BALANCE_EMPTY = BigDecimal.ZERO;
    public static final BigDecimal BALANCE_POOR = BigDecimal.valueOf(15);
    public static final BigDecimal BALANCE_RICH = BigDecimal.valueOf(10000);
    public static final BigDecimal BALANCE_INIT = BigDecimal.valueOf(2000);

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Player getPlayer(String email, BigDecimal balance) {
        Player player = new Player();
        player.setId(1L);
        player.setUserName(USER_NAME);
        player.setPassword(PASSWORD);
        player.setEmail(email);
        player.setAvatar(AVATAR);
        player.setBalanceChips(balance);
        return player;
    }

    public static PlayerEntity getPlayerEntity(String email, BigDecimal balance) {
        PlayerEntity playerEntity = modelMapper.map(getPlayer(email, balance), PlayerEntity.class);
        playerEntity.setId(null);
        return playerEntity;
    }
}
