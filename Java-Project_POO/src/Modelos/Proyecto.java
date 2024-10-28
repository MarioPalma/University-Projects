package Modelos;

import java.util.ArrayList;

public class Proyecto {

    private String nombre;
    private double presupuesto;
    private int estado;

    //Array de todos los indicadores
    private ArrayList<Indicador> indicadores; 

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    //============================================================ Asosiación con Indicadores

    public ArrayList<Indicador> getIndicadoresRelacionados() {
        return indicadores;
    }

    public void setIndicadoresRelacionados(ArrayList<Indicador> indicadoresRelacionados) {
        this.indicadores = indicadoresRelacionados;
    }
    
    public ArrayList<Indicador> getIndicadores() {
        return indicadores;
    }

    // Método para establecer los indicadores
    public void setIndicadores(ArrayList<Indicador> indicadores) {
        this.indicadores = indicadores;
    }

    //=======================================================================================
    
    @Override
    public String toString() {
        return nombre +
                ", Presupuesto = " + presupuesto +
                ", Estado = " + (estado == 1 ? "Activo" : "Inactivo") +
                ", Indicadores = " + indicadores + "}";
    }
    
}