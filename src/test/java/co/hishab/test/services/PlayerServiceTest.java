package co.hishab.test.services;

import co.hishab.test.models.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlayerServiceTest {
    private PlayerService playerService;

    @BeforeEach()
    void initTest(){
        this.playerService = new PlayerService();
    }

    @Test
    void initialStateShouldHaveNoPlayer(){
        List<Player> playerList = playerService.state();
        Assertions.assertAll(
                () -> Assertions.assertNotEquals(playerList, null),
                () -> Assertions.assertEquals(playerList.size(), 0)
        );
    }
    @Test
    void numberOfPlayerCreatedShouldMatch() throws Exception {
        Player player1 = Player.from("Suben", 28f);
        Player player2 = Player.from("Sakib", 26f);
        playerService.add(player1);
        playerService.add(player2);
        List<Player> playerList = playerService.state();
        Assertions.assertEquals(playerList.size(), 2);
    }

    @Test
    void moreThan4PlayerShouldThrowError() throws Exception {
        Player player1 = Player.from("Suben", 28f);
        Player player2 = Player.from("Sakib", 26f);
        Player player3 = Player.from("Fahim", 28f);
        Player player4 = Player.from("Rakib", 26f);
        Player player5 = Player.from("Samir", 26f);
        playerService.add(player1);
        playerService.add(player2);
        playerService.add(player3);
        playerService.add(player4);
        Assertions.assertAll(
                () -> Assertions.assertThrows(Exception.class, () -> playerService.add(player5)),
                () -> Assertions.assertEquals(playerService.state().size(), 4)
        );
    }

}
