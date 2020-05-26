//Alumnos: Julieta Aboy (254051) y Manuel Garrido (251152)
package obligatoriop2;
import java.util.Random;

public class Dado {
    private int numero;

    public void tirar(){
        Random random = new Random();
        numero = random.nextInt(6)+1;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
