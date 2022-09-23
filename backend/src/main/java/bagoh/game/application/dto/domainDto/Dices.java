package bagoh.game.application.dto.domainDto;

import java.util.List;

public class Dices {

    private int numberOfDices;
    private List<DiceValues> fightDice;
    private int[] dicesQuantities = new int[6]; //[bagos,duques,ternas,quadras,quinas,senas]

    public Dices(int numberOfDices) {
        this.numberOfDices = numberOfDices;
    }

    public void resetDices() {
        this.dicesQuantities = new int[6];
        this.fightDice.clear();
    }

    public int getNumberOfDices() {
        return numberOfDices;
    }

    public void setNumberOfDices(int numberOfDices) {
        this.numberOfDices = numberOfDices;
    }

    public List<DiceValues> getFightDice() {
        return fightDice;
    }

    public int[] getDicesQuantities() {
        return dicesQuantities;
    }

    public void setDicesQuantities(int[] dicesQuantities) {
        this.dicesQuantities = dicesQuantities;
    }
}
