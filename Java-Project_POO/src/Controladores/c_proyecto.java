package Controladores;

import Modelos.Proyecto;

import java.util.ArrayList;
import java.util.Random;

public class c_proyecto {

    //Array donde están todos los proyectos
    private ArrayList<Proyecto> proyectos = new ArrayList<>();

    //Array donde están todos los números ya utilizados
    private ArrayList<Integer> numerosAsignados = new ArrayList<>();

    public ArrayList<Proyecto> getProyectos() {
        return proyectos;
    }

    public ArrayList<Proyecto> getProyectosActivos() {
        ArrayList<Proyecto> proyectosActivos = new ArrayList<>();
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getEstado() == 1) { 
                proyectosActivos.add(proyecto);
            }
        }
        return proyectosActivos;
    }

    //===========================================================

    public void generarProyectosAleatorios(int numeroProyectos) {
        Random rand = new Random();
        for (int i = 0; i < numeroProyectos; i++) {
            int numeroProyecto;
            do {
                numeroProyecto = rand.nextInt(1000) + 1;
            } while (numerosAsignados.contains(numeroProyecto));

            numerosAsignados.add(numeroProyecto);
            
            Proyecto proyecto = new Proyecto();
            proyecto.setNombre("PROY_" + numeroProyecto);
            proyecto.setPresupuesto(rand.nextDouble() * 10000);
            proyecto.setEstado(rand.nextInt(2)); 
            proyectos.add(proyecto);
        }
    }
}