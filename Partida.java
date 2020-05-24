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

    /*
    public void setTablero(Tablero tablero) {
        this.tablero = tablero; 
    }*/

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

    public String toString() {
        String mostrarDado = "\n(" + dados[0].getNumero() + ") ";
        for (int i = 1; i < dados.length; i++) {
            mostrarDado += dados[i].getNumero() + " ";
        }
        String turno = jugador2.getAlias();
        if (turnoJugador1) {
            turno = jugador1.getAlias();
        }

        return tablero.toString() + mostrarDado + "\nT U R N O : " + turno + "\n";
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
        int numero = dados[0].getNumero();
        String respuesta = "";
        String[] combinatoria = new String[120];

        ArrayList<Integer[]> combinaciones = new ArrayList<>();

        Integer[] nuevo = new Integer[1];
        nuevo[0] = dados[0].getNumero();
        combinaciones.add(nuevo);
//        3  4  2   2   4
        //       
        //       3

        //       3   (3 4)  (3 2)  (3 2)  (3 4)   
        //      3   (3 4)  (3 2)  (3 2)  (3 4)  (3  4  4) (3 4 2)
        for (int x = 1; x <= 4; x++) {
            int tamanio=combinaciones.size();
            ArrayList<Integer[]> listaAux=new ArrayList<>();
            for (int z=0;z<tamanio;z++) {
                Integer[] numeros=combinaciones.get(z);
                if (x == numeros.length) {
                    for (int i = 1; i < 5; i++) {
                        int num = dados[i].getNumero();
                        nuevo = new Integer[numeros.length + 1];
                        int j;
                        for (j = 0; j < numeros.length; j++) {
                            nuevo[j] = numeros[j];
                        }
                        nuevo[j] = num;
                        if(!estaContenido(nuevo,listaAux))
                        {
                            if(esValido(nuevo))
                            {
                                listaAux.add(nuevo);
                            }
                        }

                    }
                }
            }
            combinaciones.addAll(listaAux);
        }

        int i=1;
        for(Integer[] vec : combinaciones)
        {
            int suma=sumatoria(vec);
            if(suma<=20 && !tablero.estaOcupado(suma))
            {
                String aux="(";
                for(int j=0;j<vec.length;j++)
                {
                    aux+=vec[j]+" ";
                }
                respuesta+="Opcion "+i+" tomar los siguiente numeros "+aux+")\n";
                i++;
            }
        }

        return respuesta;

    }

    private boolean esValido(Integer numerosRecuperados[])
    {
        ArrayList<Integer> numerosDados = convertirDadoAnumeros();
        for (int i = 0; i < numerosRecuperados.length; i++) {
           
                
                    if (!numerosDados.contains(numerosRecuperados[i])) {
                        return false;
                    }
                    else{
                        numerosDados.remove(numerosRecuperados[i]);
                    }
                
           
        }
        return true;
    }
    
    private int sumatoria(Integer[] vec)
    {
        int suma=0;
        for(Integer numero:vec)
        {
            suma+=numero;
        }
        return suma;
    }
    
    private boolean estaContenido(Integer[] vector, ArrayList<Integer[]> listaAux) {
        for(Integer[] numeros:listaAux)
        {
            if(sonIguales(numeros,vector))
                return true;
        }

        return false;
    }

    private boolean sonIguales(Integer[] numeros, Integer[] vector) {
        if(numeros.length==vector.length)
        {
            
            for(int i=1;i<numeros.length;i++)
            {
                int contador=0;
               for(int j=1;j<numeros.length;j++)
                {
                    if(!numeros[i].equals(vector[j]))
                        return false;
                            
                } 
            }
            return true;
        }
        else{
            return false;
        }
    

}
}