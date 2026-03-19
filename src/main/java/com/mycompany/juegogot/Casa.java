
package com.mycompany.juegogot;

import com.mycompany.juegogot.personajes.Personaje;
import java.util.ArrayList;
import java.util.List;

public class Casa {
    
    private String nombre;
    private List<Personaje> ejercito;
    
    public Casa(String nombre) {
        this.nombre = nombre;
        this.ejercito = new ArrayList<>();
    }
    
    public void unir(Personaje p) {
        if (ejercito.size()<5) {
            ejercito.add(p);
        }
    }
    
    public boolean estaViva() {
        for (Personaje p : ejercito) {
            if (p.estaVivo()) return true;
        }
        return false;
    }
    
    public List<Personaje> obtenerVivos(){
        List<Personaje> vivos = new ArrayList<>();
        
        for (Personaje p : ejercito) {
            if (p.estaVivo()) {
                vivos.add(p);
            }
        }
        return vivos;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public List<Personaje> getEjercito() {
        return ejercito;
    }
}
