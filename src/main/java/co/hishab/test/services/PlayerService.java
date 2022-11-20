package co.hishab.test.services;

import co.hishab.test.models.Player;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PlayerService {
    private final List<Player> list = new LinkedList<>();

    public Integer count(){
        return list.size();
    }

    public Player add(Player player) throws Exception {
        if(count() >= 4) throw new Exception("Max Player Reached");
        this.list.add(player);
        return player;
    }

    public void reset(){
        for(Player player: list){
            player.setScore(null);
        }
    }

    public List<Player> state(){
        return this.list;
    }
}
