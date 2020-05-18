/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obli2;

/**
 *
 * @author Admin
 */

public class Partida {
    private Jugador jugador1;
    private Jugador jugador2;
    private Tablero tablero;
    private char sigla1;
    private char sigla2;
    private Dado[] dados;
    private boolean turnoJugador1;
    
    public Partida(Jugador jugador1, char sigla1,Jugador jugador2, char sigla2) {
        this.jugador1 = jugador1;
        this.sigla1 = sigla1;
        this.jugador2=jugador2;
        this.sigla2 = sigla2;
        tablero=new Tablero();
        dados=new Dado[5];
        turnoJugador1=true;
    }
    
    public void inicializar()
    {
        tablero.inicializar();
        for(int posicion=0;posicion<dados.length;posicion++)
        {
            dados[posicion].tirar();
        }
    }
    
    private char getSigla()
    {
        if(turnoJugador1)
        {
            return sigla1;
        }
        else{
            return sigla2;
        }
    }
    
    private void cambioTurno()
    {
        turnoJugador1=!turnoJugador1;
    }
    public void ponerFichaBase() throws Exception
    {
        int numero=dados[0].getNumero();
        char sigla=getSigla();
        tablero.ponerFicha(numero,sigla);
        cambioTurno();
        
    }
    
    
//@@ -36,7 +27,7 @@ public Tablero getTablero() {
//    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
        this.tablero = tablero; 
    }

    public void ponerFichaExtra(int posicionDado1, int posicionDado2, int posicionDado3) throws Exception {
        int numero=dados[0].getNumero()+dados[posicionDado1].getNumero()+dados[posicionDado2].getNumero()+dados[posicionDado3].getNumero();
        if(numero>20)
        {
            throw new Exception("Supera el numero permitido");
        }
        else
        {
            char sigla=getSigla();
            tablero.ponerFicha(numero,sigla);
            cambioTurno();
        }
    }


    
}