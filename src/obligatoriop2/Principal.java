package obligatoriop2;
import java.util.Scanner;

public class Principal {
    static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        //Inicializar objetos del sistema y variables
        Sistema sistema = new Sistema();
        Jugador jugador1 = null;
        Jugador jugador2 = null;
        String seleccion;
        String nombreJugador;
        String aliasJugador;
        char siglaJ1 = ' ';
        char siglaJ2 = ' ';
        int edadJugador;
        boolean valorValido;
        System.out.println("------ Juego Sumas ------");
        
        //Loop Principal - Muestra menu principal y pide opcion
        seleccion = sistema.menuPrincipal();
        
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
                    //Pedir Jugador 1
                    valorValido = false;
                    while (!valorValido){
                        try {
                            
                            int entrada = in.nextInt();
                            in.nextLine();
                            if (entrada > 0 && entrada <= sistema.getListaJugadores().size()){
                                jugador1 = sistema.getListaJugadores().get(entrada - 1);
                                valorValido = true;
                            }else{
                                
                                System.out.println("Numero incorrecto, vuelva a intentar: ");
                            }
                        } catch (Exception e){
                            System.out.println("Numero incorrecto, vuelva a intentar: ");
                            in.nextLine();
                        }
                    }
                    //Pedir Sigla 1
                    System.out.println("Ingrese una letra para el Jugador 1:");
                    valorValido = false;
                    while (!valorValido){
                        try {
                            String entrada = in.nextLine().toUpperCase();
                            if (entrada.length() == 1 && entrada.toUpperCase().charAt(0) >= 65 && entrada.toUpperCase().charAt(0) <= 90){
                                siglaJ1 = entrada.charAt(0);
                                valorValido = true;
                            }else{
                                System.out.println("Debe ingresar una sola letra y no puede ser un número:");
                            }
                        } catch (Exception e) {
                            System.out.println("Debe ingresar una sola letra y no puede ser un número:");
                        }
                    }
                    
                    //Listar Jugadores
                    System.out.println("Seleccione al segundo jugador:");
                    for (int i = 0; i < sistema.getListaJugadores().size(); i++){
                        System.out.println( i+1 + ")"+ sistema.getListaJugadores().get(i).toString());
                    }
                    
                    //TODO: Implementar compareTo para el objeto Jugador para validar que no se seleccione el mismo jugador dos veces.
                    
                    //Seleccionar Jugador 2
                    valorValido = false;
                    while (!valorValido){
                        try {
                            int entrada = in.nextInt();
                            in.nextLine();
                            if (entrada > 0 && entrada <= sistema.getListaJugadores().size()){
                                jugador2 = sistema.getListaJugadores().get(entrada - 1);
                                valorValido = true;
                            }else{
                                System.out.println("Numero incorrecto, vuelva a intentar: \n");
                            }
                        } catch (Exception e) {
                            System.out.println("Numero incorrecto, vuelva a intentar: \n");
                            in.nextLine();
                        }
                    }
                   //Pedir Sigla 2
                    System.out.println("Ingrese una letra para el Jugador 2:");
                    valorValido = false;
                    while (!valorValido){
                        try {
                            String entrada = in.nextLine().toUpperCase();
                            if (entrada.length() == 1 && entrada.toUpperCase().charAt(0) >= 65 && entrada.toUpperCase().charAt(0) <= 90){
                                siglaJ2 = entrada.charAt(0);
                                valorValido = true;
                            }else{
                                System.out.println("Debe ingresar una sola letra y no puede ser un número:");
                            }
                        } catch (Exception e) {
                            System.out.println("Debe ingresar una sola letra y no puede ser un número:");
                        }
                    } 
                    sistema.crearPartida(jugador1, siglaJ1, jugador2, siglaJ2);
                    //sistema.getPartida().inicializar();
                    
                    break;
                case "C":
                    //Listar Ranking
                    break;
            }
            seleccion = sistema.menuPrincipal();
        }
    }
}
