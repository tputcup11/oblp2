package obligatoriop2;
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
        
        //Loop Principal - Muestra menu principal y pide opcion
        seleccion = menuPrincipal();
        
        while (!seleccion.equals("D")){
            switch (seleccion) {
                case "A":
                    //Registro Jugador
                    System.out.println("Ingrese el nombre del jugador:");
                    nombreJugador = in.nextLine();
                    
                    System.out.println("Ingrese la edad del jugador:");
                    edadJugador = in.nextInt();
                    in.nextLine(); //Consume el cambio de linea para poder pedir otro string.
                    
                    System.out.println("Ingrese el alias del jugador:");
                    aliasJugador = in.nextLine();
                    
                    Jugador jugador = new Jugador(nombreJugador, edadJugador, aliasJugador);
                    sistema.getListaJugadores().add(jugador);
                    System.out.println("Jugador Ingresado: " + jugador.toString());
                    break;
                case "B":
                //Iniciar Juego
                    //Listar Jugadores
                    System.out.println("Selecciona el primer jugador: \n");
                    for (int i = 0; i < sistema.getListaJugadores().size(); i++){
                        System.out.println( i+1 + ")"+ sistema.getListaJugadores().get(i).toString());
                    }
                    
                    //TODO: Elegir 2 jugadores, 2 letras
                    
                    //Partida partida = new Partida(jugador1, jugador2, letraj1, letraj2);
                    
                    //TODO: Empezar Turno, etc...
                    break;
                case "C":
                    //Listar Ranking
                    break;
            }
            seleccion = menuPrincipal();
        }
    }

    //Deberia ir en sistema??
    public static String menuPrincipal(){
        String opcion;
        
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
                return menuPrincipal();
        }
    }
}
