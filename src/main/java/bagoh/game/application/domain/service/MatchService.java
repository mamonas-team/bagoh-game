package bagoh.game.application.domain.service;

import bagoh.game.application.domain.Bid;
import bagoh.game.application.domain.Match;
import bagoh.game.application.domain.Player;

import java.util.List;

/**
 * Esta interface é responsável pelas regras de negócio do jogo
 * Aqui estão as funcionalidades core, o domínio do projeto
 */

public interface MatchService {

    /**
     * Este método é responsável por inicializar uma partida de Bagoh.
     * Ou seja, definir quem são os jogadores, e quantos dados têm na partida.
     * @param players Lista de jogadores.
     * @param totalDados Total de dados de cada jogador.
     * @return Retorna uma partida inicializada.
     */
     Match inicializarPartida(List<Player> players, int totalDados);


    /**
     * Este método é responsável por gerar dados pra seus jogadores.
     * @param gerarPrimeiroDado Indica se deve gerar o primeiro dado de cada jogador.
     * @return Retorna uma lista de Players com dados gerados.
     */
    List<Player> gerarDadosJogadores(boolean gerarPrimeiroDado);


    /**
     * Este método é responsável por registrar as apostas que são feitas na partida.
     * @param bid Aposta a ser validada.
     * @return Retorna uma Aposta com status validada/invalidada e o motivo da invalidez.
     */
    Bid saveBid(Bid bid);


    /**
     * Este método é responsável por validar a última aposta.
     * @return Retorna true se a aposta for verdadeira ou false caso contrário
     */
    Boolean validateIfBidIsTrue();

}
