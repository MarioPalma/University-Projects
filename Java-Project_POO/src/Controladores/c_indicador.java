package Controladores;

import Modelos.Indicador;
import Modelos.Proyecto;

import java.util.ArrayList;

public class c_indicador {

    //el array donde están todos los indicadores #Corregir lo del llamado ciclico#

    private ArrayList<Indicador> indicadores = new ArrayList<>();

    public ArrayList<Indicador> getIndicadores() {
        return indicadores;
    }

    public void agregarIndicador(Indicador indicador) {
        indicadores.add(indicador);
    }

    public double getPresupuesto(Indicador indicador) {
        double presupuestoTotal = 0;
        for (Proyecto proyecto : indicador.getProyectosRelacionados()) {
            presupuestoTotal += proyecto.getPresupuesto();
        }
        return presupuestoTotal;
    }

    public double calcularPromedioDineroGastado(Indicador indicador) {
        double totalDineroGastado = indicador.getPresupuesto();
        return indicador.getVecesUsado() == 0 ? 0 : totalDineroGastado / indicador.getVecesUsado();
    }

    public int getVecesUsado(Proyecto proyecto) {
        int totalVecesUsado = 0;
        for (Indicador indicador : proyecto.getIndicadores()) {
            totalVecesUsado++;
        }
        return totalVecesUsado;
    }

    //===========================================================

    public String obtenerResultadoMedicion(Indicador indicador) {
        int valorCumplimiento = indicador.getValorCumplimiento();

        if (valorCumplimiento >= 0 && valorCumplimiento <= 29) {
            return "Pésimo";
        } else if (valorCumplimiento >= 30 && valorCumplimiento <= 54) {
            return "Vamos mal";
        } else if (valorCumplimiento >= 55 && valorCumplimiento <= 74) {
            return "Puede mejorar";
        } else if (valorCumplimiento >= 75 && valorCumplimiento <= 89) {
            return "Vamos bien";
        } else if (valorCumplimiento >= 90 && valorCumplimiento <= 100) {
            return "Excelente";
        } else {
            return "Valor fuera de rango";
        }
    }

    //===========================================================

    public ArrayList<Indicador> obtenerIndicadoresMenosUsados() {
        ArrayList<Indicador> menosUsados = new ArrayList<>();
        int menorVecesUsado = Integer.MAX_VALUE;
    
        for (Indicador indicador : indicadores) {
            if (indicador.getVecesUsado() < menorVecesUsado) {
                menosUsados.clear();
                menosUsados.add(indicador);
                menorVecesUsado = indicador.getVecesUsado();
            } else if (indicador.getVecesUsado() == menorVecesUsado) {
                menosUsados.add(indicador);
            }
        }
    
        return menosUsados;
    }

}