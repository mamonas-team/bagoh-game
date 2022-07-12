package application.game.repositories;

import javax.lang.model.type.NullType;
import java.util.LinkedList;
import java.util.List;

class Partida {
    public Integer Criador;
    public LinkedList<Integer> Participantes;
    public Boolean ComecouOJogo = Boolean.FALSE;
    public String Id;
}

public class MatchRepository {

    public LinkedList<Partida> ListaPartidas; //lista de partidas criadas


    public String criarPartida(Integer userId){
        for (int i = 0; i < this.ListaPartidas.size(); i++) { //parte do service provavelmente
            for (int j = 0; j < this.ListaPartidas.get(i).Participantes.size(); j++) {
                if (this.ListaPartidas.get(i).Participantes.get(j) == userId){
                    return "O jogador ja esta em uma partida";
                }
            }
        } //final parte service MatchRepository.criarPartida(userId)
        Partida PartidaNova = new Partida();
        PartidaNova.Criador = userId;
        PartidaNova.Participantes.add(userId);
        PartidaNova.Id = "C"+String.valueOf(userId);
        this.ListaPartidas.add(PartidaNova);
        return PartidaNova.Id;
    }

    public Boolean buscarPartida(String idPartida){
        for (int i = 0; i < this.ListaPartidas.size(); i++) {
            if(this.ListaPartidas.get(i).Id == idPartida){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public Boolean inserirUsuarioPartida(String idPartida, Integer idUsuario) {
        if (this.buscarPartida(idPartida)) {
            for (int i = 0; i < this.ListaPartidas.size(); i++) {
                if (idPartida == ListaPartidas.get(i).Id) {
                    ListaPartidas.get(i).Participantes.add(idUsuario);
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    public Boolean finalizarPartida(String idPartida){
            //retirar partida da lista e apagar ela
    }
}
