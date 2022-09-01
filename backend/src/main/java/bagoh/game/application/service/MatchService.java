package bagoh.game.application.service;

import bagoh.game.application.dto.domainDto.Bid;
import bagoh.game.application.dto.domainDto.Match;
import bagoh.game.application.dto.domainDto.Player;
import org.springframework.stereotype.Service;

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
     * Esté método é responsável por gerar dados pra seus jogadores.
     * @param gerarPrimeiroDado Indica se deve gerar o primeiro dado de cada jogador.
     * @return Retorna uma lista de Players com dados gerados.
     */
    List<Player> gerarDadosJogadores(boolean gerarPrimeiroDado);


    /**
     * Este método é responsável por registrar as apostas que são feitas na partida.
     * @param bid Aposta feita pelo idPlayer.
     * @param idPlayer Player que fez a aposta.
     * @return Retorna o status da Partida.
     */
    String registrarAposta(Bid bid, Long idPlayer);


    /**
     * Este método é responsável por validar uma aposta.
     * @param idJogador
     * @return Retorna true se a aposta é verdadeira, e false se é falsa.
     */
    Match validarAposta(Long idJogador);

}
