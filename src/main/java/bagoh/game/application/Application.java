package bagoh.game.application;

import bagoh.game.application.domain.Player;
import bagoh.game.application.domain.service.implementation.DefaultMatchService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		Player nairon = new Player(3, "Nairon");
		Player grosso = new Player(3, "Grosso");
		Player dadalt = new Player(3, "Dadalt");
		Player carnica = new Player(3, "Carnica");
		Player b1 = new Player(3, "B1");
		Player samuka = new Player(3, "Samuka");

		List<Player> mamonas = new ArrayList<>();
		mamonas.add(nairon);
		mamonas.add(grosso);
		mamonas.add(dadalt);
		mamonas.add(carnica);
		mamonas.add(b1);
		mamonas.add(samuka);

		DefaultMatchService matchService = new DefaultMatchService();

		matchService.inicializarPartida(mamonas, 3);
		matchService.gerarDadosJogadores(true);
		matchService.printPlayers();
	}
}