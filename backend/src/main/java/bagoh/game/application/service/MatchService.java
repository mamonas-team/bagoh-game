package bagoh.game.application.service;

import bagoh.game.application.dto.Match;
import bagoh.game.application.dto.Player;

import java.util.List;

public class MatchService {

    private Match match;
    private List<Player> players;
    private Long[][] betHistory = new Long[7][2];


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
    public Long fazerAposta(Long idJogador, Long[2] aposta, String ordem){
        //vai dar erro se o nro de jogadores n for igual match.getNumberOfPlayer
        //vai dar erro se iniciarNovoTurno n for chamada antes
        long totalNumberOfDices = 0L;
        boolean validBid = false;
        for (Player player : players){
            totalNumberOfDices += player.getNumberOfDices;
        }
        if (aposta[0] > totalNumberOfDices){
            return -1L; //nro de dados na aposta maior que o nro de dados na mesas
        } else if (aposta[0] <= 0L || aposta[1] < 1L || aposta[1] > 6L){
            return -1L; //aposta invalida
        }
        Long[2] higherBagohBid = {0L,1L};
        Long[2] higherNormalBid = {0L,0L};
        for (int i = 0; i<betHistory.length() ; i++){
            if (betHistory[i][1] == 1L && betHistory[i][0] > higherBagohBid[0]) {
                higherBagohBid = betHistory[i];
            }else if (betHistory[i][1] != 1L && betHistory[i][0] >= higherNormalBid[0]){
                if (betHistory[i][0] > higherNormalBid[0]) {
                    higherNormalBid = betHistory[i];
                } else if (betHistory[i][1] > higherNormalBid[1]) {
                    higherNormalBid = betHistory[i];
                }
            }
        }
        if (higherNormalBid[0] == 2*higherBagohBid[0]){
            if (aposta[1] == 1L && aposta[0] > higherBagohBid[0]){
                validBid = true;
            } else if (aposta[1] > higherNormalBid[1] && aposta[0] >= higherNormalBid[0]) {
                validBid = true;
            } else if (aposta[1] <= higherNormalBid[1] && aposta[0] > higherNormalBid[0]){
                validBid = true;
            }
        } else if (higherNormalBid[0] > 2*higherBagohBid[0]) {
            if (aposta[1] == 1L && aposta[0] >= Math.ceil(higherNormalBid[0]/2)){
                validBid = true;
            } else if (aposta[1] > higherNormalBid[1] && aposta[0] >= higherNormalBid[0]) {
                validBid = true;
            } else if (aposta[1] <= higherNormalBid[1] && aposta[0] > higherNormalBid[0]){
                validBid = true;
            }
        } else if (higherNormalBid[0] < 2*higherBagohBid[0]) {
            if (aposta[1] == 1L && aposta[0] > higherBagohBid[0]){
                validBid = true;
            } else if (aposta[1] != 1L && aposta[0] >= 2*higherBagohBid[0]) {
                validBid = true;
            }
        }
        if (validBid){
            int posicao = 0;
            for (Player player : players){
                if (player.getId() == idJogador){
                    break
                };
                i += 1;
            }
            if (ordem == 'cima-baixo' && i < match.getNumberOfPlayer()-1){
                return players[i+1].getId();
            } else if (ordem == 'cima-baixo') {
                return players[0].getId();
            } else if (ordem == 'baixo-cima' && i > 0) {
                return players[i-1].getId();
            } else{
                return players[match.getNumberOfPlayer()-1].getId();
            }
        } else {
            return -1L //aposta invalida
        }
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
        Arrays.fill(betHistory, 0);
    }


















}
