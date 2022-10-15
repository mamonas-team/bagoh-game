package bagoh.game.application.dto.domainDto;

import java.util.List;

public class Player {

    private Long id;
    private String name;
    private Dices dices;
    private boolean status = true;

    public Player(int numberOfDices, String name) {
        this.dices = new Dices(numberOfDices);
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dices getDices() {
        return dices;
    }

    public void setDices(Dices dices) {
        this.dices = dices;
    }
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
