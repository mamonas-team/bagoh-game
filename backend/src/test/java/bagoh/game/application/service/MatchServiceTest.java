package bagoh.game.application.service;

import org.junit.jupiter.api.Test;

public class MatchServiceTest {

    @Test
    void shouldValidateBidValidator(){
        //		//Instancia de uma partida
//		Match match1 = new Match(5, 3);
//
//
//		//Instancia de um MatchService
//		DefaultMatchService matchService = new DefaultMatchService();
//		matchService.inicializarPartida(5, 3);
//		matchService.inicializarJogadores();
//		System.out.println("Jogadores na partida: ");
//		matchService.printPlayers();
//
//		//Teste se o método está retornando certo o próximo jogador
//		matchService.iniciarNovoTurno();
//		Bid bid1 = new Bid(DiceValues.BAGO,2,1L);
//		Long proximo = matchService.fazerAposta(1L, bid1,"top-down");
//
//		Bid bid2 = new Bid(DiceValues.TERNO,4, proximo);
//		proximo = matchService.fazerAposta(proximo, bid2,"top-down");
//
//		Bid bid3 = new Bid(DiceValues.SENA,4, proximo);
//		proximo = matchService.fazerAposta(proximo, bid3,"top-down");
//
//		Bid bid4 = new Bid(DiceValues.DUQUE,5, proximo);
//		proximo = matchService.fazerAposta(proximo, bid4,"top-down");
//
//		Bid bid5 = new Bid(DiceValues.BAGO,3, proximo);
//		proximo = matchService.fazerAposta(proximo, bid5,"top-down");
//
//		Bid bid6 = new Bid(DiceValues.QUADRA,6, proximo);
//		proximo = matchService.fazerAposta(proximo, bid6,"top-down");
//
//		Bid bid7 = new Bid(DiceValues.QUINA,6, proximo);
//		proximo = matchService.fazerAposta(proximo, bid7,"top-down");
//
//		Bid bid8 = new Bid(DiceValues.BAGO,4, proximo);
//		proximo = matchService.fazerAposta(proximo, bid8,"top-down");
//
//		Bid bid9 = new Bid(DiceValues.BAGO,5, proximo);
//		proximo = matchService.fazerAposta(proximo, bid9,"top-down");
//
//		matchService.iniciarNovoTurno();
//		System.out.println("\n\n Na ordem contrária agora:");
//		Bid bid10 = new Bid(DiceValues.BAGO,2,1L);
//		proximo = matchService.fazerAposta(1L, bid10,"bottom-up");
//
//		Bid bid20 = new Bid(DiceValues.TERNO,4, proximo);
//		proximo = matchService.fazerAposta(proximo, bid20,"bottom-up");
//
//		Bid bid30 = new Bid(DiceValues.SENA,4, proximo);
//		proximo = matchService.fazerAposta(proximo, bid30,"bottom-up");
//
//		Bid bid40 = new Bid(DiceValues.DUQUE,5, proximo);
//		proximo = matchService.fazerAposta(proximo, bid40,"bottom-up");
//
//		Bid bid50 = new Bid(DiceValues.BAGO,3, proximo);
//		proximo = matchService.fazerAposta(proximo, bid50,"bottom-up");
//
//		Bid bid60 = new Bid(DiceValues.QUADRA,6, proximo);
//		proximo = matchService.fazerAposta(proximo, bid60,"bottom-up");
//
//		Bid bid70 = new Bid(DiceValues.QUINA,6, proximo);
//		proximo = matchService.fazerAposta(proximo, bid70,"bottom-up");
//
//		Bid bid80 = new Bid(DiceValues.BAGO,4, proximo);
//		proximo = matchService.fazerAposta(proximo, bid80,"bottom-up");
//
//		Bid bid90 = new Bid(DiceValues.BAGO,5, proximo);
//		proximo = matchService.fazerAposta(proximo, bid9,"bottom-up");
//
//		System.out.println("\n\n Testando alguns erros: ");
//
//		System.out.println("\nFazendo uma aposta repetida (5 bagos):");
//		Bid bid = new Bid(DiceValues.BAGO,5, proximo);
//		proximo = matchService.fazerAposta(1l, bid,"bottom-up");
//
//		System.out.println("\nFazendo uma aposta menor (9 senas):");
//		bid.setType(DiceValues.SENA);
//		bid.setQuantity(9);
//		proximo = matchService.fazerAposta(1l, bid,"bottom-up");
//
//		System.out.println("\nFazendo uma aposta de 20 dados (o total é de 15 na mesa):");
//		bid.setType(DiceValues.SENA);
//		bid.setQuantity(20);
//		proximo = matchService.fazerAposta(1l, bid,"bottom-up");
//
////		System.out.println("\nFazendo uma aposta inválida (10 dados de face 7):");
////		try{
////			bid.setType(7);
////			bid.setQuantity(10);
////			proximo = matchService.fazerAposta(1l, bid,"bottom-up");
////		} catch (RuntimeException e) {
////			System.out.println(e.getMessage());
////		}
//
//		System.out.println("\npassando um idJogador inexistente (-1L):");
//		bid.setType(DiceValues.SENA);
//		bid.setQuantity(10);
//		proximo = matchService.fazerAposta(-1l, bid,"bottom-up");
//	}
    }
}
