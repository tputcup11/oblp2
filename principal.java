//Alumnos: Julieta Aboy (254051) y Manuel Garrido (251152)

package obligatoriop2;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Sistema sistema = new Sistema();
        Jugador jugador1 = null;
        Jugador jugador2 = null;
        String seleccion;
        String nombreJugador;
        String aliasJugador;
        char siglaJ1 = ' ';
        char siglaJ2 = ' ';
        int edadJugador;
        int selection = 0;
        boolean valorValido;
        System.out.println("------ Juego Sumas ------");

        //Loop Principal - Muestra menu principal y pide opcion
        seleccion = Utilidades.menuPrincipal();
        while (!seleccion.equals("D")){
            switch (seleccion) {
                case "A":
                    nombreJugador = Utilidades.ingresarPalabra("Por favor, ingrese el nombre del jugador:");
                    edadJugador = Utilidades.ingresarEntero(1, 110, "Por favor, ingrese la edad del jugador:");
                    aliasJugador = "";
                    valorValido = false;
                    while(!valorValido){
                        aliasJugador = Utilidades.ingresarPalabra("Por favor, ingrese el alias del jugador:");
                        valorValido = true;
                        for (Jugador player : sistema.getListaJugadores()) {
                            if (player.getAlias().equals(aliasJugador)){
                                valorValido = false;
                                System.out.println("Ya existe un jugador con este Alias.");
                            }
                        }
                    }
                    
                    //Crear jugador
                    Jugador jugador = new Jugador(nombreJugador, edadJugador, aliasJugador);
                    sistema.getListaJugadores().add(jugador);
                    System.out.println("Jugador Ingresado: " + jugador.toString());
                    break;
                case "B"://Iniciar Juego
                    if(sistema.getListaJugadores().size() > 1){
                        //Pedir Datos
                        System.out.println(Utilidades.imprimirLista(sistema.getListaJugadores()));
                        selection = Utilidades.ingresarEntero(1,sistema.getListaJugadores().size(), "Seleccione un jugador:");
                        jugador1 = sistema.getListaJugadores().get(selection - 1);
                        siglaJ1 = Utilidades.ingresarLetra("Ingrese una letra correspondiente al Jugador 1:");
                        
                        System.out.println(Utilidades.imprimirLista(sistema.getListaJugadores()));
                        valorValido = false;
                        //Verifica que no se seleccione el mismo jugador otra vez.
                        while (!valorValido){
                            selection = Utilidades.ingresarEntero(1,sistema.getListaJugadores().size(), "Seleccione el segundo jugador:");
                            jugador2 = sistema.getListaJugadores().get(selection - 1);
                            if (!jugador1.equals(jugador2)){
                                valorValido = true;
                            }else{
                                System.out.println("Disculpe, no puede seleccionar al mismo jugador dos veces. \n");
                            }
                        }
                        siglaJ2 = Utilidades.ingresarLetra("Ingrese una letra para el jugador 2:");
                        
                        //Elegir Modo
                        System.out.println("¿Es modo Test? (S)i ó (N)o:");
                        String respuesta = in.nextLine();
                        boolean modoTest = respuesta.equalsIgnoreCase("S");

                        //Se suma una partida jugada a cada jugador.
                        jugador1.setPartidasJugadas(jugador1.getPartidasJugadas() + 1);
                        jugador2.setPartidasJugadas(jugador2.getPartidasJugadas() + 1);

                        //Instanciar e iniciar partida
                        sistema.crearPartida(jugador1, siglaJ1, jugador2, siglaJ2,modoTest);
                        sistema.getPartida().inicializar();
                        boolean seguir = true;
                        boolean cambioTurno = true;
                        while(seguir)
                        {
                            try
                            {
                                if(sistema.getPartida().isTest() && cambioTurno)
                                {
                                    System.out.println("Ingrese los dados para el siguiente turno:");
                                    String numeros=in.nextLine();
                                    sistema.cargarDados(numeros);
                                    cambioTurno = false;
                                }
                                System.out.println("----------------------------------\n");
                                System.out.println(sistema.getPartida().toString());
                                System.out.println("A - Ayuda");
                                System.out.println("0 - Dado base");
                                System.out.println("N - Dado extra");
                                System.out.println("X - Abandonar");
                                System.out.println("P - Pasar");
                                System.out.print("Opcion : ");
                                char opcion=in.nextLine().toUpperCase().charAt(0);
                                switch(opcion)
                                {
                                    case 'A':
                                        System.out.println("Opcion A: Ayuda - Posibles Jugadas");
                                        String ayuda=sistema.ayuda();
                                        System.out.println(ayuda);
                                        break;
                                    case '0':
                                        System.out.println("Opcion 0");
                                        sistema.ponerFichaBase();
                                        seguir=sistema.continuar();
                                        cambioTurno = true;
                                        break;
                                    case 'N':
                                        System.out.println("Opcion N");
                                        String extras=in.nextLine();
                                        sistema.ponerFichaExtra(extras);
                                        seguir=sistema.continuar();
                                        cambioTurno = true;
                                        break;
                                    case 'X':
                                        System.out.println("Opcion X");
                                        seguir=false;

                                        break;
                                    case 'P':
                                        System.out.println("Opcion P");
                                        sistema.pasar();
                                        cambioTurno=true;
                                        break;
                                    default:
                                        System.out.println("Opcion desconocida.");
                                        break;

                                }
                                if(!seguir)
                                {
                                    System.out.println("----------------------------------\n");
                                    if(opcion!='X')
                                    {
                                        String resultado=sistema.analisis();
                                        System.out.println(resultado);
                                    }
                                    else{
                                        String ganador=sistema.abandonar();
                                        System.out.println(ganador);
                                    }
                                }
                            }catch(Exception ex)
                            {
                                System.out.println(ex.getMessage());
                            }
                        }
                    }else{
                        System.out.println("Debe registrar al menos 2 jugadores para poder jugar.");
                    }    
                    break;
                case "C":
                    ArrayList<Jugador> listaJugadores=sistema.ranking();
                     System.out.println("RANKING\n");
                    for (int i = 0; i < sistema.getListaJugadores().size(); i++){
                        System.out.println( i+1 + ")"+ listaJugadores.get(i).devolverRanking());
                    }
                    break;
            }
            seleccion = Utilidades.menuPrincipal();
        }
    }
}

