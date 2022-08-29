package bagoh.game.application.service.implementation;

import bagoh.game.application.dto.domainDto.BidTypes;
import bagoh.game.application.dto.domainDto.Match;
import bagoh.game.application.dto.domainDto.Player;
import bagoh.game.application.dto.domainDto.Bid;

import java.util.ArrayList;
import java.util.List;

public class DefaultMatchService {

    private Match match;
    private List<Player> players;
    private List<Bid> betsHistory = new ArrayList<>();
    private int totalDicesMatch;


    public DefaultMatchService(Match match) {
        this.match = match;
        this.players = match.getPlayers();
        this.totalDicesMatch = match.getTotalDicesMatch();
    }


    public void inicializarJogadores() {
        Long numberOfPlayers = match.getNumberOfPlayer();

        for (int i = 1; i <= numberOfPlayers; i++) {
            String name = "Jogador-" + i;
            Player player = new Player(match.getNumberOfInitialDices(), name);
            player.setId(Long.valueOf(i));
            players.add(player);
        }
    }

    public Long fazerAposta(Long idJogador, Bid bid, String order) {
        System.out.println("Vez do jogador-"+idJogador);
        Long proximo = makeBet(idJogador, bid, order);
        if (bid.isValid()) {
            System.out.println("aposta de "+bid.getQuantity()+" "+bid.getType()+" passou. O próximo jogador é jogador-"+proximo+"\n");
            return proximo;
        } else {
            System.out.println(bid.getInvalidReason());
            return proximo;
        }
    }

    public void printPlayers() {
        for (Player player : players) {
            System.out.println(player.getName());
        }
    }

    public void gerarDadosJogadores() {
        // popular players com numeros aleatorios, pra cada jogador.
    }


    public void definirPrimeiroJogador() {
        // gerar um numero aleatorio de 1 a 6 pra cada player
        // ver qual o maior e definir PrimeiroJogador
        // Definir PrimeiroJogador como playerOfTheTurn.
    }


    public Long makeBet(Long idJogador, Bid bid, String order) {
        int bidQuantity = bid.getQuantity();
        int bidType = bid.getType().getNumericType();

        initialBidValidation(bid, bidQuantity, bidType, idJogador);
        if (bid.isValid()){
            bid.setValid(false);
            bidValidation(bid, bidQuantity, bidType);
        }
        if (bid.isValid()) {
            this.betsHistory.add(bid);
            return getNextPlayer(idJogador, order);
        } else {
            return -1L;
        }

    }

    private void initialBidValidation(Bid bid, int bidQuantity, int bidType, Long idJogador) {
        int bago = 1;
        int sena = 6;
        int playerPosition = 0;
        boolean findPlayer = false;
        for (Player player : players) {
            if (player.getId() == idJogador) {
                findPlayer = true;
                break;
            }
            playerPosition += 1;
        }
        if (!findPlayer){
            bid.setInvalidReason("Jogador não identificado, id inexistente.");
        }else if (bidQuantity > totalDicesMatch){
            bid.setInvalidReason("Aposta maior que a quantidade de dados na mesa.");
        } else if (bidQuantity <= 0 || bidType < bago || bidType > sena){
            bid.setInvalidReason("Aposta inválida. Os valores dos dados vão de 1 a 6 apenas.");
        } else {
            bid.setValid(true);
        }
    }

    private void bidValidation(Bid bid, int bidQuantity, int bidType) {
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
        Bid higherBagoBid = new Bid(BidTypes.BAGO, 0, 0L);
        int bago = 1;
        for (Bid bid : betsHistory) {
            int bidType = bid.getType().getNumericType();
            int bidQuantity = bid.getQuantity();
            if(bidType == 1 && bidQuantity > higherBagoBid.getQuantity()){
                higherBagoBid = bid;
            }
        }
        return higherBagoBid;
    }

    private Bid getHigherNormalBid() {
        Bid higherNormalBid = new Bid(BidTypes.DUQUE, 0, 0L);
        for (Bid bid : betsHistory) {
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
        for (Player player : players) {
            if (player.getId() == idJogador) {
                break;
            }
            playerPosition += 1;
        }
        if (order == "top-down" && playerPosition < match.getNumberOfPlayer() - 1) {
            return players.get(playerPosition + 1).getId();
        } else if (order == "top-down") {
            return players.get(0).getId();
        } else if (order == "bottom-up" && playerPosition > 0) {
            return players.get(playerPosition - 1).getId();
        } else {
            return players.get(Math.toIntExact(match.getNumberOfPlayer() - 1L)).getId();
        }
    }

    ;

    public void duvidarDeAposta (){
        // validar se a aposta é verdadeira
        // informar quem venceu a aposta (quem apostou ou quem duvidou)
        // remover um dado do perdedor
        // iniciarNovoTurno()
    }

    public void iniciarNovoTurno(){
        this.betsHistory.clear();
        this.totalDicesMatch = match.getTotalDicesMatch();
        // gerarDadosProsPlayers (respeitando a quatde de dados de cada Player)
        // informar de quem é a vez ( quem perdeu dado recomeça o turno)
    }

}
