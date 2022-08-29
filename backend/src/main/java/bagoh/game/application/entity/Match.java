package bagoh.game.application.entity;


//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//@Entity
public class Match {


//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long numberOfPlayer;

    private long numberOfDices;

    public Match(long numberOfPlayer, long numberOfDices) {
        this.numberOfPlayer = numberOfPlayer;
        this.numberOfDices = numberOfDices;
    }

    public Match() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumberOfPlayer() {
        return numberOfPlayer;
    }

    public void setNumberOfPlayer(long numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }

    public long getNumberOfDices() {
        return numberOfDices;
    }

    public void setNumberOfDices(long numberOfDices) {
        this.numberOfDices = numberOfDices;
    }
}

