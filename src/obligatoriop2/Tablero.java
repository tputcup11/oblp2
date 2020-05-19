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
                tablero[fila][columna]=contador+"";
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
    
    private boolean estaOcupado(int numero)
    {
        int col=columna(numero);
        int fil=fila(numero);
        char caracter=tablero[fil][col].charAt(0);
        if(caracter>=65 && caracter<=90)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    
    public void ponerFicha(int numero, char sigla) throws Exception{
        int col=columna(numero);
        int fil=fila(numero);
        
        if(estaOcupado(numero))
            throw new Exception("Esta ocupado");
        else
            tablero[fil][col]=sigla+"";
        
        
    }

}
    

