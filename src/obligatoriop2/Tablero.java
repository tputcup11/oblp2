//Alumnos: Julieta Aboy (...) y Manuel Garrido (251152)
package obligatoriop2;
public class Tablero {

    private String[][] tablero;

    public Tablero() {
        tablero = new String[4][5];

    }

    public void inicializar() {
        int contador = 1;
        for (int fila = 0; fila < tablero.length; fila++) {
            for (int columna = 0; columna < tablero[0].length; columna++) {
                tablero[fila][columna] = "\u001B[32m" + contador + "\u001B[30m";
                contador++;
            }
        }
    }

    private int fila(int numero) {
        return (numero - 1) / 5;
    }

    private int columna(int numero) {
        return (numero - 1) % 5;
    }

    public boolean estaOcupado(int numero) {
        int col = columna(numero);
        int fil = fila(numero);

        if (!tablero[fil][col].contains("\u001B[32m")) {
            return true;
        } else {
            return false;
        }

    }

    public void ponerFicha(int numero, String sigla) throws Exception {
        int col = columna(numero);
        int fil = fila(numero);

        if (estaOcupado(numero)) {
            throw new Exception("Esta ocupado");
        } else {
            tablero[fil][col] = sigla + "";
        }

    }
    @Override
    public String toString() {
        String respuesta = "";
        for (int fila = 0; fila < tablero.length; fila++) {
            for (int columna = 0; columna < tablero[0].length; columna++) {
                respuesta += tablero[fila][columna] + "\t";
            }
            respuesta += "\n";
        }
        return respuesta;
    }



    public boolean hayDisponible() {
        for (int fila = 0; fila < tablero.length; fila++) {
            for (int columna = 0; columna < tablero[0].length; columna++) {
                if (tablero[fila][columna].contains("\u001B[32m")) {
                    return true;
                }
            }
        }
        return false;
    }

    public int puntaje(String sigla) {
        int puntos = 0;
        for (int fila = 0; fila < tablero.length; fila++) {
            int continua = 0;
            for (int columna = 0; columna < tablero[0].length; columna++) {
                if (tablero[fila][columna].equals(sigla)) {
                    continua++;
                } else {
                    if (continua >= 3) {
                        puntos += continua;
                    }
                    continua = 0;

                }
            }
            if (continua >= 3) {
                puntos += continua;
            }
        }

        for (int columna = 0; columna < tablero[0].length; columna++) {
            int continua = 0;

            for (int fila = 0; fila < tablero.length; fila++) {
                if (tablero[fila][columna].equals(sigla)) {
                    continua++;
                } else {
                    if (continua >= 3) {
                        puntos += continua;
                    }
                    continua = 0;

                }
            }
            if (continua >= 3) {
                puntos += continua;
            }
        }
        
        //Diagonal izq a der
        if(tablero[0][1].equals(sigla) && tablero[1][2].equals(sigla) && tablero[2][3].equals(sigla)&&tablero[3][4].equals(sigla))
            puntos+=4;
        else
            if(tablero[0][1].equals(sigla) && tablero[1][2].equals(sigla) && tablero[2][3].equals(sigla))
                puntos+=3;
            else
                if(tablero[1][2].equals(sigla) && tablero[2][3].equals(sigla)&&tablero[3][4].equals(sigla))
                    puntos+=3;
        
        if(tablero[0][0].equals(sigla) && tablero[1][1].equals(sigla) && tablero[2][2].equals(sigla)&& tablero[3][3].equals(sigla))    
            puntos+=4;
        else{
            if(tablero[0][0].equals(sigla) && tablero[1][1].equals(sigla) && tablero[2][2].equals(sigla))
            {
                puntos+=3;
            }
            else{
                if(tablero[0][2].equals(sigla) && tablero[1][3].equals(sigla)&& tablero[2][4].equals(sigla))
                    puntos+=3;
            }
        }
            
        if(tablero[1][1].equals(sigla) && tablero[2][2].equals(sigla)&& tablero[3][3].equals(sigla))    
            puntos+=3;
        
        //Diagonal der a izq
        if(tablero[0][4].equals(sigla) && tablero[1][3].equals(sigla) && tablero[2][2].equals(sigla)&&tablero[3][1].equals(sigla))
            puntos+=4;
        else
            if(tablero[0][4].equals(sigla) && tablero[1][3].equals(sigla) && tablero[2][2].equals(sigla))
                puntos+=3;
            else
                if(tablero[1][3].equals(sigla) && tablero[2][2].equals(sigla)&&tablero[3][1].equals(sigla))
                    puntos+=3;
        
        
        if(tablero[0][3].equals(sigla) && tablero[1][2].equals(sigla) && tablero[2][1].equals(sigla)&& tablero[3][0].equals(sigla))    
            puntos+=4;
        else{
            if(tablero[0][3].equals(sigla) && tablero[1][2].equals(sigla) && tablero[2][1].equals(sigla))
            {
                puntos+=3;
            }
            else{
                if(tablero[1][2].equals(sigla) && tablero[2][1].equals(sigla)&& tablero[3][0].equals(sigla))
                    puntos+=3;
            }
        }
        
        if(tablero[1][4].equals(sigla) && tablero[2][3].equals(sigla)&& tablero[3][2].equals(sigla))    
            puntos+=3;
        
        
        return puntos;
    }
}
    

