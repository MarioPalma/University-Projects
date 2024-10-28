package Controladores;

import Modelos.Tema;
import Modelos.Indicador;

import java.util.ArrayList;

public class c_tema {

    private ArrayList<Tema> temas = new ArrayList<>();

    //=================================================================== Método para cargar temas  ===========================================//

    public void setTemasGlobales(ArrayList<Tema> temasGlobales) {
        this.temas = temasGlobales;
    }

    //======================================================================================== FIN MÉTODO ====================================//


    public void asignarIndicadores(Tema tema, ArrayList<Indicador> indicadores) {
        tema.setIndicadores(indicadores);
    }

    public ArrayList<Tema> getTemas() {
        System.out.println("Obteniendo temas. Total: " + temas.size()); 
        return temas;
    }

    //===========================================

    public int getValorCumplimientoTema(Tema tema) {
        int totalCumplimiento = 0;
        int totalIndicadores = tema.getIndicadores().size();
    
        for (Indicador indicador : tema.getIndicadores()) {
            totalCumplimiento += indicador.getValorCumplimiento();
        }
    
        int promedioCumplimiento = totalCumplimiento / totalIndicadores;
    
        return (int) Math.round(promedioCumplimiento);
    }

    //==========================================

    public String obtenerResultadoMedicion(int totalCumplimiento) {
        if (totalCumplimiento >= 0 && totalCumplimiento <= 29) {
            return "Pésimo";
        } else if (totalCumplimiento >= 30 && totalCumplimiento <= 54) {
            return "Vamos mal";
        } else if (totalCumplimiento >= 55 && totalCumplimiento <= 74) {
            return "Puede mejorar";
        } else if (totalCumplimiento >= 75 && totalCumplimiento <= 89) {
            return "Vamos bien";
        } else if (totalCumplimiento >= 90 && totalCumplimiento <= 100) {
            return "Excelente";
        } else {
            return "Valor fuera de rango";
        }
    }

    //==========================================

    public Tema calcularTemaMayorCumplimiento() {
        Tema temaMayorCumplimiento = null;
        int mayorCumplimiento = 0;

        for (Tema tema : temas) {
            int cumplimientoTema = 0;
            int totalIndicadores = tema.getIndicadores().size();
            for (Indicador indicador : tema.getIndicadores()) {
                cumplimientoTema += indicador.getValorCumplimiento();
            }
            int promedioCumplimiento = (cumplimientoTema / totalIndicadores);
            if (promedioCumplimiento > mayorCumplimiento) {
                mayorCumplimiento = promedioCumplimiento;
                temaMayorCumplimiento = tema;
            }
        }

        return temaMayorCumplimiento;
    }
}
