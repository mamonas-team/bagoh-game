package bagoh.game.application;

import bagoh.game.application.dto.Match;
import bagoh.game.application.dto.Player;
import bagoh.game.application.service.MatchService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Application {
	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

		//Instancia de um player
		Player player1 = new Player(3L, "Joaozito");
		System.out.println(player1.getName());


		//Instancia de uma partida
		Match match1 = new Match(3L, 3L);
		System.out.println(match1.getNumberOfPlayer());


		//Instancia de um MatchService
		MatchService matchService = new MatchService(match1);
		matchService.inicializarJogadores();
		matchService.printPlayers();
	}
}
