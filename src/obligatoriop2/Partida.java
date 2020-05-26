//Alumnos: Julieta Aboy (254051) y Manuel Garrido (251152)
package obligatoriop2;
import java.util.ArrayList;

public class Partida {

    private Jugador jugador1;
    private Jugador jugador2;
    private Tablero tablero;
    private String sigla1;
    private String sigla2;
    private Dado[] dados;
    private boolean test;
    private boolean turnoJugador1;

    public Partida(Jugador jugador1, char sigla1, Jugador jugador2, char sigla2, boolean test) {
        this.jugador1 = jugador1;
        this.test = test;
        this.sigla1 = "\u001B[31m" + sigla1 + "\u001B[30m";
        this.jugador2 = jugador2;
        this.sigla2 = "\u001B[34m" + sigla2 + "\u001B[30m";
        tablero = new Tablero();
        dados = new Dado[5];
        turnoJugador1 = true;
    }

    public void inicializar() {
        tablero.inicializar();
        for (int posicion = 0; posicion < dados.length; posicion++) {
            dados[posicion] = new Dado();
            dados[posicion].tirar();
        }
    }
    
    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }
    
    public String getSigla() {
        if (turnoJugador1) {
            return sigla1;
        } else {
            return sigla2;
        }
    }

    public void cambioTurno() {
        turnoJugador1 = !turnoJugador1;
    }

    private void lanzarDados() {
        for (int posicion = 0; posicion < dados.length; posicion++) {
            dados[posicion].tirar();
        }
    }

    public void ponerFichaBase() throws Exception {
        int numero = dados[0].getNumero();
        String sigla = getSigla();
        tablero.ponerFicha(numero, sigla);
        cambioTurno();
        lanzarDados();
    }

    public void cargarDados(String dado) throws Exception {
        String[] corta = dado.split(" ");
        if (corta.length == 5) {
            int[] numerosRecuperados = new int[corta.length];

            for (int i = 0; i < numerosRecuperados.length; i++) {
                try {
                    numerosRecuperados[i] = Integer.parseInt(corta[i]);
                    if (numerosRecuperados[i] < 1 || numerosRecuperados[i] > 6) {
                        throw new Exception("Este número no es valido");
                    }

                } catch (NumberFormatException ex) {
                    throw new Exception("Solamente se deben ingresar números.");
                }
            }

            for (int i = 0; i < 5; i++) {
                dados[i].setNumero(numerosRecuperados[i]);
            }

        } else {
            throw new Exception("Deben ser 5 numeros");
        }
    }

    public void ponerFichaExtra(String extras) throws Exception {
        String[] corta = extras.split(" ");
        int[] numerosRecuperados = new int[corta.length];
        ArrayList<Integer> numerosDados = convertirDadoAnumeros();
        for (int i = 0; i < numerosRecuperados.length; i++) {
            try {
                numerosRecuperados[i] = Integer.parseInt(corta[i]);
                if (numerosRecuperados[i] < 1 || numerosRecuperados[i] > 6) {
                    throw new Exception("El número no es valido");
                } else {
                    if (!numerosDados.contains(numerosRecuperados[i])) {
                        throw new Exception("Los números ingresados no estan en los dados");
                    }
                }
            } catch (NumberFormatException ex) {
                throw new Exception("Solamente se deben ingresar números");
            }
        }

        int numero = dados[0].getNumero();
        for (int i = 0; i < numerosRecuperados.length; i++) {
            numero += numerosRecuperados[i];
        }
        if (numero > 20) {
            throw new Exception("Supera el numero permitido");
        } else {
            String sigla = getSigla();
            tablero.ponerFicha(numero, sigla);
            cambioTurno();
            lanzarDados();
        }
    }

    private ArrayList<Integer> convertirDadoAnumeros() {
        ArrayList<Integer> numeros = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            numeros.add(dados[i].getNumero());
        }
        return numeros;
    }

    public void pasar() {
        cambioTurno();
        lanzarDados();
    }
    
    private int sumatoria(Integer[] vec) {
        int suma = 0;
        for (Integer numero : vec) {
            suma += numero;
        }
        return suma;
    }

    private boolean estaContenido(Integer[] vector, ArrayList<Integer[]> listaAux) {
        for (Integer[] numeros : listaAux) {
            if (sumatoria(numeros) == sumatoria(vector)) {
                return true;
            }
        }
        return false;
    }    
    
    public String ayuda() {
        String respuesta = "";
        int i = 1;
        //Arrays combinaciones
        ArrayList<Integer[]> combinaciones = combinar();
        ArrayList<Integer[]> combFinales = new ArrayList<>();
        
        //Se eliminan combinaciones repetidas
        for (Integer[] vec : combinaciones) {
            int suma = sumatoria(vec);
            if (suma <= 20 && !estaContenido(vec, combFinales)) {
                combFinales.add(vec);
            }
        }
        //Se muestran las jugadas si la casilla corespondiente está libre
        for (Integer[] vec : combFinales) {
            int suma = sumatoria(vec);
            if (!tablero.estaOcupado(suma)) {
                String aux = "(";
                for (int j = 0; j < vec.length; j++) {
                    aux += vec[j] + " ";
                }
                respuesta += "Opcion " + i + ": Tomar los siguiente numeros " + aux + ") para ocupar la posición " + suma + "\n";
                i++;
            }
        }
        if (respuesta.equals("")){
            respuesta = "\nNo hay jugadas disponibles";
        }

        return respuesta;
    }

    private ArrayList<Integer[]> combinar() {
        //Array combinaciones
        ArrayList<Integer[]> combinaciones = new ArrayList<>();

        //Ingreso el primer dado
        Integer[] comb = new Integer[1];
        comb[0] = dados[0].getNumero();

        combinaciones.add(comb);
        
        //Ingreso las combinaciones de dos dados
        for (int i = 1; i < 5; i++) {
            comb = new Integer[2];
            comb[0] = dados[0].getNumero(); //La base se mantiene fija
            comb[1] = dados[i].getNumero();
            combinaciones.add(comb);
        }
        
        //Ingreso las combinaciones de tres dados
        for (int i = 1; i < 5; i++) {
            for (int j = i + 1; j < 5; j++) {
                comb = new Integer[3];
                comb[0] = dados[0].getNumero(); //La base se mantiene fija
                comb[1] = dados[i].getNumero();
                comb[2] = dados[j].getNumero();
                combinaciones.add(comb);
            }
        }
        
        //Ingreso las combinaciones de cuatro dados
        for (int i = 1; i < 5; i++) {
            for (int j = i + 1; j < 5; j++) {
                for (int k = j + 1; k < 5; k++) {
                    comb = new Integer[4];
                    comb[0] = dados[0].getNumero(); //La base se mantiene fija
                    comb[1] = dados[i].getNumero();
                    comb[2] = dados[j].getNumero();
                    comb[3] = dados[k].getNumero();
                    combinaciones.add(comb);
                }
            }
        }
        
        //Ingreso la combinacion de los cinco dados
        comb = new Integer[5];
        comb[0] = dados[0].getNumero();
        comb[1] = dados[1].getNumero();
        comb[2] = dados[2].getNumero();
        comb[3] = dados[3].getNumero();
        comb[4] = dados[4].getNumero();
        combinaciones.add(comb);

        return combinaciones;
    }

    public boolean tableroLleno() {
        return !tablero.hayDisponible();
    }

    public String analisis() {
        //Muestra el ganador, los puntajes y el tablero final.
        int puntajeFinalJugador1 = tablero.puntaje(sigla1);
        int puntajeFinalJugador2 = tablero.puntaje(sigla2);
        int puntajeFinalGanador = puntajeFinalJugador1;
        int puntajeFinalPerdedor = puntajeFinalJugador2;
        String aliasGanador = jugador1.getAlias();
        String aliasPerdedor = jugador2.getAlias();
        String resultado = "";
        
        if (puntajeFinalJugador1 == puntajeFinalJugador2) {
            resultado = "\n FIN DE LA PARTIDA: \n Hay un empate, el puntaje final es: " + puntajeFinalJugador1+"\n Tablero Final: \n \n" + tablero.toString();
        } else {
            if (puntajeFinalJugador1 > puntajeFinalJugador2) {
                jugador1.setPartidasGanadas(jugador1.getPartidasGanadas() + 1); //Se suma una partida ganada al jugador.
            } else {
                jugador2.setPartidasGanadas(jugador2.getPartidasGanadas() + 1);
                puntajeFinalGanador = puntajeFinalJugador2;
                puntajeFinalPerdedor = puntajeFinalJugador1;
                aliasGanador = jugador2.getAlias();
                aliasPerdedor = jugador1.getAlias();
            }
            resultado = "\n FIN DE LA PARTIDA: \n Ganó el jugador: " + aliasGanador + " con " + puntajeFinalGanador + " puntos. \n El jugador "+aliasPerdedor+ " obtuvo " + puntajeFinalPerdedor + " puntos."+"\n Tablero Final: \n \n" + tablero.toString();
        }
        return resultado;
    }

    public String abandonar() {
        //Muestra el ganador para el caso en el que se interrumpa la partida.
        if (turnoJugador1) {
            jugador2.setPartidasGanadas(jugador2.getPartidasGanadas() + 1);
            return "\n FIN DE LA PARTIDA. Un jugador ha abandonado la partida. \n El ganador es: " + jugador2.getAlias() + " con " + tablero.puntaje(sigla2) + " puntos. \n El jugador " + jugador1.getAlias() + " obtuvo " +tablero.puntaje(sigla1) + " puntos. \n Tablero Final: \n \n" + tablero.toString();
        } else {
            jugador1.setPartidasGanadas(jugador1.getPartidasGanadas() + 1);
            return "\n FIN DE LA PARTIDA. Un jugador ha abandonado la partida. \n El ganador es: " + jugador1.getAlias() + " con " + tablero.puntaje(sigla1) + " puntos. \n El jugador " + jugador2.getAlias() + " obtuvo " +tablero.puntaje(sigla2) + " puntos. \n Tablero Final: \n \n" + tablero.toString();
        }
    }
    @Override
    public String toString() {
        String mostrarDado = "\n(" + dados[0].getNumero() + ") ";
        for (int i = 1; i < dados.length; i++) {
            mostrarDado += dados[i].getNumero() + " ";
        }
        String turno = jugador2.getAlias();
        if (turnoJugador1) {
            turno = jugador1.getAlias();
        }
        return tablero.toString() + "\n" + "Puntos " + jugador1.getAlias() + ": " + tablero.puntaje(sigla1) + "\n" + "Puntos " + jugador2.getAlias() + ": " + tablero.puntaje(sigla2) +"\n \n Dados: "+ mostrarDado + "\n T U R N O : " + turno + "\n";
    }
}