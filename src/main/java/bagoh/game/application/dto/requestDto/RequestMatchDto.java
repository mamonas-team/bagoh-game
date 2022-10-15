package bagoh.game.application.dto.requestDto;

public class RequestMatchDto {


    private long numberOfPlayers;
    private long numberOfDices;

    public long getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(long numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public long getNumberOfDices() {
        return numberOfDices;
    }

    public void setNumberOfDices(long numberOfDices) {
        this.numberOfDices = numberOfDices;
    }
}
