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

    //Para TESTEAR: Metodo que ordena ArrayList por cant. de partidas ganadas.
    public ArrayList<Jugador> ordenarGanadores(){
        Collections.sort(this.getListaJugadores(), new CriterioGanadas());
        return this.getListaJugadores();
    }
    
    public void crearPartida(Jugador jugador1,char sigla1,Jugador jugador2,char sigla2){
        partida=new Partida(jugador1,sigla1,jugador2,sigla2);
    }
    
    public void ponerFichaBase() throws Exception{
        partida.ponerFichaBase();
    }
    
    public void ponerFichaExtra(int posicionDado1,int posicionDado2,int posicionDado3) throws Exception{
        partida.ponerFichaExtra(posicionDado1,posicionDado2,posicionDado3);
    }
    
    public String menuPrincipal(){
        String opcion;
        Scanner in = new Scanner(System.in);
        System.out.println("\n Menú del Juego:");
        System.out.println("a) Registrar Jugador");
        System.out.println("b) Jugar a 'Sumas'");
        System.out.println("c) Ver Ranking de Jugadores");
        System.out.println("d) Terminar");
        opcion = in.nextLine();
        switch(opcion.toUpperCase()) {
            case "A":
                return "A";
            case "B":
                return "B";
            case "C":
                return "C";
            case "D":
                return "D";
            default:
                System.out.println("Opción Incorrecta. Intente otra vez.");
                System.out.println("-------------------------------------");
                return this.menuPrincipal();
        }
    }
    
    
    //Criterio de comparacion del sort.
     private class CriterioGanadas implements Comparator<Jugador> {
        public int compare(Jugador jugador1, Jugador jugador2){
            return jugador1.getPartidasGanadas() - jugador2.getPartidasGanadas();
        }
    }
} 