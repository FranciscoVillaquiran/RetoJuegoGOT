
package com.mycompany.juegogot.personajes;
import java.util.Random;

public class Caballero extends Personaje {
    private Random random = new Random();
    
    public Caballero(String nombre, int vida, int ataque){
        super (nombre, vida, ataque);
    }
    
    @Override
    public int atacar() {
        if (random.nextInt(100)<25){
            return getAtaque()*2+5;
        } else {
            return getAtaque()+3;
        }
    }
}
