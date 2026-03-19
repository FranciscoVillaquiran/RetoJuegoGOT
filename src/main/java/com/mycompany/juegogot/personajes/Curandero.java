
package com.mycompany.juegogot.personajes;


public class Curandero extends Personaje {
    
    public Curandero(String nombre, int vida, int ataque){
      super(nombre, vida, ataque);   
    }
    
    @Override
    public int atacar(){
       return -(getAtaque()+5); 
    }
}
