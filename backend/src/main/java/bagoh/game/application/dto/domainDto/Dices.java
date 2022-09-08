package bagoh.game.application.dto.domainDto;

public class Dices {

    private int numberOfDices;
    private DiceValues fightDice;
    private int[] dicesQuantities = new int[6]; //[bagos,duques,ternas,quadras,quinas,senas]

    public Dices(int numberOfDices) {
        this.numberOfDices = numberOfDices;
    }

    public int getNumberOfDices() {
        return numberOfDices;
    }

    public void setNumberOfDices(int numberOfDices) {
        this.numberOfDices = numberOfDices;
    }

    public DiceValues getFightDice() {
        return fightDice;
    }

    public void setFightDice(DiceValues fightDice) {
        this.fightDice = fightDice;
    }

    public int[] getDicesQuantities() {
        return dicesQuantities;
    }

    public void setDicesQuantities(int[] dicesQuantities) {
        this.dicesQuantities = dicesQuantities;
    }
}
