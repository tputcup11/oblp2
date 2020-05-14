package juegoSumas;
import java.util.Scanner;

public class Principal {
    static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        //Inicializar objetos del sistema y variables
        Sistema sistema = new Sistema();
        Jugador jugador1;
        Jugador jugador2;
        String seleccion;
        String nombreJugador;
        String aliasJugador;
        String letraJ1;
        String letraJ2;
        int edadJugador;
        
        System.out.println("------ Juego Sumas ------");
        System.out.println("");
        
        //Loop Principal - Muestra menu principal y pide opcion
        seleccion = menuPrincipal();
        
        while (!seleccion.equals("D")){
            
            if(seleccion.equals("A")){
               //Registro Jugador
               System.out.println("Ingrese el nombre del jugador:");
               nombreJugador = in.nextLine();
               System.out.println("Ingrese la edad del jugador:");
               edadJugador = in.nextInt();
               System.out.println("Ingrese el alias del jugador:");
               aliasJugador = in.nextLine();
               
               Jugador jugador = new Jugador(nombreJugador, edadJugador, aliasJugador);
               sistema.getJugadores().add(jugador);
               System.out.println("Jugador Ingresado: " + jugador.toString());
            }
            else if(seleccion.equals("B")){
               //Iniciar Juego
               //Listar Jugadores, Elegir 2 jugadores, 2 letras. instanciar una partida.
               
               Partida partida = new Partida(jugador1, jugador2, letraj1, letraj2);
            }
            else if(seleccion.equals("C")){
               //Listar Ranking 
            }
            seleccion = menuPrincipal();
        }
    }
    
    public static String menuPrincipal(){
        String opcion;
        
        System.out.println("Menú del Juego:");
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
                return menuPrincipal();
        }
    }
}
