package bagoh.game.application.service;

import bagoh.game.application.dto.Match;
import bagoh.game.application.dto.Player;

import java.util.List;

public class MatchService {

    private Match match;
    private List<Player> players;


    public MatchService(Match match) {
        this.match = match;
        this.players = match.getPlayers();
    }

    public void inicializarJogadores(){
        Long numberOfPlayers = match.getNumberOfPlayer();

        for (int i = 1; i <= numberOfPlayers; i++){
            String name = "Jogador-" + i;
            Player player = new Player(match.getNumberOfInitialDices(), name);
            players.add(player);
        }
    }

    public void printPlayers(){
        for (Player player : players){
            System.out.println(player.getName());
        }
    }

    @B1
    public void gerarDadosJogadores(){
        // popular players com numeros aleatorios, pra cada jogador.
    };

    @Dadalt
    public void definirPrimeiroJogador(){
        // gerar um numero aleatorio de 1 a 6 pra cada player
        // ver qual o maior e definir PrimeiroJogador
        // Definir PrimeiroJogador como playerOfTheTurn.
    };

    @Grosso
    public void fazerAposta(Long idJogador, Aposta aposta){
        //validar que a aposta é maior do que a ultima aposta
        //informar de qual jogador é a vez de jogar
    }

    @Carnica
    public void duvidarDeAposta (){
        // validar se a aposta é verdadeira
        // informar quem venceu a aposta (quem apostou ou quem duvidou)
        // remover um dado do perdedor
        // iniciarNovoTurno()
    }

    public void iniciarNovoTurno(){
        // gerarDadosProsPlayers (respeitando a quatde de dados de cada Player)
        // informar de quem é a vez ( quem perdeu dado recomeça o turno)
    }


















}
