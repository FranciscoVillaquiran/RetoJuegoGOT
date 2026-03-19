
package com.mycompany.juegogot.personajes;
import java.util.Random;

public class Arquero extends Personaje{
    private Random random = new Random();
    
    public Arquero(String nombre, int vida, int ataque){
        super (nombre, vida, ataque);
    }
    
    @Override
    public int atacar(){
        return getAtaque()+random.nextInt(8)+1;
    }
}
