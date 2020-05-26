//Alumnos: Julieta Aboy (254051) y Manuel Garrido (251152)
package obligatoriop2;
import java.util.*;

public class Utilidades {
    public static String menuPrincipal(){
        String opcion;
        Scanner in = new Scanner(System.in);
        System.out.println("\n Menú del Juego:");
        System.out.println("a) Registrar Jugador");
        System.out.println("b) Jugar a Sumas");
        System.out.println("c) Ranking de Jugadores");
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
                System.out.println("La opción que fue ingresada no es correcta. Intente otra vez.");
                System.out.println("-------------------------------------");
                return menuPrincipal();
        }
    }
    
    public static int ingresarEntero(int min, int max, String mensaje){
        int numero = 0;
        Scanner in = new Scanner(System.in);
        boolean valorValido = false;
        while(!valorValido){
            try{
                System.out.println(mensaje);
                numero = in.nextInt();
                in.nextLine();
                if(numero < min || numero > max ){
                    System.out.println("Número incorrecto.");
                }else{
                    valorValido = true;
                }
            }catch(InputMismatchException e){
                System.out.println("Solo se admiten números.");
                in.nextLine();
            }
            
        }
        return numero;
    }
    
    public static String ingresarPalabra(String mensaje){
        String palabra = "";
        Scanner in = new Scanner(System.in);
        boolean valorValido = false;
        while(!valorValido){
            try{
                System.out.println(mensaje);
                palabra = in.nextLine();
                if(!(palabra.toUpperCase().charAt(0) >= 65 && palabra.toUpperCase().charAt(0) <= 90) && !palabra.matches("^[a-zA-Z]*$")){
                    System.out.println("Se debe ingresar una palabra.");
                }else{
                    valorValido = true;
                }
            }catch(Exception e){
                System.out.println("Se debe ingresar una palabra.");
            }
            
        }
        return palabra;
    }
    
    public static char ingresarLetra(String mensaje){
        String letra = "";
        Scanner in = new Scanner(System.in);
        boolean valorValido = false;
        while(!valorValido){
            try{
                System.out.println(mensaje);
                letra = in.nextLine();
                if(!(letra.toUpperCase().charAt(0) >= 65 && letra.toUpperCase().charAt(0) <= 90) && !letra.matches("^[a-zA-Z]*$") && !(letra.length() == 1)){
                    System.out.println("Se debe ingresar una letra.");
                }else{
                    valorValido = true;
                }
            }catch(Exception e){
                System.out.println("Se debe ingresar una letra.");
            }
            
        }
        return letra.toUpperCase().charAt(0);
    }
    
    public static String imprimirLista(ArrayList<Jugador> lista){
        String jugadores = "Jugadores: \n";
        for (int i = 0; i < lista.size(); i++){
            jugadores += (i+1) + ")"+ lista.get(i).toString()+"\n";
        }
        return jugadores;
    }
}
