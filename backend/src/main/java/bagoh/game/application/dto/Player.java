package bagoh.game.application.dto;

public class Player {

    private Long id;
    private Long numberOfDices;
    private String name;
    private int[] dices = new int[6];
    private boolean status = true;

    public Player(Long numberOfDices, String name) {
        this.numberOfDices = numberOfDices;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumberOfDices() {
        return numberOfDices;
    }

    public void setNumberOfDices(Long numberOfDices) {
        this.numberOfDices = numberOfDices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getDices() {
        return dices;
    }

    public void setDices(int[] dices) {
        this.dices = dices;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
