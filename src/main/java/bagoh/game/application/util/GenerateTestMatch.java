package bagoh.game.application.util;

import bagoh.game.application.domain.Player;
import bagoh.game.application.domain.service.implementation.DefaultMatchService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateTestMatch {

    private GenerateTestMatch() {}
    public static DefaultMatchService gerarRodadaTeste() {
        DefaultMatchService service = new DefaultMatchService();

        Player player1 = new Player(4, "Carniça");
        int[] dices1 = {1, 1, 1, 1, 0, 0};
        List<Integer> battleDices1 = Arrays.asList(1,2,3);
        player1.getDices().setBattleDice(battleDices1);
        player1.getDices().setDicesQuantities(dices1);
        player1.setId(1L);

        Player player2 = new Player(4, "Fio");
        int[] dices2 = {0, 1, 1, 1, 1, 0};
        List<Integer> battleDices2 = Arrays.asList(1,2,3);
        player2.getDices().setBattleDice(battleDices2);
        player2.getDices().setDicesQuantities(dices2);
        player2.setId(2L);

        Player player3 = new Player(4, "B1");
        int[] dices3 = {0, 0, 1, 1, 2, 0};
        List<Integer> battleDices3 = Arrays.asList(1,2,5);
        player3.getDices().setBattleDice(battleDices3);
        player3.getDices().setDicesQuantities(dices3);
        player3.setId(3L);

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        service.inicializarPartida(players, 12);
        service.getMatch().setId(1234L);

        return service;
    }

}
