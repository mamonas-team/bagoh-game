package bagoh.game.application.dto.response;

public class ResponseSaveBid {

    private boolean isAllowed;

    private String statusMatch;

    public void setAllowed(boolean allowed) {
        isAllowed = allowed;
    }

    public void statusMatch(String message) {
        statusMatch = message;
    }
}
