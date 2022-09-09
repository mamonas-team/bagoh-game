package bagoh.game.application.dto.domainDto;

public class Bid {

    private DiceValues type;
    private int quantity;
    private Long idPlayer;
    private boolean registered = false;
    private String unregisteredReason = "";
    private boolean valid;

    public Bid(DiceValues type, int quantity, Long idPlayer) {
        this.type = type;
        this.quantity = quantity;
        this.idPlayer = idPlayer;

    }

    public DiceValues getType() {
        return type;
    }

    public void setType(DiceValues type) {
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

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public String getUnregisteredReason() {
        return unregisteredReason;
    }

    public void setUnregisteredReason(String unregisteredReason) {
        this.unregisteredReason = unregisteredReason;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}