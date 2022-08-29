package bagoh.game.application.dto.domainDto;

public class Bid {

    private BidTypes type;
    private int quantity;
    private Long idPlayer;
    private boolean valid = false;
    private String invalidReason = "";

    public Bid(BidTypes type, int quantity, Long idPlayer) {
        this.type = type;
        this.quantity = quantity;
        this.idPlayer = idPlayer;

    }

    public BidTypes getType() {
        return type;
    }

    public void setType(BidTypes type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(Long idPlayer) {
        this.idPlayer = idPlayer;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getInvalidReason() {
        return invalidReason;
    }

    public void setInvalidReason(String invalidReason) {
        this.invalidReason = invalidReason;
    }
}