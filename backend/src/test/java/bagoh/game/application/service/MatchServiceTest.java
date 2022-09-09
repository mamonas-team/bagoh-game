package bagoh.game.application.service;

import bagoh.game.application.dto.domainDto.Bid;
import bagoh.game.application.dto.domainDto.DiceValues;
import bagoh.game.application.dto.domainDto.Match;
import bagoh.game.application.dto.domainDto.Player;
import bagoh.game.application.service.implementation.DefaultMatchService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MatchServiceTest {

    @Test
    void shouldValidateBidValidatorWrongQuantityBagoh() {
        DefaultMatchService service = gerarRodadaTeste();
        Bid bid = new Bid(DiceValues.BAGO,2,2L);
        service.registrarAposta(bid);
        boolean validation = service.validarAposta();
        Assertions.assertFalse(validation);
    }

    @Test
    void shouldValidateBidValidatorWrongQuantityNormal() {
        DefaultMatchService service = gerarRodadaTeste();
        Bid bid = new Bid(DiceValues.SENA,1,2L);
        service.registrarAposta(bid);
        boolean validation = service.validarAposta();
        Assertions.assertFalse(validation);
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
