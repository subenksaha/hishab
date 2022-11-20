package co.hishab.test.services;

import co.hishab.test.models.Dice;
import co.hishab.test.models.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class GameService {
    @Value("${app.win.score}")
    private Integer winningScore;
    private boolean started = false;
    private Timer timer = new Timer();
    RestTemplate restTemplate = new RestTemplate();
    private final PlayerService playerService;
    Logger logger = LoggerFactory.getLogger(GameService.class);

    public GameService(PlayerService playerService) {
        this.playerService = playerService;
    }

    public int getDiceScore(){
        String url = "http://developer-test.hishab.io/api/v1/roll-dice";
        ResponseEntity<Dice> response = this.restTemplate.getForEntity(url, Dice.class);
        return  response.getBody().getScore();

    }
    public Boolean start() throws Exception {
        Integer count = this.playerService.count();
        if(count < 2 || count > 4) throw new Exception("Player count must be between 2 to 4");
        if(started) throw new Exception("Game already started");
        this.started = true;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                List<Player> playerList = playerService.state();
                int iteration = 0;
                int playerCount = playerList.size();
                while (true) {
                    Player player = playerList.get(iteration % playerCount);
                    int score = getDiceScore();
                    if(player.getScore() != null && player.getScore() >= winningScore){
                        logger.info(
                                String.format("""
                                
                                ============================
                                    Winner: %s
                                ============================
                                """, player.getName()));
                        stop();
                        break;
                    }
                    logger.info(
                            String.format("(Before)Player name: %s, Total score: %s, Current Value of Dice: %s",
                                    player.getName(),
                                    player.getScore(),
                                    score
                            )
                    );
                    switch (score) {
                        case 6 -> {
                            if (player.getScore() == null) {
                                player.setScore(0);
                            } else {
                                player.setScore(player.getScore() + score);
                            }
                        }
                        case 4 -> {
                            iteration++;
                            if(player.getScore() != null){
                                if (player.getScore() == 0) {
                                    player.setScore(null);
                                } else {
                                    player.setScore(player.getScore() - score);
                                }
                            }
                        }
                        default -> {
                            iteration++;
                            if(player.getScore() != null){
                                player.setScore(player.getScore() + score);
                            }
                        }
                    }
                }
            }
        }, 0, 2000);
        return true;
    }
    public Boolean stop(){
        timer.cancel();
        this.started = false;
        this.playerService.reset();
        timer = new Timer();
        return false;
    }
}
