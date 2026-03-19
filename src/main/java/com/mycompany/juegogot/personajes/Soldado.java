
package com.mycompany.juegogot.personajes;

import java.util.Random;

public class Soldado extends Personaje {
    private Random random = new Random();
    
    public Soldado(String nombre, int vida, int atk) {
        super(nombre, vida, atk);
    }
    
    @Override
    public int atacar() {
        return getAtaque() + random.nextInt(5) + 1;
    }
}
