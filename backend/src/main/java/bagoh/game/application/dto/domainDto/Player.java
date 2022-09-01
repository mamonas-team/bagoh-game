package bagoh.game.application.dto.domainDto;

import java.util.List;

public class Player {

    private Long id;
    private int numberOfDices;
    private String name;
    private List<DiceValues> firstDice;
    private DiceValues[] dices = new DiceValues[6]; // TO-DO - talvez seja vetor de int mesmo
    private boolean status = true;

    public Player(int numberOfDices, String name) {
        this.numberOfDices = numberOfDices;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfDices() {
        return numberOfDices;
    }

    public void setNumberOfDices(int numberOfDices) {
        this.numberOfDices = numberOfDices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DiceValues[] getDices() {
        return dices;
    }

    public void setDices(DiceValues[] dices) {
        this.dices = dices;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
