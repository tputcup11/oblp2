/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obli2;

/**
 *
 * @author Admin
 */
    


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

    public ArrayList<Jugador> getListaJugadores(){
        return listaJugadores;
    }

    public void agregarJugador(Jugador unJugador){
        listaJugadores.add(unJugador);
    }                                                                                                                   

    //Para TESTEAR: Metodo que ordena ArrayList por cant. de partidas ganadas.
    public ArrayList<Jugador> ordenarGanadores(){
        Collections.sort(this.getListaJugadores(), new CriterioGanadas());
        return this.getListaJugadores();
    }
    
    public void crearPartida(Jugador jugador1,char sigla1,Jugador jugador2,char sigla2)
    {
        partida=new Partida(jugador1,sigla1,jugador2,sigla2);
    }
    
    public void ponerFichaBase() throws Exception
    {
        partida.ponerFichaBase();
    }
    
    public void ponerFichaExtra(int posicionDado1,int posicionDado2,int posicionDado3) throws Exception
    {
        partida.ponerFichaExtra(posicionDado1,posicionDado2,posicionDado3);
    }
    
    //Criterio de comparacion del sort.
     private class CriterioGanadas implements Comparator<Jugador> {
        public int compare(Jugador jugador1, Jugador jugador2){
            return jugador1.getPartidasGanadas() - jugador2.getPartidasGanadas();
        }
    }
} 