
package com.mycompany.juegogot.personajes;


public abstract class Personaje {
    
    private String nombre;
    private int vida;
    protected int vidaMax;
    private int atk;
    
    public Personaje(String nombre, int vida, int atk) {
        this.nombre = nombre;
        this.vida = vida;
        this.atk = atk;
        this.vidaMax = vida;
    }
    
    public abstract int atacar();
    
    public void recibirDaño(int daño) {
        vida -= daño;
        if(vida < 0){
            vida = 0;
        }
    }
    
    public boolean estaVivo(){
        return vida > 0;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public int getVida(){
        return vida;
    }
    
    public int getAtaque(){
        return atk;
    }
    
    public int getVidaMax(){
        return vidaMax;
    }
}
