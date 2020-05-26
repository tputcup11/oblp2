//Alumnos: Julieta Aboy (254051) y Manuel Garrido (251152)
package obligatoriop2;
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

    //Método que ordena arraylist por partidas ganadas
    public ArrayList<Jugador> ordenarGanadores(){
        Collections.sort(this.getListaJugadores(), new CriterioGanadas());
        return this.getListaJugadores();
    }

    public void crearPartida(Jugador jugador1,char sigla1,Jugador jugador2,char sigla2,boolean test){
        partida=new Partida(jugador1,sigla1,jugador2,sigla2,test);
    }

    public void ponerFichaBase() throws Exception{
        partida.ponerFichaBase();
    }

    public void ponerFichaExtra(String extra) throws Exception{
        partida.ponerFichaExtra(extra);
    }

    public void pasar()
    {
        partida.pasar();
    }

    public String ayuda() {
        return partida.ayuda();
    }

    public Boolean tableroLleno() {
        return partida.tableroLleno();
    }
    public ArrayList<Jugador> ranking() {
        Collections.sort(listaJugadores,new CriterioGanadas());
        return listaJugadores;
    }

    public boolean continuar() {
         return !partida.tableroLleno();
    }

    public String analisis() {
        return partida.analisis();
    }

    public void cargarDados(String numeros) throws Exception {
         partida.cargarDados(numeros);
    }

    public String abandonar() {
        return partida.abandonar();
    }
    //Criterio de comparacion del sort (INNER CLASS).
     private class CriterioGanadas implements Comparator<Jugador> {
        @Override
        public int compare(Jugador jugador1, Jugador jugador2){
            return (jugador1.getPartidasGanadas() - jugador2.getPartidasGanadas())*(-1);
        }
    }
}  