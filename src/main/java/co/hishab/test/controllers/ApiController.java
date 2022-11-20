package co.hishab.test.controllers;

import co.hishab.test.dtos.CreatePlayer;
import co.hishab.test.models.Player;
import co.hishab.test.services.GameService;
import co.hishab.test.services.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {
    PlayerService playerService;
    GameService gameService;

    public ApiController(
            PlayerService playerService,
            GameService gameService
    ) {
        this.playerService = playerService;
        this.gameService = gameService;
    }

    @PostMapping("/player")
    Player createPlayer(@RequestBody CreatePlayer body) throws Exception {
        Player player = Player.from(body.getName(), body.getAge());
        return this.playerService.add(player);
    }
    @PostMapping("/game")
    Boolean startGame(@RequestBody boolean start) throws Exception {
        if(start) return this.gameService.start();
        else return this.gameService.stop();
    }

    @GetMapping("/status")
    List<Player> getStatus(){
        return this.playerService.state();
    }
}
