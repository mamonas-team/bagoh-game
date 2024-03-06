package bagoh.game.application.dto.request;

public class RequestBid {

    private int[] bid = new int[2]; //{NroDeDadosNaAposta, FaceApostada}

    private Long idPlayer;

    public int[] getBid() {
        return bid;
    }

    public void setBid(int[] bid) {
        this.bid = bid;
    }

    public Long getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(Long idPlayer) {
        this.idPlayer = idPlayer;
    }

}
