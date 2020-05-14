package juegoSumas;

import java.util.*;

public class Sistema {
    private ArrayList<Jugador> listaJugadores;
    private Partida partida;

    //Constructor
    public Sistema(){
        listaJugadores = new ArrayList<>();
    }
    
    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }
    
    public ArrayList<Jugador> getJugadores(){
        return listaJugadores;
    }
    
    public void agregarJugador(Jugador unJugador){
        listaJugadores.add(unJugador);
    }                                                                                                                   
    
    //Para TESTEAR: Metodo que ordena ArrayList por cant. de partidas ganadas.
    public ArrayList<Jugador> ordenarGanadores(){
        Collections.sort(this.getJugadores(), new CriterioGanadas());
        return this.getJugadores();
    }
    //Criterio de comparacion del sort.
     private class CriterioGanadas implements Comparator<Jugador> {
        public int compare(Jugador jugador1, Jugador jugador2){
            return jugador1.getPartidasGanadas() - jugador2.getPartidasGanadas();
        }
    }
}
