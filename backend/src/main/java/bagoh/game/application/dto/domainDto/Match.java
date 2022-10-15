package bagoh.game.application.dto.domainDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Match {

    private Date startDate;
    private Date finalDate;
    private Long id;
    private int numberOfPlayer;
    private int numberOfInitialDices;
    private int totalDicesMatch = 0;
    private Player playerOfTheTurn;
    private Player firstPlayer;
    private List<Player> players = new ArrayList<>();
    private List<Bid> betHistory = new ArrayList<>();

    public Match(int numberOfPlayer, int numberOfInitialDices) {
        this.numberOfPlayer = numberOfPlayer;
        this.numberOfInitialDices = numberOfInitialDices;
    }

    public void resetPlayersDices() {
        for (Player player : players) {
            player.getDices().resetDices();
        }
    }
    public int[] countDicesInTurn(){
        int[] dicesOfTurn = new int[6];
        for (Player player : players){
            int[] dicesQuantities = player.getDices().getDicesQuantities();
            for (int i=0; i<dicesQuantities.length; i++){
                dicesOfTurn[i] += dicesQuantities[i];
            }
        }
        return dicesOfTurn;
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

    public int getNumberOfPlayer() {
        return numberOfPlayer;
    }

    public void setNumberOfPlayer(int numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }

    public int getNumberOfInitialDices() {
        return numberOfInitialDices;
    }

    public int getTotalDicesMatch() {
        totalDicesMatch = 0;
        for (Player player : players){
            totalDicesMatch += player.getDices().getNumberOfDices();
        }
        return totalDicesMatch;
    }

    public void setTotalDicesMatch(int totalDicesMatch) {
        this.totalDicesMatch = totalDicesMatch;
    }

    public void setNumberOfInitialDices(int numberOfInitialDices) {
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

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public Bid getLastBid() {
        return betHistory.get(betHistory.size()-1);
    }

    public void setLastBid(Bid lastBid) {
        this.betHistory.set(betHistory.size()-1, lastBid);
    }

    public List<Bid> getBetHistory() {
        return betHistory;
    }
}
