package bagoh.game.application.service.implementation;

import bagoh.game.application.dto.domainDto.DiceValues;
import bagoh.game.application.dto.domainDto.Match;
import bagoh.game.application.dto.domainDto.Player;
import bagoh.game.application.dto.domainDto.Bid;
import bagoh.game.application.service.MatchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMatchService implements MatchService {

    private Match match;

    @Override
    public Match inicializarPartida(List<Player> players, int totalDados) {
        this.match = new Match(players.size(), totalDados);
        match.setPlayers(players);
        // repository salvando match no banco
        return match;
    }

    @Override
    public List<Player> gerarDadosJogadores(boolean gerarPrimeiroDado) {
        // Pra cada dado que cada jogador tiver, gerará um número aleatório de 1 a 6
        // Se o parametro gerarPrimeiroDado for true:
        // Gerar também um valor aleatório de 1 a 6 pra cada jogador
        // Se mais do que um jogador tirar o mesmo valor, gerar outro valor pra esses jogadores
        // Esses valores devem ser salvos no atributo primeiroDado de cada jogador
        // retornar uma lista com os Players (preenchidos com os dados gerados)
        // Se o parametro gerarPrimeiroDado for false, apenas gera os dados pros players.
        return this.match.getPlayers();
    }

    @Override
    public Bid registrarAposta(Bid bid) {
        int bidQuantity = bid.getQuantity();
        int bidType = bid.getType().getNumericType();

        initialBidValidation(bid);
        if (bid.isValid()){
            bid.setValid(false);
            bidValidation(bid);
        }
        if (bid.isValid()) {
            this.match.getBetHistory().add(bid);
            this.match.setLastBid(bid);
        }
        return bid;
    }

    @Override
    public boolean duvidarAposta() {
        int[] dicesOfTurn = countDicesOnTurn();
        return verificationOfBid(dicesOfTurn);
    }

    public void printPlayers() {
        List<Player> players = match.getPlayers();
        for (Player player : players) {
            System.out.println(player.getName());
        }
    }

//    public Long makeBet(Long idJogador, Bid bid, String order) {
//        int bidQuantity = bid.getQuantity();
//        int bidType = bid.getType().getNumericType();
//
//        initialBidValidation(bid, bidQuantity, bidType, idJogador);
//        if (bid.isValid()){
//            bid.setValid(false);
//            bidValidation(bid, bidQuantity, bidType);
//        }
//        if (bid.isValid()) {
//            this.match.getBetHistory().add(bid);
//            return getNextPlayer(idJogador, order);
//        } else {
//            return -1L;
//        }
//
//    }
    private int[] countDicesOnTurn(){
        int[] dicesOfTurn = new int[6];
        for (Player player : match.getPlayers()){
            dicesOfTurn += player.getDices().getDicesQuantities();
        }
    }

    boolean verificationOfBid(int[] dicesOfTurn){
        int bago = 1;
        int bagoQuantity = dicesOfTurn[0];

        Bid bid = match.getLastBid();
        int bidType = bid.getType().getNumericType();
        int bidQuantity = bid.getQuantity();
        if (bidType == bago){
            if (bidQuantity <= bagoQuantity){
                bid.setValid(true);
            } else {
                bid.setValid(false);
            }
        } else {
            if (dicesOfTurn[bidType-1] == 0){
                bid.setValid(false);
            }
            else if (bidQuantity <= bagoQuantity+dicesOfTurn[bidType-1]){
                bid.setValid(true);
            } else {
                bid.setValid(false);
            }
        }
        return bid.isValid();
    }
    private void initialBidValidation(Bid bid) {
        int bidQuantity = bid.getQuantity();
        int bidType = bid.getType().getNumericType();
        Long idJogador = bid.getIdPlayer();
        int bago = 1;
        int sena = 6;
        int playerPosition = 0;
        boolean findPlayer = false;
        for (Player player : match.getPlayers()) {
            if (player.getId() == idJogador) {
                findPlayer = true;
                break;
            }
            playerPosition += 1;
        }
        if (!findPlayer){
            bid.setInvalidReason("Jogador não identificado, id inexistente.");
        }else if (bidQuantity > match.getTotalDicesMatch()){
            bid.setInvalidReason("Aposta maior que a quantidade de dados na mesa.");
        } else if (bidQuantity <= 0 || bidType < bago || bidType > sena){
            bid.setInvalidReason("Aposta inválida. Os valores dos dados vão de 1 a 6 apenas.");
        } else {
            bid.setValid(true);
        }
    }

    private void bidValidation(Bid bid) {
        int bidQuantity = bid.getQuantity();
        int bidType = bid.getType().getNumericType();
        Bid higherBagoBid = getHigherBagoBid();
        Bid higherNormalBid = getHigherNormalBid();
        int higherNormalBidQuantity = higherNormalBid.getQuantity();
        int higherNormalBidType = higherNormalBid.getType().getNumericType();
        int higherBagoBidQuantity = higherBagoBid.getQuantity();
        if (higherNormalBidQuantity == 2*higherBagoBidQuantity){
            if (bidType == 1 && bidQuantity > higherBagoBidQuantity){
                bid.setValid(true);
            } else if (bidType > higherNormalBidType && bidQuantity >= higherNormalBidQuantity) {
                bid.setValid(true);
            } else if (bidType <= higherNormalBidType && bidQuantity > higherNormalBidQuantity) {
                bid.setValid(true);
            }
        } else if (higherNormalBidQuantity > 2*higherBagoBidQuantity) {
            if (bidType == 1 && bidQuantity >= Math.ceil(higherNormalBidQuantity/2)){
                bid.setValid(true);
            } else if (bidType > higherNormalBidType && bidQuantity >= higherNormalBidQuantity) {
                bid.setValid(true);
            } else if (bidType <= higherNormalBidType && bidQuantity > higherNormalBidQuantity) {
                bid.setValid(true);
            }
        } else if (higherNormalBidQuantity < 2*higherBagoBidQuantity) {
            if (bidType == 1 && bidQuantity > higherBagoBidQuantity){
                bid.setValid(true);
            } else if (bidType != 1 && bidQuantity >= 2* higherBagoBidQuantity) {
                bid.setValid(true);
            }
        }
        if(!bid.isValid()){
            bid.setInvalidReason("Aposta inválida. O valor apostado não é maior que a última aposta ou já foi utilizado.");
        }
    }

    private Bid getHigherBagoBid() {
        Bid higherBagoBid = new Bid(DiceValues.BAGO, 0, 0L);
        int bago = 1;
        for (Bid bid : match.getBetHistory()) {
            int bidType = bid.getType().getNumericType();
            int bidQuantity = bid.getQuantity();
            if(bidType == 1 && bidQuantity > higherBagoBid.getQuantity()){
                higherBagoBid = bid;
            }
        }
        return higherBagoBid;
    }

    private Bid getHigherNormalBid() {
        Bid higherNormalBid = new Bid(DiceValues.DUQUE, 0, 0L);
        for (Bid bid : match.getBetHistory()) {
            int bidType = bid.getType().getNumericType();
            int bidQuantity = bid.getQuantity();
            if(bidType != 1 && bidQuantity >= higherNormalBid.getQuantity()){
                if (bidQuantity > higherNormalBid.getQuantity()){
                    higherNormalBid = bid;
                } else if (bidType > higherNormalBid.getType().getNumericType()) {
                    higherNormalBid = bid;
                }
            }
        }
        return higherNormalBid;
    }
    
    private Long getNextPlayer(Long idJogador,String order) {
        int playerPosition = 0;
        for (Player player : match.getPlayers()) {
            if (player.getId() == idJogador) {
                break;
            }
            playerPosition += 1;
        }
        if (order == "top-down" && playerPosition < match.getNumberOfPlayer() - 1) {
            return match.getPlayers().get(playerPosition + 1).getId();
        } else if (order == "top-down") {
            return match.getPlayers().get(0).getId();
        } else if (order == "bottom-up" && playerPosition > 0) {
            return match.getPlayers().get(playerPosition - 1).getId();
        } else {
            return match.getPlayers().get(Math.toIntExact(match.getNumberOfPlayer() - 1L)).getId();
        }
    }

    public void iniciarNovoTurno(){
        this.match.getBetHistory().clear();
        // gerarDadosProsPlayers (respeitando a quatde de dados de cada Player)
        // informar de quem é a vez ( quem perdeu dado recomeça o turno)
    }






}
