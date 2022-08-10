package bagoh.game.application.service;

import bagoh.game.application.dto.Match;
import bagoh.game.application.dto.Player;

import java.util.Arrays;
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
            player.setId(Long.valueOf(i));
            players.add(player);
        }
    }

    public void printPlayers(){
        for (Player player : players){
            System.out.println(player.getName());
        }
    }

    public void gerarDadosJogadores(){
        // popular players com numeros aleatorios, pra cada jogador.
    };

    public void definirPrimeiroJogador(){
        // gerar um numero aleatorio de 1 a 6 pra cada player
        // ver qual o maior e definir PrimeiroJogador
        // Definir PrimeiroJogador como playerOfTheTurn.
    };

    public Long fazerAposta(Long idJogador, Long[] aposta, String ordem){
        //vai dar erro se o nro de jogadores n for igual match.getNumberOfPlayer
        //vai dar erro se iniciarNovoTurno n for chamada antes
        long totalNumberOfDices = 0L;
        boolean validBid = false;
        for (Player player : players){
            totalNumberOfDices += player.getNumberOfDices();
        }
        if (aposta[0] > totalNumberOfDices){
            throw new IllegalArgumentException("Número de dados na aposta ("+aposta[0]+") maior que o número de dados na mesas ("+totalNumberOfDices+").");
        } else if (aposta[0] <= 0L || aposta[1] < 1L || aposta[1] > 6L){
            throw new IllegalArgumentException("Aposta inválida.");
        }
        Long[] higherBagohBid = {0L,1L};
        Long[] higherNormalBid = {0L,0L};
        for (int i = 0; i< betHistory.length ; i++){
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
            //Adiocionar aposta no betHistory
            boolean betHistoryIsFull = true;
            for (int i = 0; i< betHistory.length ; i++){
                if(betHistory[i][0] == 0L){
                    betHistory[i] = aposta;
                    betHistoryIsFull = false;
                    break;
                }
            }
            if (betHistoryIsFull){
                for (int i = 0; i< betHistory.length-1 ; i++){
                    betHistory[i] = betHistory[i+1];
                }
                betHistory[betHistory.length-1] = aposta;
            }
            //Encontrar id do proximo jogador
            int playerPosition = 0;
            for (Player player : players){
                if (player.getId() == idJogador){
                    break;
                };
                playerPosition += 1;
            }
            if (ordem == "cima-baixo" && playerPosition < match.getNumberOfPlayer()-1){
                return players.get(playerPosition+1).getId();
            } else if (ordem == "cima-baixo") {
                return players.get(0).getId();
            } else if (ordem == "baixo-cima" && playerPosition > 0) {
                return players.get(playerPosition-1).getId();
            } else{
                return players.get(Math.toIntExact(match.getNumberOfPlayer()-1L)).getId();
            }
        } else {
            throw new IllegalArgumentException("Aposta não é maior que a anterior ou já foi feita.");
        }
    }

    public void duvidarDeAposta (){
        // validar se a aposta é verdadeira
        // informar quem venceu a aposta (quem apostou ou quem duvidou)
        // remover um dado do perdedor
        // iniciarNovoTurno()
    }

    public void iniciarNovoTurno(){
        // gerarDadosProsPlayers (respeitando a quatde de dados de cada Player)
        // informar de quem é a vez ( quem perdeu dado recomeça o turno)
        Arrays.fill(betHistory, new Long[]{0L,0L});
    }








}
