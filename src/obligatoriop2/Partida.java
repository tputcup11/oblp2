package obligatoriop2;

import java.util.ArrayList;
import java.util.Arrays;

public class Partida {

    private Jugador jugador1;
    private Jugador jugador2;
    private Tablero tablero;
    private String sigla1;
    private String sigla2;
    private Dado[] dados;
    private boolean turnoJugador1;

    public Partida(Jugador jugador1, char sigla1, Jugador jugador2, char sigla2) {
        this.jugador1 = jugador1;
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

    public void ponerFichaExtra(String extras) throws Exception {
        String[] corta = extras.split(" ");
        int[] numerosRecuperados = new int[corta.length];
        ArrayList<Integer> numerosDados = convertirDadoAnumeros();
        for (int i = 0; i < numerosRecuperados.length; i++) {
            try {
                numerosRecuperados[i] = Integer.parseInt(corta[i]);
                if (numerosRecuperados[i] < 1 || numerosRecuperados[i] > 6) {
                    throw new Exception("No es valido el numero");
                } else {
                    if (!numerosDados.contains(numerosRecuperados[i])) {
                        throw new Exception("No estan en los dados");
                    }
                }
            } catch (NumberFormatException ex) {
                throw new Exception("Solamente se acepta numericos");
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

        return tablero.toString() + "\n" + "Puntos " + jugador1.getAlias() + ": " + tablero.contarPuntos(sigla1) + "\n" + "Puntos " + jugador2.getAlias() + ": " + tablero.contarPuntos(sigla2) + mostrarDado + "\nT U R N O : " + turno + "\n";
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

    public String ayuda() {
        String respuesta = "";
        int i=1;
        //Arrays combinaciones
        ArrayList<Integer[]> combinaciones = combinar();
        ArrayList<Integer[]> combFinales = new ArrayList<>();
        
        for(Integer[] vec : combinaciones){
            int suma = sumatoria(vec);
            if(suma <= 20 && !estaContenido(vec, combFinales)){
                combFinales.add(vec);
            }
        }
        
        for(Integer[] vec : combFinales){
            int suma = sumatoria(vec);
            if(!tablero.estaOcupado(suma))
            {
                String aux="(";
                for(int j = 0;j < vec.length;j++){
                    aux+=vec[j]+" ";
                }
                respuesta+="Opcion "+i+": Tomar los siguiente numeros "+aux+") para ocupar la posición "+suma+"\n";
                i++;
            }
        }
        return respuesta;
    }
    
    private int sumatoria(Integer[] vec)
    {
        int suma=0;
        for(Integer numero : vec)
        {
            suma+=numero;
        }
        return suma;
    }
    
    private boolean estaContenido(Integer[] vector, ArrayList<Integer[]> listaAux) {
        for(Integer[] numeros:listaAux){
            if(sumatoria(numeros) == sumatoria(vector)){
              return true;  
            }
        }
        return false;
    }
    
    private ArrayList<Integer[]> combinar(){
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
    public Boolean tableroLleno(){
        
        for (int i = 1; i <= 20; i++) {
            if (!tablero.estaOcupado(i)) {
                return false;
            }
        }
        return true;
    }
    public String mostrarGanador(boolean abandona){
        boolean empate = false;
        String respuesta = "";
        Jugador ganador = jugador1;
        int puntosJ1 = tablero.contarPuntos(sigla1);
        int puntosJ2 = tablero.contarPuntos(sigla2);
        if (abandona) {
            if (turnoJugador1) {
                ganador = jugador2;
            }
        }else{
            if (puntosJ1 > puntosJ2) {
                ganador = jugador1;
            }else if (puntosJ1 < puntosJ2) {
                ganador = jugador2;
            }else{
                empate = true;
            }
        }
        if (empate) {
            respuesta = "Fin de la Partida: EMPATE \n Puntos Finales: \n"+jugador1.getAlias()+": "+puntosJ1+"\n"+jugador2.getAlias()+": "+puntosJ2+"\n";
        }else{
            respuesta = "Fin de la Partida: GANADOR: "+ganador.getAlias()+" \nPuntos Finales: \n"+jugador1.getAlias()+": "+puntosJ1+"\n"+jugador2.getAlias()+": "+puntosJ2+"\n";
        }
        return respuesta+"Tablero Final: \n"+tablero.toString();
    }
    public Jugador devolverGanador(boolean abandona){
        boolean empate = false;
        Jugador ganador = jugador1;
        
        int puntosJ1 = tablero.contarPuntos(sigla1);
        int puntosJ2 = tablero.contarPuntos(sigla2);
        
        if (abandona) {
            if (turnoJugador1) {
                ganador = jugador2;
            }
        }else{
            if (puntosJ1 > puntosJ2) {
                ganador = jugador1;
            }else if (puntosJ1 < puntosJ2) {
                ganador = jugador2;
            }else{
                empate = true;
            }
        }
        if (empate) {
            ganador = null;
        }else{
            ganador.setPartidasGanadas();
        }
        return ganador;
    }
    
}