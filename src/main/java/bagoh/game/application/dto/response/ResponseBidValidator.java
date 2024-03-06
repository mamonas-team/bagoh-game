package bagoh.game.application.dto.response;


public class ResponseBidValidator {

    private boolean bidIsTrue;

    private String statusMatch;

    public boolean isBidIsTrue() {
        return bidIsTrue;
    }

    public void setBidIsTrue(boolean bidIsTrue) {
        this.bidIsTrue = bidIsTrue;
    }

    public String getStatusMatch() {
        return statusMatch;
    }

    public void setStatusMatch(String statusMatch) {
        this.statusMatch = statusMatch;
    }
}
