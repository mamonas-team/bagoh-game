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
        playerValidation(bid);
        initialValidationOfBidRegistration(bid);
        validationOfBidRegistration(bid);
        this.match.getBetHistory().add(bid);
        this.match.setLastBid(bid);
        return bid;
    }

    @Override
    public Match validarAposta() {
        int[] dicesOfTurn = match.countDicesInTurn();
        return this.match;
        //return verificationOfBid(dicesOfTurn);
    }



    public void printPlayers() {
        List<Player> players = match.getPlayers();
        for (Player player : players) {
            System.out.println(player.getName());
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

    private void playerValidation(Bid bid) {
        Long idJogador = bid.getIdPlayer();

        boolean findPlayer = false;

        for (Player player : match.getPlayers()) {
            if (player.getId() == idJogador) {
                findPlayer = true;
                bid.setValid(true);
                break;
            }
        }

        if (!findPlayer) {
            bid.setUnregisteredReason("Jogador não identificado, id inexistente.");
        }

    }
    private Match initialValidationOfBidRegistration(Bid bid) {
        int bidQuantity = bid.getQuantity();
        int bidType = bid.getType().getNumericType();
        int bago = 1;
        int sena = 6;
        if (bidQuantity > match.getTotalDicesMatch()){
            bid.setUnregisteredReason("Aposta maior que a quantidade de dados na mesa.");
        } else if (bidQuantity <= 0 || bidType < bago || bidType > sena){
            bid.setUnregisteredReason("Aposta inválida. Os valores dos dados vão de 1 a 6 apenas.");
        } else {
            bid.setRegistered(true);
        }

        return this.match;
    }

    private void validationOfBidRegistration(Bid bid) {
        int bidQuantity = bid.getQuantity();
        int bidType = bid.getType().getNumericType();
        boolean bidIsBago = bid.getType() == DiceValues.BAGO;
        Bid higherBagoBid = getHigherBagoBid(); //Maior aposta envolvendo bagos no turno
        Bid higherNormalBid = getHigherNormalBid(); //Maior aposta não envolvendo bagos (normal) no turno

        int higherNormalBidQuantity = higherNormalBid.getQuantity();
        int higherNormalBidType = higherNormalBid.getType().getNumericType();
        int higherBagoBidQuantity = higherBagoBid.getQuantity();

        if (higherNormalBidQuantity == 2*higherBagoBidQuantity){
            /*
            Se a quantidade de dados na maior aposta normal é exatamente o dobro da quantidade de dados na
            maior aposta de bagos (por exemplo: 3 BAGOS e 6 QUINAS)
             */
            if (bidIsBago && bidQuantity > higherBagoBidQuantity){
                /*
                A aposta a ser registrada:
                    - É em BAGOS;
                    - Ela tem mais BAGOS que na maior aposta de BAGOS.
                 */
                bid.setRegistered(true);
            } else if (bidType > higherNormalBidType && bidQuantity >= higherNormalBidQuantity) {
                /*
                A aposta a ser registrada:
                    - É normal mas em um valor "maior" que o da maior aposta normal (por exemplo: a
                aposta a ser registrada é em SENA e a maior aposta normal em QUADRA);
                    - Tem o número de dados maior ou igual que o da maior aposta normal.
                 */
                bid.setRegistered(true);
            } else if (bidType <= higherNormalBidType && bidQuantity > higherNormalBidQuantity) {
                /*
                A aposta a ser registrada:
                    - É normal mas em um valor "menor ou igual" que o da maior aposta normal (por exemplo: a
                aposta a ser registrada é em DUQUE e a maior aposta normal em QUINA);
                    - Tem o número de dados maior que o da maior aposta normal.
                 */
                bid.setRegistered(true);
            }
        } else if (higherNormalBidQuantity > 2*higherBagoBidQuantity) {
            /*
            Se a quantidade de dados na maior aposta normal é maior que o dobro da quantidade de dados na
            maior aposta de bagos (por exemplo: 3 BAGOS e 7 QUINAS)
             */
            if (bidIsBago && bidQuantity >= Math.ceil(higherNormalBidQuantity/2)){
                /*
                A aposta a ser registrada:
                    - É em BAGOS;
                    - O dobro da quantidade de dados na aposta a ser registrado é maior ou igual à quantidade de dados
                    na maior aposta (por exemplo: 4 BAGOS e 8 SENAS, 5 BAGOS e 9 QUADRAS).
                 */
                bid.setRegistered(true);
            } else if (bidType > higherNormalBidType && bidQuantity >= higherNormalBidQuantity) {
                 /*
                A aposta a ser registrada:
                    - É normal mas em um valor "maior" que o da maior aposta normal (por exemplo: a
                aposta a ser registrada é em SENA e a maior aposta normal em QUADRA);
                    - Tem o número de dados maior ou igual que o da maior aposta normal.
                 */
                bid.setRegistered(true);
            } else if (bidType <= higherNormalBidType && bidQuantity > higherNormalBidQuantity) {
                /*
                A aposta a ser registrada:
                    - É normal mas em um valor "menor ou igual" que o da maior aposta normal (por exemplo: a
                aposta a ser registrada é em DUQUE e a maior aposta normal em QUINA);
                    - Tem o número de dados maior que o da maior aposta normal.
                 */
                bid.setRegistered(true);
            }
        } else if (higherNormalBidQuantity < 2*higherBagoBidQuantity) {
            /*
            Se a quantidade de dados na maior aposta normal é menor que o dobro da quantidade de dados na
            maior aposta de bagos (por exemplo: 5 BAGOS e 4 QUINAS)
             */
            if (bidIsBago && bidQuantity > higherBagoBidQuantity){
                /*
                A aposta a ser registrada:
                    - É em BAGOS;
                    - Ela tem mais BAGOS que na maior aposta de BAGOS.
                 */
                bid.setRegistered(true);
            } else if (!bidIsBago && bidQuantity >= 2* higherBagoBidQuantity) {
                /*
                A aposta a ser registrada:
                    - É normal;
                    - Ela tem mais ou igual número de dados que o da maior aposta de BAGOS.
                 */
                bid.setRegistered(true);
            }
        }
        if(!bid.isRegistered()){
            bid.setUnregisteredReason("Aposta inválida. O valor apostado não é maior que a última aposta ou já foi utilizado.");
        }
    }

    private Bid getHigherBagoBid() {
        Bid higherBagoBid = new Bid(DiceValues.BAGO, 0, 0L);
        for (Bid bid : match.getBetHistory()) {
            int bidQuantity = bid.getQuantity();
            boolean bidIsBago = bid.getType() == DiceValues.BAGO;
            if(bidIsBago && bidQuantity > higherBagoBid.getQuantity()){
                higherBagoBid = bid;
            }
        }
        return higherBagoBid;
    }

    private Bid getHigherNormalBid() {
        Bid higherNormalBid = new Bid(DiceValues.DUQUE, 0, 0L);
        for (Bid bid : match.getBetHistory()) {
            boolean bidIsBago = bid.getType() == DiceValues.BAGO;
            if(!bidIsBago){
                higherNormalBid = bid;
            }
        }
        return higherNormalBid;
    }
    
//    private Long getNextPlayer(Long idJogador,String order) {
//        int playerPosition = 0;
//        for (Player player : match.getPlayers()) {
//            if (player.getId() == idJogador) {
//                break;
//            }
//            playerPosition += 1;
//        }
//        if (order == "top-down" && playerPosition < match.getNumberOfPlayer() - 1) {
//            return match.getPlayers().get(playerPosition + 1).getId();
//        } else if (order == "top-down") {
//            return match.getPlayers().get(0).getId();
//        } else if (order == "bottom-up" && playerPosition > 0) {
//            return match.getPlayers().get(playerPosition - 1).getId();
//        } else {
//            return match.getPlayers().get(Math.toIntExact(match.getNumberOfPlayer() - 1L)).getId();
//        }
//    }

    public void iniciarNovoTurno() {
        this.match.getBetHistory().clear();
        this.match.getTotalDicesMatch();
        // gerarDadosProsPlayers (respeitando a quatde de dados de cada Player)
        // informar de quem é a vez ( quem perdeu dado recomeça o turno)
    }

    public Match getMatch() {
        return match;
    }
}
