package bagoh.game.application;

import bagoh.game.application.dto.Match;
import bagoh.game.application.service.MatchService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Application {
	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

		//Instancia de uma partida
		Match match1 = new Match(5L, 3L);


		//Instancia de um MatchService
		MatchService matchService = new MatchService(match1);
		matchService.inicializarJogadores();
		System.out.println("Jogadores na partida: ");
		matchService.printPlayers();

		//Teste se o método está retornando certo o próximo jogador
		matchService.iniciarNovoTurno();
		System.out.println("\n Primeiro a jogar: jogador-1");
		Long proximo = matchService.fazerAposta(1L, new Long[]{2L, 1L},"cima-baixo");
		System.out.println("aposta de 2 bagos passou. O próximo jogador é jogador-"+proximo);
		proximo = matchService.fazerAposta(proximo, new Long[]{4L, 3L},"cima-baixo");
		System.out.println("aposta de 4 ternas passou. O próximo jogador é jogador-"+proximo);
		proximo = matchService.fazerAposta(proximo, new Long[]{4L, 6L},"cima-baixo");
		System.out.println("aposta de 4 senas passou. O próximo jogador é jogador-"+proximo);
		proximo = matchService.fazerAposta(proximo, new Long[]{5L, 2L},"cima-baixo");
		System.out.println("aposta de 5 duques passou. O próximo jogador é jogador-"+proximo);
		proximo = matchService.fazerAposta(proximo, new Long[]{3L, 1L},"cima-baixo");
		System.out.println("aposta de 3 bagos passou. O próximo jogador é jogador-"+proximo);

		matchService.iniciarNovoTurno();
		System.out.println("\n\n Na ordem contrária agora:");
		System.out.println("Primeiro: jogador-5");
		proximo = matchService.fazerAposta(5L, new Long[]{2L, 1L},"baixo-cima");
		System.out.println("aposta de 2 bagos passou. O próximo jogador é jogador-"+proximo);
		proximo = matchService.fazerAposta(proximo, new Long[]{4L, 3L},"baixo-cima");
		System.out.println("aposta de 4 ternas passou. O próximo jogador é jogador-"+proximo);
		proximo = matchService.fazerAposta(proximo, new Long[]{4L, 6L},"baixo-cima");
		System.out.println("aposta de 4 senas passou. O próximo jogador é jogador-"+proximo);
		proximo = matchService.fazerAposta(proximo, new Long[]{5L, 2L},"baixo-cima");
		System.out.println("aposta de 5 duques passou. O próximo jogador é jogador-"+proximo);
		proximo = matchService.fazerAposta(proximo, new Long[]{3L, 1L},"baixo-cima");
		System.out.println("aposta de 3 bagos passou. O próximo jogador é jogador-"+proximo);
		proximo = matchService.fazerAposta(proximo, new Long[]{6L, 4L},"baixo-cima");
		System.out.println("aposta de 6 quadras passou. O próximo jogador é jogador-"+proximo);
		proximo = matchService.fazerAposta(proximo, new Long[]{6L, 5L},"baixo-cima");
		System.out.println("aposta de 6 quinas passou. O próximo jogador é jogador-"+proximo);
		proximo = matchService.fazerAposta(proximo, new Long[]{4L, 1L},"baixo-cima");
		System.out.println("aposta de 4 bagos passou. O próximo jogador é jogador-"+proximo);
		proximo = matchService.fazerAposta(proximo, new Long[]{5L, 1L},"baixo-cima");
		System.out.println("aposta de 5 bagos passou. O próximo jogador é jogador-"+proximo);

		System.out.println("\n\n Testando alguns erros: ");
		try{
			System.out.println("Fazendo uma aposta repetida (5 bagos):");
			proximo = matchService.fazerAposta(proximo, new Long[]{5L, 1L},"baixo-cima");
		} catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
		}

		try{
			System.out.println("Fazendo uma aposta menor (9 senas):");
			proximo = matchService.fazerAposta(proximo, new Long[]{9L, 6L},"baixo-cima");
		} catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
		}

		try{
			System.out.println("Fazendo uma aposta de 20 dados (o total é de 15 na mesa):");
			proximo = matchService.fazerAposta(proximo, new Long[]{20L, 6L},"baixo-cima");
		} catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
		}

		try{
			System.out.println("Fazendo uma aposta inválida (-4 bagos):");
			proximo = matchService.fazerAposta(proximo, new Long[]{-3L, 1L},"baixo-cima");
		} catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
		}

		try{
			System.out.println("Fazendo uma aposta inválida (8 faces 7):");
			proximo = matchService.fazerAposta(proximo, new Long[]{3L, 7L},"baixo-cima");
		} catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
		}

		try{
			System.out.println("Fazendo uma aposta inválida (8 faces -2):");
			proximo = matchService.fazerAposta(proximo, new Long[]{3L, -2L},"baixo-cima");
		} catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
		}

	}
}
