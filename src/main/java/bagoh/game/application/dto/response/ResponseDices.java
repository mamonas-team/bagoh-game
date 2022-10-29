package bagoh.game.application.dto.response;

import java.util.ArrayList;
import java.util.List;

public class ResponseDices {

    private List<String> players = new ArrayList<>();

    private List<int[]> dices = new ArrayList<>();

    public List<String> getPlayers() {
        return players;
    }

    public List<int[]> getDices() {
        return dices;
    }

    public void addPlayer(String player) {
        this.players.add(player);
    }

    public void addDice(int[] dice) {
        this.dices.add(dice);
    }

}
