package bagoh.game.application.dto.response;

import java.util.ArrayList;
import java.util.List;

public class ResponseBattleDices {

    private List<String> players = new ArrayList<>();

    private List<List<Integer>> battleDices = new ArrayList<>();

    public List<String> getPlayers() {
        return players;
    }

    public List<List<Integer>> getBattleDices() {
        return battleDices;
    }

    public void addPlayer(String player) {
        this.players.add(player);
    }

    public void addDice(List<Integer> dice) {
        this.battleDices.add(dice);
    }

}
