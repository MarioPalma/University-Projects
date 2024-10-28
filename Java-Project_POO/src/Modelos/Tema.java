package Modelos;

import java.util.ArrayList;

public class Tema {

    private int valorCumplimientoTema;
    private String nombreTema;

    //Array de todos los indicadores
    public ArrayList<Indicador> indicadores;

    public Tema(String nombreTema,String indicadores, int valorCumplimientoTema) {
        this.nombreTema = nombreTema;
        this.valorCumplimientoTema = valorCumplimientoTema;
    }

    public Tema() {
    }

    // Getters y Setters
    public String getNombreTema() {
        return nombreTema;
    }

    public void setNombreTema(String nombreTema) {
        this.nombreTema = nombreTema;
    }

    public ArrayList<Indicador> getIndicadores() {
        return indicadores;
    }

    public void setIndicadores(ArrayList<Indicador> indicadores) {
        this.indicadores = indicadores;
    }

    public int getValorCumplimientoTema() {
        return valorCumplimientoTema;
    }

    public void setValorCumplimientoTema(int valorCumplimientoTema) {
        this.valorCumplimientoTema = valorCumplimientoTema;
    }
    

    @Override
    public String toString() {
        return "Tema{" +
                "nombreTema='" + nombreTema + '\'' +
                ", indicadores =" + indicadores +
                '}';
    }
}