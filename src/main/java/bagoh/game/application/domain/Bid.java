package bagoh.game.application.domain;

public class Bid {

    private DiceValues value;
    private int quantity;
    private Long idPlayer;
    private boolean isAllowed;
    private boolean isTrue;
    private String unallowedBidReason = "";


    public Bid(DiceValues type, int quantity, Long idPlayer) {
        this.value = type;
        this.quantity = quantity;
        this.idPlayer = idPlayer;

    }

    public DiceValues getValue() {
        return value;
    }

    public void setValue(DiceValues value) {
        this.value = value;
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

    public String getUnallowedBidReason() {
        return unallowedBidReason;
    }

    public void setUnallowedBidReason(String unallowedBidReason) {
        this.unallowedBidReason = unallowedBidReason;
    }

    public boolean isAllowed() {
        return isAllowed;
    }

    public void setAllowed(boolean allowed) {
        this.isAllowed = allowed;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }
}