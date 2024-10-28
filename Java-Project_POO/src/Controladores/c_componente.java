package Controladores;

import Modelos.Componente;
import Modelos.Tema;
import Modelos.Indicador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class c_componente {

    // El array donde están todos los componentes
    private ArrayList<Componente> componentes = new ArrayList<>();
    // El array donde están todos los temas
    private ArrayList<Tema> temas = new ArrayList<>();

    //============================================================================= Método para cargar componentes desde un archivo
    public void cargarComponentes(String rutaArchivo) {

        Componente componente = null;
        Tema tema = null;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(";", 5);
                String primerDato = datos[0];

                //========================================== Llenar los componentes
                if (primerDato.length() == 3) {
                    // Es un componente
                    componente = new Componente();
                    componente.setCodComponente(primerDato);
                    componente.setNomComponente(datos[1]);
                    componente.setTemas(new ArrayList<>());
                    componentes.add(componente);

                //========================================== Llenar los Temas
                } else if (primerDato.length() == 5) {
                    // Es un tema
                    tema = new Tema();
                    tema.setNombreTema(datos[1]);
                    tema.setIndicadores(new ArrayList<>());
                    temas.add(tema); // Agregar el tema al ArrayList global de temas
                    if (componente != null) {
                        componente.getTemas().add(tema);
                    }

                //========================================== Llenar los Indicadores
                } else if (primerDato.length() == 7) {
                    // Es un indicador
                    Indicador indicador = new Indicador(datos[1], datos[2], 0.0, 0, 0);
                    if (tema != null) {
                        tema.getIndicadores().add(indicador);
                    }
                }
                //========================================== Fin
            }
            System.out.println("Componentes y temas cargados correctamente."); // Mensaje de depuración
        } catch (IOException e) {
            System.err.println("Error al cargar componentes desde el archivo: " + e.getMessage());
        }
    }

    public void asignarTemas(Componente componente, ArrayList<Tema> temas) {
        componente.setTemas(temas);
    }

    public ArrayList<Componente> getComponentes() {
        System.out.println("Obteniendo componentes. Total: " + componentes.size()); 
        return componentes;
    }

    public ArrayList<Tema> getTemasGlobales() {
        return temas;
    }

    //==================================================================

    public String obtenerResultadoMedicion(Componente componente) {
        int porcentajeCumplimiento = componente.getPorcentajeCumplimiento();

        if (porcentajeCumplimiento >= 0 && porcentajeCumplimiento <= 29) {
            return "Pésimo";
        } else if (porcentajeCumplimiento >= 30 && porcentajeCumplimiento <= 54) {
            return "Vamos mal";
        } else if (porcentajeCumplimiento >= 55 && porcentajeCumplimiento <= 74) {
            return "Puede mejorar";
        } else if (porcentajeCumplimiento >= 75 && porcentajeCumplimiento <= 89) {
            return "Vamos bien";
        } else if (porcentajeCumplimiento >= 90 && porcentajeCumplimiento <= 100) {
            return "Excelente";
        } else {
            return "Valor fuera de rango";
        }
    }

    public int getPorcentajeCumplimiento(Componente componente) {
        int totalCumplimiento = 0;
        int totalTemas = 0;
        for (Tema tema : componente.getTemas()) {
            totalCumplimiento += tema.getValorCumplimientoTema();
            totalTemas++;
        }
    
        double promedioCumplimiento = (double) (totalCumplimiento / totalTemas);
    
        return (int) Math.round(promedioCumplimiento);
    }
}