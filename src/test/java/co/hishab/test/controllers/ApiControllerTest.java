package co.hishab.test.controllers;

import co.hishab.test.dtos.CreatePlayer;
import co.hishab.test.models.Player;
import co.hishab.test.services.GameService;
import co.hishab.test.services.PlayerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ApiControllerTest {

    GameService gameService;
    PlayerService playerService;

    private ApiController apiController;

    @BeforeEach()
    void initTest(){
        playerService = new PlayerService();
        gameService = new GameService(playerService);
        apiController = new ApiController(playerService, gameService);
    }

    @Test
    void createdPlayerShouldHaveIdButScore() throws Exception {
        CreatePlayer body = new CreatePlayer("Suben", 28f);
        Player player = this.apiController.createPlayer(body);
        Assertions.assertAll(
                () -> Assertions.assertNotEquals(player.getId(), null),
                () -> Assertions.assertNull(player.getScore())
        );
    }
}
