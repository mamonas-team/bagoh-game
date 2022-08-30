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
     * Este método é responsável por inicializar uma partida de Bagoh
     * Ou seja, definir quem são os jogadores, e quantos dados têm na partida
     */
     Match inicializarPartida();


    /**
     * Esté método é responsável por gerar dados pra seus jogadores
     * @param players
     */
    void gerarDadosJogadores(List<Player> players);

    /**
     * Este método é responsável por conduzir o jogo
     * Informando os acontecimentos, e ditando quem são os jogadores a jogar
     * @return Retorna o id do próximo jogador a jogar ou do campeão
     */
    Long conduzirJogo();

    /**
     * Este método é responsável por registrar as apostas que são feitas na partida
     * @return
     */
    List<Bid> registrarAposta();

    /**
     * Este método é resoponsável por validar uma aposta
     * @param idJogador
     * @param bid
     * @return Retorna true se a aposta é verdadeira, e false se é falsa
     */
    boolean validarAposta(Long idJogador, Bid bid);

    /**
     * Este método é responsável por remover o dado de quem perder uma aposta
     * @param idPlayer
     */
    void removerDado(Long idPlayer);


    /**
     * Este método retorna o status atual da Partida
     * @return
     */
    Match consultarStatusPartida();



}
