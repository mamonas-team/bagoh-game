package bagoh.game.application.dto.domainDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Match {

    private Date startDate;
    private Date finalDate;
    private Long id;
    private Long numberOfPlayer;
    private Long numberOfInitialDices;
    private int totalDicesMatch = 0;
    private Player playerOfTheTurn;
    private Player firstPlayer;
    private List<Player> players = new ArrayList<>();


    public Match(Long numberOfPlayer, Long numberOfInitialDices) {
        this.numberOfPlayer = numberOfPlayer;
        this.numberOfInitialDices = numberOfInitialDices;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumberOfPlayer() {
        return numberOfPlayer;
    }

    public void setNumberOfPlayer(Long numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }

    public Long getNumberOfInitialDices() {
        return numberOfInitialDices;
    }

    public int getTotalDicesMatch() {
        totalDicesMatch = 0;
        for (Player player : players){
            totalDicesMatch += player.getNumberOfDices();
        }
        return totalDicesMatch;
    }

    public void setTotalDicesMatch(int totalDicesMatch) {
        this.totalDicesMatch = totalDicesMatch;
    }

    public void setNumberOfInitialDices(Long numberOfInitialDices) {
        this.numberOfInitialDices = numberOfInitialDices;
    }

    public Player getPlayerOfTheTurn() {
        return playerOfTheTurn;
    }

    public void setPlayerOfTheTurn(Player playerOfTheTurn) {
        this.playerOfTheTurn = playerOfTheTurn;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
