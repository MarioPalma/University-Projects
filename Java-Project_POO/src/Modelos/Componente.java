package Modelos;

import java.util.ArrayList;

public class Componente {

    private String codComponente;
    private String nomComponente;
    private int porcentajeCumplimiento;

    public ArrayList<Tema> temas;

    // Getters y Setters
    public String getCodComponente() {
        return codComponente;
    }

    public void setCodComponente(String codComponente) {
        this.codComponente = codComponente;
    }

    public String getNomComponente() {
        return nomComponente;
    }

    public void setNomComponente(String nomComponente) {
        this.nomComponente = nomComponente;
    }

    public ArrayList<Tema> getTemas() {
        return temas;
    }

    public void setTemas(ArrayList<Tema> temas) {
        this.temas = temas;
    }
    
    public int getPorcentajeCumplimiento() {
        return porcentajeCumplimiento;
    }

    public void setPorcentajeCumplimiento(int porcentajeCumplimiento) {
        this.porcentajeCumplimiento = porcentajeCumplimiento;
    }

    @Override
    public String toString() {
        return "Componente{" +
                "codComponente='" + codComponente + '\'' +
                ", nomComponente='" + nomComponente + '\'' +
                ", temas=" + temas +
                '}';
    }
}