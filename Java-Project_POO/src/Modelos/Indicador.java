package Modelos;

import java.util.ArrayList;

public class Indicador {

    private String nombreIndicador;
    private String descripcion;
    private double presupuesto;
    private int vecesUsado;
    private int valorCumplimiento;

    // Array de los proyectos que contienen indicadores
    private ArrayList<Proyecto> proyectosRelacionados = new ArrayList<>();

    // Constructor
    public Indicador(String nombreIndicador, String descripcion, double presupuesto, int vecesUsado, int valorCumplimiento) {
        this.nombreIndicador = nombreIndicador;
        this.descripcion = descripcion;
        this.presupuesto = presupuesto;
        this.vecesUsado = vecesUsado;
        this.valorCumplimiento = valorCumplimiento;
    }

    public Indicador() {
    }

    // Getters y Setters
    public String getNombreIndicador() {
        return nombreIndicador;
    }

    public void setNombreIndicador(String nombreIndicador) {
        this.nombreIndicador = nombreIndicador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public int getVecesUsado() {
        return vecesUsado;
    }

    public void setVecesUsado(int vecesUsado) {
        this.vecesUsado = vecesUsado;
    }

    public int getValorCumplimiento() {
        return valorCumplimiento;
    }

    public void setValorCumplimiento(int valorCumplimiento) {
        this.valorCumplimiento = valorCumplimiento;
    }

    public ArrayList<Proyecto> getProyectosRelacionados() {
        return proyectosRelacionados;
    }

    public void agregarProyecto(Proyecto proyecto) {
        proyectosRelacionados.add(proyecto);
    }

    @Override
    public String toString() {
        return "Indicador{" +
                "nombre='" + nombreIndicador + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", presupuesto=" + presupuesto +
                ", vecesUsado=" + vecesUsado +
                ", valorCumplimiento=" + valorCumplimiento +
                '}';
    }
}