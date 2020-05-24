package obligatoriop2;

public class Tablero {
    private String[][] tablero ;
    
    public Tablero()
    {
        tablero = new String[4][5];
        
    }
    
    public void inicializar()
    {
        int contador=1;
        for(int fila=0;fila<tablero.length;fila++)
        {
            for(int columna=0;columna<tablero[0].length;columna++)
            {
                tablero[fila][columna]="\u001B[32m"+contador+"\u001B[30m";
                contador++;
            }
        }
    }

    private int fila(int numero)
    {
        return (numero-1)/5;
    }
    private int columna(int numero)
    {
        return (numero-1)%5;
    }
    
    public boolean estaOcupado(int numero)
    {
        int col=columna(numero);
        int fil=fila(numero);
        
        if(!tablero[fil][col].contains("\u001B[32m"))
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    
    public void ponerFicha(int numero, String sigla) throws Exception{
        int col=columna(numero);
        int fil=fila(numero);
        
        if(estaOcupado(numero))
            throw new Exception("Esta ocupado");
        else
            tablero[fil][col]=sigla+"";
        
        
    }
    
    public String toString()
    {
        String respuesta="";
        for(int fila=0;fila<tablero.length;fila++)
        {
            for(int columna=0;columna<tablero[0].length;columna++)
            {
                respuesta+=tablero[fila][columna]+"\t";
            }
            respuesta+="\n";
        }
        return respuesta;
    }

}
    

