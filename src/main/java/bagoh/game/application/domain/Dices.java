package bagoh.game.application.domain;

import java.util.List;

public class Dices {

    private int numberOfDices;
    private List<DiceValues> battleDice;
    private int[] dicesQuantities = new int[6]; //[bagos,duques,ternas,quadras,quinas,senas]

    public Dices(int numberOfDices) {
        this.numberOfDices = numberOfDices;
    }

    public void resetDices() {
        this.dicesQuantities = new int[6];
        this.battleDice.clear();
    }

    public int getNumberOfDices() {
        return numberOfDices;
    }

    public void setNumberOfDices(int numberOfDices) {
        this.numberOfDices = numberOfDices;
    }

    public List<DiceValues> getBattleDice() {
        return battleDice;
    }

    public int[] getDicesQuantities() {
        return dicesQuantities;
    }

    public void setDicesQuantities(int[] dicesQuantities) {
        this.dicesQuantities = dicesQuantities;
    }
}
