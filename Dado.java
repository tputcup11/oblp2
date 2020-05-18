/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obli2;

import java.util.Random;

/**
 *
 * @author Admin
 */

public class Dado {
    private int numero;

    public Dado() {
        
    }

    public void tirar()
    {
        Random random=new Random();
        numero=random.nextInt(6)+1;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
    
