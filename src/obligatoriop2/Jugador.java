package obligatoriop2;

import java.util.Objects;

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

    public void setPartidasGanadas() {
        this.partidasGanadas++;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public void setPartidasJugadas() {
        this.partidasJugadas++;
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
    

