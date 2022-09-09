package bagoh.game.application.service;

import bagoh.game.application.dto.domainDto.Bid;
import bagoh.game.application.dto.domainDto.Match;
import bagoh.game.application.dto.domainDto.Player;

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
     * @param bid aposta a ser validada.
     * @return A aposta com sua validade atualizada e se for inválida com o motivo da invalidex no atributo invalidReason
     */
    Bid registrarAposta(Bid bid);


    /**
     * Este método é responsável por validar a última aposta.
     * @return true se a aposta for verdadeira ou false caso contrario
     */
    boolean validarAposta();

}
