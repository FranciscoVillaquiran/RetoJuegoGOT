
package com.mycompany.juegogot.personajes;
import java.util.Random;

public class JineteDeDragones extends Personaje{
    private Random random = new Random();
    
    public JineteDeDragones(String nombre, int vida, int ataque){
        super (nombre, vida, ataque);
    }
    @Override
    public int atacar(){
        
        if(random.nextInt(100)<30){
            return 0;
        } else {
            return getAtaque()*3;
        }
    }
}
