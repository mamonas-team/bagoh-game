package bagoh.game.application.dto.domainDto;

public enum BidTypes {
    BAGO(1), DUQUE(2), TERNO(3), QUADRA(4), QUINA(5), SENA(6);

    private final int value;

    BidTypes(int value) {
        this.value = value;
    }

    public int getNumericType(){
        return value;
    }
}
