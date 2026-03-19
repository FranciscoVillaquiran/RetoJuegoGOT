
package com.mycompany.juegogot;
import com.mycompany.juegogot.personajes.*;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.io.FileWriter;

public class JuegoGOT {
    static List<Evento> eventos = new ArrayList<>();
    static List<Personaje> todos = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        Random random = new Random();
        
        Casa Stark = new Casa("Casa Stark");
        Casa Targaryen = new Casa("Casa Targaryen");
        
        for (int i = 0; i < 5; i++) {
            Personaje p1 = Aleatorio("Stark "+i);
            Stark.unir(p1);
            todos.add(p1);
        }
        for (int i = 0; i < 5; i++) {
            Personaje p2 = Aleatorio("Targaryen "+i);
            Targaryen.unir(p2);
            todos.add(p2);
        }
        
        System.out.println("--- COMIENZA EL COMBATE ---");
        
        int ronda = 1;
        
        while (Stark.estaViva() && Targaryen.estaViva()) {
            
            System.out.println("\n--- Ronda "+ ronda++ + "---");
            
            ejecutarTurno(Stark, Targaryen, random);
            ejecutarTurno(Targaryen, Stark, random);
            
            mostrarEstado(Stark, Targaryen);
        }
        
        if (Stark.estaViva()){
            System.out.println(" LA CASA STARK SE ALZA EN EL TRONO");
        } else {
            System.out.println("LA CASA TARGARYEN SE ALZA EN EL TRONO");
        }
        
        guardarJSON();
    }      
      private static void ejecutarTurno(Casa atacante, Casa defensor, Random random) {
          

    List<Personaje> atacantes = atacante.obtenerVivos();
    List<Personaje> defensores = defensor.obtenerVivos();

    for (Personaje a : atacantes) {

        if (defensores.isEmpty())
            return;

        int valor = a.atacar();
        Personaje objetivo;

        if (valor < 0) {
            List<Personaje> aliados = atacante.obtenerVivos();
            objetivo = aliados.get(random.nextInt(aliados.size()));

            objetivo.recibirDaño(valor); // negativo = cura

            int curacion = -valor;

            System.out.println("[" + atacante.getNombre() + "] "
                    + a.getNombre() + " cura a "
                    + objetivo.getNombre() + " en "
                    + curacion + " puntos. Vida actual: "
                    + objetivo.getVida());

         
            eventos.add(new Evento("cura",
                    a.getNombre(),
                    objetivo.getNombre(),
                    curacion));

        } else {
           
            objetivo = defensores.get(random.nextInt(defensores.size()));

            objetivo.recibirDaño(valor);

            System.out.println("[" + atacante.getNombre() + "] "
                    + a.getNombre() + " ataca a "
                    + objetivo.getNombre() + " y hace "
                    + valor + " de daño. Vida restante: "
                    + objetivo.getVida());

            
            eventos.add(new Evento("ataque",
                    a.getNombre(),
                    objetivo.getNombre(),
                    valor));

            // actualizar enemigos vivos
            defensores = defensor.obtenerVivos();
        }
    }
}



        private static void mostrarEstado(Casa c1, Casa c2) {
            
            System.out.println("\n--- Estado de Ejercitos ---");
            
            imprimirCasa(c1);
            imprimirCasa(c2);
        }
        
        private static void imprimirCasa(Casa casa){
            
            System.out.println("\n"+casa.getNombre());
            
            for (Personaje p : casa.getEjercito()) {
                System.out.println("- "+p.getNombre()+" ("+p.getClass().getSimpleName()+"): "+p.getVida()+ " PV");
                
            }
        }
        
        private static Personaje Aleatorio(String nombre) {
            Random random = new Random ();
            int tipo = random.nextInt(5);
            
            switch (tipo) {
                case 0:
                    return new Soldado(nombre, 90, 18);
                case 1:
                    return new Caballero (nombre, 100, 20);
                case 2:
                    return new Arquero (nombre, 80, 15);
                case 3:
                    return new JineteDeDragones (nombre, 150, 25);
                default:
                    return new Curandero (nombre, 80, 15);
            }
        }
        
        static class Evento {
            String tipo;
            String actor;
            String objetivo;
            int valor;
            
            public Evento(String tipo, String actor, String objetivo, int valor) {
                this.tipo = tipo;
                this.actor = actor;
                this.objetivo = objetivo;
                this.valor = valor;
            }
            
        }
            private static void guardarJSON() {

            try {
                FileWriter writer = new FileWriter("batalla.json");

                writer.write("{\n");

                // PERSONAJES
                writer.write("\"personajes\": [\n");
                for (int i = 0; i < todos.size(); i++) {
                    Personaje p = todos.get(i);

                    writer.write("  {\"nombre\":\"" + p.getNombre() + "\",");
                    writer.write("\"vida\":" + p.getVidaMax() + ",");
                    writer.write("\"vidaMax\":" + p.getVidaMax() + ",");
                    writer.write("\"tipo\":\""+p.getClass().getSimpleName()+"\",");
                    writer.write("\"casa\":\"" + (i < 5 ? "Stark" : "Targaryen") + "\"}");

                    if (i < todos.size() - 1) writer.write(",");
                    writer.write("\n");
                }
                writer.write("],\n");

                // EVENTOS
                writer.write("\"eventos\": [\n");
                for (int i = 0; i < eventos.size(); i++) {
                    Evento e = eventos.get(i);

                    writer.write("  {\"tipo\":\"" + e.tipo + "\",");
                    writer.write("\"actor\":\"" + e.actor + "\",");
                    writer.write("\"objetivo\":\"" + e.objetivo + "\",");
                    writer.write("\"valor\":" + e.valor + "}");

                    if (i < eventos.size() - 1) writer.write(",");
                    writer.write("\n");
                }
                writer.write("]\n}");

                writer.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
