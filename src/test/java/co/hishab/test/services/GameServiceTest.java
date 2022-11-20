package co.hishab.test.services;

import co.hishab.test.models.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameServiceTest {

    private PlayerService playerService;
    private GameService gameService;

    @BeforeEach()
    void initTest(){
        this.playerService = new PlayerService();
        this.gameService = new GameService(this.playerService);
    }

    @Test
    void diceShouldReturnAIntegerBetween1To6(){
        int score = gameService.getDiceScore();
        Assertions.assertAll(
                () -> Assertions.assertTrue(score > 0),
                () -> Assertions.assertTrue(score < 7)
        );
    }

    @Test
    void startShouldThrowExceptionWithoutMinPlayer(){
        Assertions.assertThrows(Exception.class, ()-> this.gameService.start());
    }

    @Test
    void shouldStartWithMinimumPlayer() throws Exception {
        this.playerService.add(Player.from("Suben", 28f));
        this.playerService.add(Player.from("Sakib", 28f));
        Assertions.assertEquals(this.gameService.start(), true);
    }

    @Test
    void shouldThrowErrorWhileAlreadyStarted() throws Exception {
        this.playerService.add(Player.from("Suben", 28f));
        this.playerService.add(Player.from("Sakib", 28f));
        this.gameService.start();
        Assertions.assertThrows(Exception.class, ()-> this.gameService.start());
    }
}
