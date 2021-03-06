//Alumnos: Julieta Aboy (254051) y Manuel Garrido (251152)
package obligatoriop2;

public class Jugador{
   //variables de instancia
   private String nombre;
   private int edad;
   private String alias;
   private int partidasGanadas;
   private int partidasJugadas;

   public Jugador(String nombreJugador, int edadJugador, String aliasJugador){
       this.nombre = nombreJugador;
       this.edad = edadJugador;
       this.alias = aliasJugador;
       this.partidasGanadas = 0;
       this.partidasJugadas = 0;
   }
   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public void setPartidasJugadas(int partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }
    public String devolverRanking(){
        return this.toString() + "\n Partidas Ganadas: " + this.getPartidasGanadas()+"\n Partidas Jugadas:"+ this.getPartidasJugadas();
    }
    @Override
    public String toString(){
        return "Nombre: "+this.getNombre()+", Edad: "+ this.getEdad()+", Alias: "+this.getAlias();
    }
    @Override
    public boolean equals(Object obj){
        Jugador jugador = (Jugador)obj;
        return this.getAlias().equals(jugador.getAlias());
    }
}
    

