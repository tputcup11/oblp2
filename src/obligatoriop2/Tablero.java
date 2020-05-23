package obligatoriop2;

public class Tablero {
    private String[][] tablero ;
    
    public Tablero()
    {
        tablero = new String[4][5];
        
    }
    
    public void inicializar(){
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
    
    @Override
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
    
    public int contarPuntos(String sigla){
        int puntos = 0;
        int contadorAlineados = 0;
        int puntosAuxiliar = 0;
        
        for(int i = 0; i < 4; i++){
        //Recorrer cada fila y ver si en cada fila se tiene una cantidad de elementos alineados que lleguen al minimo para que sume puntos. (Al menos 3 elementos consiguientes, sino no se suman puntos)
            for (int j = 0; j < 5; j++){
            //Recorrer cada elemento de una fila y sumar los puntos que correpondan.    
                if (this.tablero[i][j].contains(sigla)){
                    if (contadorAlineados == 0){
                        contadorAlineados++;
                    }
                    else if(this.tablero[i][j - 1].contains(this.tablero[i][j])){
                        contadorAlineados++;
                    }
                }else{
                    if (contadorAlineados > puntosAuxiliar){
                        puntosAuxiliar = contadorAlineados; 
                    }
                    contadorAlineados = 0;
                }
            }
            if(puntosAuxiliar == 5){
                puntos += 5;
            }
            else if(puntosAuxiliar == 4){
                puntos += 4;
            }
            else if(puntosAuxiliar == 3){
                puntos += 3;
            }
            contadorAlineados = 0;
            puntosAuxiliar = 0;
        }
        //Recorrer columnas y sumar puntos que alcancen minimo
        for(int j = 0; j < 5; j++){
            //Recorrer cada fila y contar elementos alineados.
            for (int i = 0; i < 4; i++){
               
                if (this.tablero[i][j].contains(sigla)) {
                    if (contadorAlineados == 0){
                        contadorAlineados++;
                    }
                    else if(this.tablero[i - 1][j].contains(this.tablero[i][j])){
                        contadorAlineados++;
                    }
                }else{
                    if (contadorAlineados > puntosAuxiliar){
                        puntosAuxiliar = contadorAlineados; 
                    }
                    contadorAlineados = 0;
                }
            }
            if(puntosAuxiliar == 4){
                puntos += 4;
            }
            else if(puntosAuxiliar == 3){
                puntos += 3;
            }
            contadorAlineados = 0;
            puntosAuxiliar = 0;
        }
        
        return puntos;
    }

}
    

