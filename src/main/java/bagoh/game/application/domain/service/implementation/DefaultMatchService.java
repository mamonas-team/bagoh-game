package bagoh.game.application.domain.service.implementation;

import bagoh.game.application.domain.DiceValues;
import bagoh.game.application.domain.Match;
import bagoh.game.application.domain.Player;
import bagoh.game.application.domain.Bid;
import bagoh.game.application.domain.service.MatchService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public Bid saveBid(Bid bid) {
        validateIfPlayerExists(bid);
        validateIfQuantityAndValuesAreValid(bid);
        validateIfBidIsBiggerThanLastBid(bid);

        if (bid.isAllowed()){
            this.match.addBidInBetHistory(bid);
            this.match.setLastBid(bid);
        }

        return bid;
    }

    @Override
    public Boolean validateIfBidIsTrue() {
        int[] totalOfEachDiceValueInGame = match.countDicesInTurn();
        int bagosInGame = totalOfEachDiceValueInGame[0];

        Bid bid = match.getLastBid();
        DiceValues bidValue = bid.getValue();
        int bidQuantity = bid.getQuantity();
        int value = bidValue.getNumericType() -1; // -1 pois o vetor começa na posição 0, logo os bagos estão na posição 0 e as senas na posição 5
        int totalBidValueInGame = totalOfEachDiceValueInGame[value];

        if (bidValue == DiceValues.BAGO){
            bid.setTrue(bidQuantity <= bagosInGame);
        } else {
            if (totalBidValueInGame == 0){
                bid.setTrue(false);
            } else {
                bid.setTrue(bidQuantity <= bagosInGame + totalBidValueInGame);
            }
        }

        match.setLastBid(bid);
        return bid.isTrue();
    }


    private void validateIfPlayerExists(Bid bid) {
        bid.setAllowed(false);
        bid.setUnallowedBidReason("Não foi possível validar o jogador que fez a aposta.");

        Long idPlayer = bid.getIdPlayer();

        // valida se o jogador pertence a lista de jogadores da partida
        for (Player player : match.getPlayers()) {
            if (Objects.equals(player.getId(), idPlayer)) {
                bid.setAllowed(true);
                return;
            }
        }

        bid.setUnallowedBidReason("Jogador não identificado, id inexistente.");

    }

    private void validateIfQuantityAndValuesAreValid(Bid bid) {
        if (bid.isAllowed()) {
            int bidQuantity = bid.getQuantity();
            int bidType = bid.getValue().getNumericType();
            int bago = 1;
            int sena = 6;

            if (bidQuantity > match.getTotalDicesMatch()){
                bid.setUnallowedBidReason("Aposta maior que a quantidade de dados na mesa.");
                bid.setAllowed(false);
            } else if (bidQuantity <= 0 || bidType < bago || bidType > sena){
                bid.setUnallowedBidReason("Aposta inválida. Os valores dos dados vão de 1 a 6 apenas.");
                bid.setAllowed(false);
            }
        }
    }

    private void validateIfBidIsBiggerThanLastBid(Bid bid) {
        if (bid.isAllowed()) {
            int bidQuantity = bid.getQuantity();
            int bidType = bid.getValue().getNumericType();
            boolean bidIsBago = bid.getValue() == DiceValues.BAGO;
            Bid lastBagoBid = getLastBagoBid(); // Ultima (maior) aposta em bago
            Bid lastNormalBid = getLastNormalBid(); // Ultima (maior) aposta sem ser em bago (normal)

            int lastNormalBidQuantity = lastNormalBid.getQuantity();
            int lastNormalBidValue = lastNormalBid.getValue().getNumericType();
            int lastBagoBidQuantity = lastBagoBid.getQuantity();

            bid.setAllowed(false);

            // Se a qtde de dados da ultima aposta normal é exatamente o dobro da qtde de dados da
            // ultima aposta em bago
            if (lastNormalBidQuantity == 2*lastBagoBidQuantity){

                // Se a aposta é em bago E
                // A qtde apostada for maior que a ultima aposta em bago
                boolean itsABagoBidAndIsAllowed = bidIsBago && bidQuantity > lastBagoBidQuantity;
                // Se a aposta não é normal, mas é em um valor maior que o da ultima aposta normal E
                // Tem o número de dados maior ou igual que o da ultima aposta normal
                boolean itsANormalBidHigherTypeAndIsAllowed = bidType > lastNormalBidValue && bidQuantity >= lastNormalBidQuantity;
                // Se a aposta é normal mas em um valor menor ou igual que o da ultima aposta normal E
                // Tem o número de dados maior que o da ultima aposta normal
                boolean itsANormalBidLowerTypeAndIsAllowed = bidType <= lastNormalBidValue && bidQuantity > lastNormalBidQuantity;
                if (itsABagoBidAndIsAllowed || itsANormalBidHigherTypeAndIsAllowed || itsANormalBidLowerTypeAndIsAllowed){
                    bid.setAllowed(true);
                }

                //Se a qtde de dados da ultima aposta normal é maior que o dobro da qtde de dados da ultima aposta em bago
            } else if (lastNormalBidQuantity > 2*lastBagoBidQuantity) {

                // Se a aposta é em bago E
                // O dobro da qtde de dados da atual aposta é maior ou igual à qtde de dados na ultima aposta
                boolean itsABagoBidAndIsAllowed = bidIsBago && bidQuantity >= lastNormalBidQuantity/2;
                // Se a aposta é normal mas em um valor maior que o da ultima aposta normal E
                // Tem o número de dados maior ou igual que o da ultima aposta normal
                boolean itsANormalBidHigherTypeAndIsAllowed = bidType > lastNormalBidValue && bidQuantity >= lastNormalBidQuantity;
                // Se a aposta é normal mas em um valor menor ou igual que o da ultima aposta normal E
                // Tem o número de dados maior que o da ultima aposta normal
                boolean itsANormalBidLowerTypeAndIsAllowed = bidType <= lastNormalBidValue && bidQuantity > lastNormalBidQuantity;
                if (itsABagoBidAndIsAllowed || itsANormalBidHigherTypeAndIsAllowed || itsANormalBidLowerTypeAndIsAllowed){
                    bid.setAllowed(true);
                }

                // Se a qtde de dados da ultima aposta normal é menor que o dobro da qtde de dados da ultima aposta em bago
            } else if (lastNormalBidQuantity < 2*lastBagoBidQuantity) {

                // Se a aposta é em bago E
                // Ela tem mais bago que na ultima aposta em bago
                boolean itsABagoBidAndIsAllowed = bidIsBago && bidQuantity > lastBagoBidQuantity;
                // Se a aposta é normal E
                // Ela tem mais ou mesmo que o dobro de número de dados da ultima aposta em bago
                boolean itsANormalBidAndIsAllowed = !bidIsBago && bidQuantity >= 2* lastBagoBidQuantity;
                if (itsABagoBidAndIsAllowed || itsANormalBidAndIsAllowed) {
                    bid.setAllowed(true);
                }
            }

            if(!bid.isAllowed()){
                bid.setUnallowedBidReason("Aposta inválida. A aposta atual não é maior que a última aposta feita.");
            }
        }
    }

    private Bid getLastBagoBid() {
        Bid higherBagoBid = new Bid(DiceValues.BAGO, 0, 0L);
        for (Bid bid : match.getBetHistory()) {
            int bidQuantity = bid.getQuantity();
            boolean bidIsBago = bid.getValue() == DiceValues.BAGO;
            if(bidIsBago && bidQuantity > higherBagoBid.getQuantity()){
                higherBagoBid = bid;
            }
        }
        return higherBagoBid;
    }

    private Bid getLastNormalBid() {
        Bid higherNormalBid = new Bid(DiceValues.DUQUE, 0, 0L);
        for (Bid bid : match.getBetHistory()) {
            boolean bidIsBago = bid.getValue() == DiceValues.BAGO;
            if(!bidIsBago){
                higherNormalBid = bid;
            }
        }
        return higherNormalBid;
    }

    public void iniciarNovoTurno() {
        this.match.getBetHistory().clear();
        this.match.getTotalDicesMatch();
        this.match.resetPlayersDices();
        // gerarDadosProsPlayers (respeitando a quatde de dados de cada Player)
        // informar de quem é a vez ( quem perdeu dado recomeça o turno)
    }

    public void printPlayers() {
        List<Player> players = match.getPlayers();
        for (Player player : players) {
            System.out.println(player.getName());
        }
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
