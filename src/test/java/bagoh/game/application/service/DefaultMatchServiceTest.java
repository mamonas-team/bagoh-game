package bagoh.game.application.service;

import bagoh.game.application.domain.Bid;
import bagoh.game.application.domain.DiceValues;
import bagoh.game.application.domain.Player;
import bagoh.game.application.domain.service.implementation.DefaultMatchService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DefaultMatchServiceTest {

    @Test
    void shouldValidateBidRecorderLastBid() {
        DefaultMatchService service = gerarRodadaTeste();
        Bid bid1 = new Bid(DiceValues.BAGO,2,1L);
        service.saveBid(bid1);
        Bid bid2 = new Bid(DiceValues.QUINA,4,2L);
        service.saveBid(bid2);
        Bid bid3 = new Bid(DiceValues.SENA,4,3L);
        service.saveBid(bid3);
        Assertions.assertSame(bid3, service.getMatch().getLastBid());
    }

    @Test
    void shouldValidateBidRecorderBidHistorySize() {
        DefaultMatchService service = gerarRodadaTeste();
        Bid bid1 = new Bid(DiceValues.BAGO,2,1L);
        service.saveBid(bid1);
        Bid bid2 = new Bid(DiceValues.QUINA,4,2L);
        service.saveBid(bid2);
        Bid bid3 = new Bid(DiceValues.SENA,4,3L);
        service.saveBid(bid3);
        Assertions.assertEquals(3,service.getMatch().getBetHistory().size());
    }

    @Test
    void shouldValidateBidRecorderBidHistoryLastElement() {
        DefaultMatchService service = gerarRodadaTeste();
        Bid bid1 = new Bid(DiceValues.BAGO,2,1L);
        service.saveBid(bid1);
        Bid bid2 = new Bid(DiceValues.QUINA,4,2L);
        service.saveBid(bid2);
        Bid bid3 = new Bid(DiceValues.SENA,4,3L);
        service.saveBid(bid3);
        List<Bid> bidHistory = service.getMatch().getBetHistory();
        Assertions.assertSame(bid3, bidHistory.get(bidHistory.size()-1));
    }

    @Test
    void shouldValidateBidRecorderLowBidRegistration() {
        DefaultMatchService service = gerarRodadaTeste();
        Bid bid1 = new Bid(DiceValues.BAGO,2,1L);
        service.saveBid(bid1);
        Bid bid2 = new Bid(DiceValues.QUINA,4,2L);
        service.saveBid(bid2);
        Bid bid3 = new Bid(DiceValues.SENA,3,3L);
        service.saveBid(bid3);
        Assertions.assertFalse(bid3.isAllowed());
    }

    @Test
    void shouldValidateBidRecorderLowBidMessage() {
        DefaultMatchService service = gerarRodadaTeste();
        Bid bid1 = new Bid(DiceValues.BAGO,2,1L);
        service.saveBid(bid1);
        Bid bid2 = new Bid(DiceValues.QUINA,4,2L);
        service.saveBid(bid2);
        Bid bid3 = new Bid(DiceValues.SENA,3,3L);
        service.saveBid(bid3);
        String unregisteredReason = "Aposta inválida. A aposta atual não é maior que a última aposta feita.";
        Assertions.assertEquals(unregisteredReason,bid3.getUnallowedBidReason());
    }

    @Test
    void shouldValidateBidRecorderWrongPlayerIdRegistration() {
        DefaultMatchService service = gerarRodadaTeste();
        Bid bid1 = new Bid(DiceValues.BAGO,2,1L);
        service.saveBid(bid1);
        Bid bid2 = new Bid(DiceValues.QUINA,4,2L);
        service.saveBid(bid2);
        Bid bid3 = new Bid(DiceValues.SENA,4,-3L);
        service.saveBid(bid3);
        Assertions.assertFalse(bid3.isAllowed());
    }

    @Test
    void shouldValidateBidRecorderWrongPlayerIdMessage() {
        DefaultMatchService service = gerarRodadaTeste();
        Bid bid1 = new Bid(DiceValues.BAGO,2,1L);
        service.saveBid(bid1);
        Bid bid2 = new Bid(DiceValues.QUINA,4,2L);
        service.saveBid(bid2);
        Bid bid3 = new Bid(DiceValues.SENA,4,-3L);
        service.saveBid(bid3);
        String unregisteredReason = "Jogador não identificado, id inexistente.";
        Assertions.assertEquals(unregisteredReason,bid3.getUnallowedBidReason());
    }

    @Test
    void shouldValidateBidRecorderWrongDicesQuantityRegistration() {
        DefaultMatchService service = gerarRodadaTeste();
        Bid bid1 = new Bid(DiceValues.BAGO,2,1L);
        service.saveBid(bid1);
        Bid bid2 = new Bid(DiceValues.QUINA,4,2L);
        service.saveBid(bid2);
        Bid bid3 = new Bid(DiceValues.SENA,13,3L);
        service.saveBid(bid3);
        Assertions.assertFalse(bid3.isAllowed());
    }

    @Test
    void shouldValidateBidRecorderWrongDicesQuantityMessage() {
        DefaultMatchService service = gerarRodadaTeste();
        Bid bid1 = new Bid(DiceValues.BAGO,2,1L);
        service.saveBid(bid1);
        Bid bid2 = new Bid(DiceValues.QUINA,4,2L);
        service.saveBid(bid2);
        Bid bid3 = new Bid(DiceValues.SENA,13,3L);
        service.saveBid(bid3);
        String unregisteredReason = "Aposta maior que a quantidade de dados na mesa.";
        Assertions.assertEquals(unregisteredReason,bid3.getUnallowedBidReason());
    }

    @Test
    void shouldValidateBidRecorderNegativeDicesQuantityRegistration() {
        DefaultMatchService service = gerarRodadaTeste();
        Bid bid1 = new Bid(DiceValues.BAGO,2,1L);
        service.saveBid(bid1);
        Bid bid2 = new Bid(DiceValues.QUINA,4,2L);
        service.saveBid(bid2);
        Bid bid3 = new Bid(DiceValues.SENA,-4,3L);
        service.saveBid(bid3);
        Assertions.assertFalse(bid3.isAllowed());
    }

    @Test
    void shouldValidateBidRecorderNegativeDicesQuantityMessage() {
        DefaultMatchService service = gerarRodadaTeste();
        Bid bid1 = new Bid(DiceValues.BAGO,2,1L);
        service.saveBid(bid1);
        Bid bid2 = new Bid(DiceValues.QUINA,4,2L);
        service.saveBid(bid2);
        Bid bid3 = new Bid(DiceValues.SENA,-4,3L);
        service.saveBid(bid3);
        String unregisteredReason = "Aposta inválida. Os valores dos dados vão de 1 a 6 apenas.";
        Assertions.assertEquals(unregisteredReason,bid3.getUnallowedBidReason());
    }

    @Test
    void shouldValidateBidValidatorWrongQuantityBagoh() {
        DefaultMatchService service = gerarRodadaTeste();
        Bid bid = new Bid(DiceValues.BAGO,2,1L);
        service.saveBid(bid);
        boolean validation = service.validateIfBidIsTrue();
        Assertions.assertFalse(validation);
    }


    @Test
    void shouldValidateBidValidatorWrongQuantityNormal() {
        DefaultMatchService service = gerarRodadaTeste();
        Bid bid = new Bid(DiceValues.SENA,1,2L);
        service.saveBid(bid);
        boolean validation = service.validateIfBidIsTrue();
        Assertions.assertFalse(validation);
    }

    @Test
    void shouldValidateBidValidatorRightQuantityBagoh() {
        DefaultMatchService service = gerarRodadaTeste();
        Bid bid = new Bid(DiceValues.BAGO,1,3L);
        service.saveBid(bid);
        boolean validation = service.validateIfBidIsTrue();
        Assertions.assertTrue(validation);
    }

    @Test
    void shouldValidateBidValidatorRightQuantityNormal() {
        DefaultMatchService service = gerarRodadaTeste();
        Bid bid = new Bid(DiceValues.QUADRA,4,1L);
        service.saveBid(bid);
        boolean validation = service.validateIfBidIsTrue();
        Assertions.assertTrue(validation);
    }

    DefaultMatchService gerarRodadaTeste() {
        DefaultMatchService service = new DefaultMatchService();
        Player player1 = new Player(4,"Carniça");
        int[] dices1 = {1, 1, 1, 1, 0, 0};
        player1.getDices().setDicesQuantities(dices1);
        player1.setId(1L);
        Player player2 = new Player(4,"Fio");
        int[] dices2 = {0, 1, 1, 1, 1, 0};
        player2.getDices().setDicesQuantities(dices2);
        player2.setId(2L);
        Player player3 = new Player(4,"B1");
        int[] dices3 = {0, 0, 1, 1, 2, 0};
        player3.getDices().setDicesQuantities(dices3);
        player3.setId(3L);
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        service.inicializarPartida(players,12);
        return service;
    }

}
