package Vistas;

import Controladores.c_componente;
import Controladores.c_indicador;
import Controladores.c_proyecto;
import Controladores.c_tema;
import Modelos.Componente;
import Modelos.Indicador;
import Modelos.Proyecto;
import Modelos.Tema;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Interfaz {

    //Instancias

    c_componente componenteControlador = new c_componente();
    c_tema temaControlador = new c_tema();
    c_indicador indicadorControlador = new c_indicador();
    c_proyecto proyectoControlador = new c_proyecto();

    //===============================================================================//

    public int contador = 0;

    public int leerUnaVez() {
        if (contador == 0) {
            String rutaArchivo = "C:\\Users\\mario\\OneDrive\\Escritorio\\Datos que se almacenan\\Componentes.txt";
            componenteControlador.cargarComponentes(rutaArchivo);
            temaControlador.setTemasGlobales(componenteControlador.getTemasGlobales());
            System.out.println("Archivo cargado por primera vez, exitosamente");
            contador = 1;
        }
        return contador;
    }

    //============================= MENÚ PRINCIPAL =================================//

    public void menuPrincipal() {
        int opc;
        do {

            if (contador == 0) {
                leerUnaVez();
            }

            Object[] opciones = { "Proyectos", "Indicadores", "Temas", "Componentes", "Salir" };
            opc = JOptionPane.showOptionDialog(null,
                    "¡Bienvenido Usuario!, Seleccione una opción:\n",
                    "Menú Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, opciones, opciones[0]);

            switch (opc) {
                case 0:
                    BotonProyectos();
                    break;
                case 1:
                    BotonIndicadores();
                    break;
                case 2:
                    BotonTemas();
                    break;
                case 3:
                    BotonComponentes();
                    break;
            }
        } while (opc != 4);
    }

    //============================ BOTÓN PROYECTOS ============================//

    public int BotonProyectos() {
        int opc = -1;

        Object[] opciones = {
                "Ver listado de proyectos con el presupuesto asignado.",
                "Ver todos los proyectos activos.",
                "Generar ciclo de proyectos.",
                "Volver"
        };
        do {
            opc = JOptionPane.showOptionDialog(null,
                    "¡Bienvenido Usuario!, Aquí puedes:\n"
                            + "\n" + "1| Ver listado de proyectos con el presupuesto asignado.\n"
                            + "2| Ver todos los proyectos activos.\n"
                            + "3| Generar ciclo de proyectos.\n",
                    "LOGIN",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, opciones, opciones[0]);

            switch (opc) {
                case 0:
                    presentarProyectosPorPresupuesto();
                    break;
                case 1:
                    verProyectosActivos();
                    break;
                case 2:
                    CicloProyectos();
                    break;
            }
        } while (opc != 3);
        return opc;
    }

    //============================ BOTÓN INDICADORES =============================//

    public int BotonIndicadores() {
        int opc = -1;

        Object[] opciones = {
                "Ver un listado con los indicadores menos utilizados.",
                "Ver un listado con los indicadores con mayor presupuesto.",
                "Ver un listado con el promedio del dinero gastado por indicador.",
                "Volver"
        };
        do {
            opc = JOptionPane.showOptionDialog(null,
                    "¡Bienvenido Usuario!, Aquí puedes:\n"
                            + "\n" + "1| Ver un listado con los indicadores menos utilizados.\n"
                            + "2| Ver un listado con los indicadores con mayor presupuesto.\n"
                            + "3| Ver el promedio del dinero gastado por indicador.\n",
                    "LOGIN",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, opciones, opciones[0]);

            switch (opc) {
                case 0:
                    listarIndicadoresMenosUsados();
                    break;
                case 1:
                    presentarIndicadoresPorPresupuesto();
                    break;
                case 2:
                    mostrarPromedioDineroGastadoIndicador();
                    break;
            }
        } while (opc != 3);
        return opc;
    }

    //============================ BOTÓN TEMAS ===================================//

    public int BotonTemas() {
        int opc = -1;

        Object[] opciones = {
                "Ver el tema con mayor porcentaje de cumplimiento",
                "Volver"
        };
        do {
            opc = JOptionPane.showOptionDialog(null,
                    "¡Bienvenido Usuario!, Aquí puedes:\n"
                            + "\n" + "1| Ver el tema con mayor porcentaje de cumplimiento.\n",
                    "LOGIN",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, opciones, opciones[0]);

            switch (opc) {
                case 0:
                    mostrarCalculoMayorCumplimiento();
                    break;
            }
        } while (opc != 1);
        return opc;
    }

    // ============================ BOTÓN COMPONENTES =============================//

    public int BotonComponentes() {
        int opc = -1;
        Object[] opciones = {
                "Ver el reporte de cumplimiento de cada componente.",
                "Examinar los componentes.",
                "Volver"
        };
        do {
            opc = JOptionPane.showOptionDialog(null,
                    "¡Bienvenido Usuario!, Aquí puedes:\n"
                            + "\n" + "1| Ver el reporte de cumplimiento de cada componente.\n"
                            + "2| Examinar los componentes.\n",
                    "LOGIN",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, opciones, opciones[0]);
            switch (opc) {
                case 0:
                    mostrarReporteCumplimiento();
                    break;
                case 1:
                    cargarYExaminarComponentes();
                    break;
            }
        } while (opc != 2);
        return opc;
    }

    // ============================ BOTÓN SECUNDARIO RECALCULO PROYECTOS =============================// #### IMPORTANTE -===-===-===-===-===-===-===-===-===-===-

    public int BotonRecalculo() {
        int opc = -1;
        Object[] opciones = {
                "Realizar el recalculo de los proyectos",
                "Proceder con los proyectos sin realizar un recálculo",
                "Volver"
        };

        do {
            opc = JOptionPane.showOptionDialog(null,
                    "Los proyectos se han validado exitosamente, ¿Que deseas realizar?\n" +
                            "1| Realizar recalculo y validación de los proyectos generados\n" +
                            "2| Proceder con los proyectos generados y validar automáticamente",
                    "LOGIN",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, opciones, opciones[0]);

            switch (opc) {
                case 0:
                    BotonRecalculoConfirmadoUsuario();
                    break;
                case 1:
                    BotonSinRecalculoConfirmado();
                    break;
            }

        } while (opc != 2);
        return opc;
    }

    //=##################################################################################################################################################=//

    //===-===-===-===-===-===-===-===-===-===-===-===-===-===-===-===-===-===-===-===-===-==== MÉTODOS BÓTONES SECUNDARIOS

    //======================================================================================== Método para recalcular los proyectos generados

    public void BotonRecalculoConfirmadoUsuario() {
        ArrayList<Proyecto> proyectos = proyectoControlador.getProyectos();
        double presupuestoMin = 1000.0; 
        double presupuestoMax = 10000.0; 
    
        for (Proyecto proyecto : proyectos) {
            double presupuestoProyecto = generarValorAleatorioP(presupuestoMin, presupuestoMax);
            ArrayList<Indicador> indicadoresRelacionados = proyecto.getIndicadoresRelacionados();
    
            for (Indicador indicador : indicadoresRelacionados) {
                int porcentajeCumplimiento = generarValorAleatorio(0, 100);
                int nuevoValorCumplimiento = calcularNuevoValorCumplimiento(indicador.getValorCumplimiento(), porcentajeCumplimiento, indicador.getVecesUsado());
                indicador.setValorCumplimiento(nuevoValorCumplimiento);
                indicador.setVecesUsado(indicador.getVecesUsado() + 1);
                indicador.setPresupuesto(indicador.getPresupuesto()
                        + (presupuestoProyecto / indicadoresRelacionados.size()));
            }
            proyecto.setPresupuesto(presupuestoProyecto);
        }

        guardarProyectosEnArchivo(proyectos);
    
        JOptionPane.showMessageDialog(null, "Proyectos recalculados exitosamente");
    }
    

    private double generarValorAleatorioP(double min, double max) {
        return (min + (Math.random() * (max - min)));
    }

    //Valor aleatorio para el porcentaje de cumplimiento
    private int generarValorAleatorio(int min, int max) {
        return (int) (min + (Math.random() * (max - min)));
    }


    private int calcularNuevoValorCumplimiento(int valorActual, int porcentajeCumplimiento, int vecesUsado) {
        return ((valorActual * vecesUsado) + porcentajeCumplimiento) / (vecesUsado + 1);
    }

    // ======== Método que escribe los proyectos en el archivo

    private void guardarProyectosEnArchivo(ArrayList<Proyecto> proyectos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Proyectos.txt", true))) {
            for (Proyecto proyecto : proyectos) {
                writer.write(proyecto.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar los proyectos en el archivo: " + e.getMessage());
        }
    }

    //======================================================================================== Método para mostrar los proyectos para revisión y validación

    private void presentarProyectosParaRevision(ArrayList<Proyecto> proyectos) {
        int opc;
        do {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Proyecto");
            model.addColumn("Presupuesto");
            model.addColumn("Estado");
            model.addColumn("Indicadores");

            for (int i = 0; i < proyectos.size(); i++) {
                Proyecto proyecto = proyectos.get(i);
                String estado = proyecto.getEstado() == 1 ? "Activo" : "Inactivo";
                String nombresIndicadores = proyecto.getIndicadores().stream()
                        .map(Indicador::getNombreIndicador)
                        .collect(Collectors.joining(", "));
                        
                model.addRow(new Object[]{i, proyecto.getNombre(), proyecto.getPresupuesto(), estado, nombresIndicadores});
            }

            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(800, 400));

            Object[] opciones = {"Eliminar un Proyecto", "Validar Todos los Proyectos"};
            opc = JOptionPane.showOptionDialog(null, scrollPane, "Validación de Proyectos Exitosa",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            if (opc == 0) { // Eliminar Proyecto
                String input = JOptionPane.showInputDialog(null, "Ingrese la posición del proyecto a eliminar:");
                try {
                    int id = Integer.parseInt(input);
                    if (id >= 0 && id < proyectos.size()) {
                        proyectos.remove(id);
                    } else {
                        JOptionPane.showMessageDialog(null, "Posición de proyecto inválido.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Posición de proyecto inválido.");
                }
            }
        } while (opc == 0);

        JOptionPane.showMessageDialog(null, "Proyectos validados exitosamente.");
        BotonRecalculo();
    }

    // ======================================================================================= botón para continuar sin recalcular los proyectos

    private void BotonSinRecalculoConfirmado() {
        JOptionPane.showMessageDialog(null, "Proyectos validados de forma automática");
    }

    // ======================================================================================= Método para el botón de la lista de proyectos por presupuesto 

    private void presentarProyectosPorPresupuesto() {
        ArrayList<Proyecto> proyectos = proyectoControlador.getProyectos();
        Collections.sort(proyectos, (p1, p2) -> Double.compare(p2.getPresupuesto(), p1.getPresupuesto()));

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Proyecto");
        model.addColumn("Presupuesto");
        model.addColumn("Estado");
        model.addColumn("Indicadores Asociados");

        for (Proyecto proyecto : proyectos) {
            String estado = proyecto.getEstado() == 1 ? "Activo" : "Inactivo";
            String nombresIndicadores = proyecto.getIndicadores().stream()
                    .map(Indicador::getNombreIndicador)
                    .reduce((acc, nombre) -> acc + ", " + nombre)
                    .orElse("");

            model.addRow(new Object[]{proyecto.getNombre(), proyecto.getPresupuesto(), estado, nombresIndicadores});
        }

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 400));

        JOptionPane.showMessageDialog(null, scrollPane, "Listado de Proyectos por Presupuesto",
                JOptionPane.PLAIN_MESSAGE);
    }

    // ======================================================================================= Metodo para el botón de los proyectos activos

    private void verProyectosActivos() {
        ArrayList<Proyecto> proyectosActivos = proyectoControlador.getProyectosActivos();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Proyecto");
        model.addColumn("Estado");
        model.addColumn("Presupuesto del Proyecto");
        model.addColumn("Indicadores");
        model.addColumn("Presupuesto de Indicadores");

        for (Proyecto proyecto : proyectosActivos) {
            String nombresIndicadores = proyecto.getIndicadores().stream()
                    .map(Indicador::getNombreIndicador)
                    .reduce((acc, nombre) -> acc + ", " + nombre)
                    .orElse("");

            String presupuestosIndicadores = proyecto.getIndicadores().stream()
                    .map(indicador -> String.valueOf(indicador.getPresupuesto()))
                    .reduce((acc, presupuesto) -> acc + ", " + presupuesto)
                    .orElse("");

            model.addRow(new Object[]{
                    proyecto.getNombre(),
                    "Activo",
                    proyecto.getPresupuesto(),
                    nombresIndicadores, 
                    presupuestosIndicadores
            });
        }

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000, 400));

        JOptionPane.showMessageDialog(null, scrollPane, "Listado de Proyectos Activos",
                JOptionPane.PLAIN_MESSAGE);
    }

    // ======================================================================================= -=== IMPORTANTE ===- Metodo para el botón que genera los proyectos

    private int contador2 = 0;

    public int UnaVez() {
        ArrayList<Tema> temas = temaControlador.getTemas();
        ArrayList<Indicador> indicadores = indicadorControlador.getIndicadores();

        if (contador2 == 0) {
            for (Tema tema : temas) {
                indicadores.addAll(tema.getIndicadores());
            }
            contador2 = 1; 
            System.out.println("Contador del ciclo proyectos usado");
        }
        return contador2;
    }

    private void CicloProyectos() {

        Random rand = new Random();
        
        ArrayList<Tema> temas = temaControlador.getTemas();
        int numeroProyectos = rand.nextInt(temas.size() - 1) + 2;

        proyectoControlador.generarProyectosAleatorios(numeroProyectos);
        ArrayList<Proyecto> proyectos = proyectoControlador.getProyectos();
        ArrayList<Indicador> indicadores = indicadorControlador.getIndicadores();

        UnaVez();

        if (!proyectos.isEmpty() && !indicadores.isEmpty()) {
            asociarProyectosConIndicadores(proyectos, indicadores, temas);
            actualizarIndicadoresConProyectos(proyectos, indicadores);
        } else {
            System.err.println("No hay proyectos o indicadores disponibles para asociar.");
        }

        presentarProyectosParaRevision(proyectos);
    }

    //Método para asociar proyectos con indicadores

    private void asociarProyectosConIndicadores(ArrayList<Proyecto> proyectos, ArrayList<Indicador> indicadores, ArrayList<Tema> temas) {
        Random rand = new Random();
        int numIndicadores = indicadores.size();
    
        if (numIndicadores > 0 && !temas.isEmpty()) {
            for (Proyecto proyecto : proyectos) {
                int numIndicadoresProyecto = rand.nextInt(8) + 2;
                ArrayList<Indicador> indicadoresProyecto = new ArrayList<>();
                ArrayList<Indicador> indicadoresUtilizados = new ArrayList<>(); // Almacenar los indicadores ya utilizados
    
                for (int i = 0; i < numIndicadoresProyecto; i++) {
                    // Seleccionar un indicador aleatorio que no haya sido utilizado antes
                    Indicador indicadorSeleccionado;
                    do {
                        indicadorSeleccionado = indicadores.get(rand.nextInt(numIndicadores));
                    } while (indicadoresUtilizados.contains(indicadorSeleccionado));
                    indicadoresUtilizados.add(indicadorSeleccionado);
    
                    indicadoresProyecto.add(indicadorSeleccionado);
                }
    
                proyecto.setIndicadores(indicadoresProyecto);
            }
        } else {
            System.err.println("La lista de indicadores o de temas está vacía.");
        }
    }

    //Método para actualizar los indicadores con los proyectos

    private void actualizarIndicadoresConProyectos(ArrayList<Proyecto> proyectos, ArrayList<Indicador> indicadores) {
        for (Proyecto proyecto : proyectos) {
            double presupuestoProyecto = proyecto.getPresupuesto();
            ArrayList<Indicador> indicadoresProyecto = proyecto.getIndicadores();

            for (Indicador indicador : indicadoresProyecto) {
                indicador.setVecesUsado(indicador.getVecesUsado() + 1);
                indicador.setPresupuesto(
                        indicador.getPresupuesto() + (presupuestoProyecto / indicadoresProyecto.size()));
            }
        }
    }

    //======================================================================================= Método para el botón de la lista de indicadores menos usados


    private void listarIndicadoresMenosUsados() {
        ArrayList<Indicador> indicadores = indicadorControlador.getIndicadores();

        UnaVez();

        Collections.sort(indicadores, (i1, i2) -> Integer.compare(i1.getVecesUsado(), i2.getVecesUsado()));

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre Indicador");
        model.addColumn("Presupuesto");
        model.addColumn("Veces Usado");

        for (Indicador indicador : indicadores) {
            model.addRow(new Object[] { indicador.getNombreIndicador(), indicador.getPresupuesto(),
                    indicador.getVecesUsado() });
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        JOptionPane.showMessageDialog(null, scrollPane, "Indicadores Menos Utilizados", JOptionPane.PLAIN_MESSAGE);
    }

    //======================================================================================= Método para el botón de los indicadores por presupuesto

    private void presentarIndicadoresPorPresupuesto() {
        ArrayList<Indicador> indicadores = indicadorControlador.getIndicadores();

        UnaVez();

        Collections.sort(indicadores, (i1, i2) -> Double.compare(i2.getPresupuesto(), i1.getPresupuesto()));
    
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre Indicador");
        model.addColumn("Presupuesto");
        model.addColumn("Veces Usado");
    
        for (Indicador indicador : indicadores) {
            model.addRow(new Object[] { indicador.getNombreIndicador(), indicador.getPresupuesto(), indicador.getVecesUsado() });
        }
    
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));
    
        JOptionPane.showMessageDialog(null, scrollPane, "Indicadores por Presupuesto", JOptionPane.PLAIN_MESSAGE);
    }

    //======================================================================================= Método para el botón del promedio gastado por indicador

    private void mostrarPromedioDineroGastadoIndicador() {
        ArrayList<Indicador> indicadores = indicadorControlador.getIndicadores();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Indicador");
        model.addColumn("Promedio gastado");
        model.addColumn("Presupuesto");
        model.addColumn("Veces Usado");
        model.addColumn("Valor Cumplimiento");
        model.addColumn("Descripción");
        model.addColumn("Resultado Medición");  // Nueva columna

        for (Indicador indicador : indicadores) {
            double promedio = indicadorControlador.calcularPromedioDineroGastado(indicador);
            String resultadoMedicion = indicadorControlador.obtenerResultadoMedicion(indicador);

            model.addRow(new Object[]{
                indicador.getNombreIndicador(),
                promedio,
                indicador.getPresupuesto(),
                indicador.getVecesUsado(),
                indicador.getValorCumplimiento(),
                indicador.getDescripcion(),
                resultadoMedicion 
            });
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000, 600));
        JOptionPane.showMessageDialog(null, scrollPane, "Promedio de Dinero Gastado por Indicador",
                JOptionPane.PLAIN_MESSAGE);
    }

    //======================================================================================= Método para el botón de el calculo del tema con más cumplimiento

    private void mostrarCalculoMayorCumplimiento() {
        ArrayList<Tema> temas = temaControlador.getTemas();

        Tema temaMayorCumplimiento = null;
        int mayorCumplimiento = 0;

        for (Tema tema : temas) {
            int sumaCumplimiento = 0;
            int totalIndicadores = tema.getIndicadores().size();

            for (Indicador indicador : tema.getIndicadores()) {
                if (indicador.getValorCumplimiento() >= 0.0 && indicador.getValorCumplimiento() <= 100.0) {
                    sumaCumplimiento += indicador.getValorCumplimiento();
                }
            }

            if (totalIndicadores > 0) {
                int promedioCumplimiento = sumaCumplimiento / totalIndicadores;

                if (promedioCumplimiento > mayorCumplimiento) {
                    mayorCumplimiento = promedioCumplimiento;
                    temaMayorCumplimiento = tema;
                }
            }
        }

        String mensaje = "Tema con mayor cumplimiento: "
                + (temaMayorCumplimiento != null ? temaMayorCumplimiento.getNombreTema() : "Ninguno") + " - Valor: "
                + mayorCumplimiento + "%";
        JOptionPane.showMessageDialog(null, mensaje);
    }

    //======================================================================================= Método para el botón del reporte de cumplimiento por componente

    private void mostrarReporteCumplimiento() {
        ArrayList<Componente> componentes = componenteControlador.getComponentes();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Componente");
        model.addColumn("Cumplimiento");
        model.addColumn("Tema");
        model.addColumn("Cumplimiento del Tema");
        model.addColumn("Resultado Medición");

        for (Componente componente : componentes) {
            for (Tema tema : componente.getTemas()) {
                int valorCumplimientoTema = temaControlador.getValorCumplimientoTema(tema);
                int PorcentajeCump = componenteControlador.getPorcentajeCumplimiento(componente);
                String resultadoMedicion = temaControlador.obtenerResultadoMedicion(valorCumplimientoTema);
        
                model.addRow(new Object[]{
                        componente.getNomComponente(),
                        PorcentajeCump + "%",
                        tema.getNombreTema(),
                        valorCumplimientoTema + "%",
                        resultadoMedicion
                });
            }
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        JOptionPane.showMessageDialog(null, scrollPane, "Reporte de Cumplimiento de Componentes y Temas", JOptionPane.PLAIN_MESSAGE);
    }

    //======================================================================================= Método para cargar y examinar componentes desde el archivo

    private void cargarYExaminarComponentes() {
        ArrayList<Componente> componentes = componenteControlador.getComponentes();
        
        String inputCodigo = JOptionPane.showInputDialog("Ingrese el código del componente:");
        if (inputCodigo != null) {
            mostrarCumplimientoComponente(inputCodigo, componentes);
        }
    }

    private void mostrarCumplimientoComponente(String codigo, ArrayList<Componente> componentes) {
        Componente componenteSeleccionado = null;
        for (Componente componente : componentes) {
            if (componente.getCodComponente().equals(codigo)) {
                componenteSeleccionado = componente;
                break;
            }
        }

        if (componenteSeleccionado != null) {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Tema");
            model.addColumn("Valor de Cumplimiento");
            model.addColumn("Resultado Medición");

            for (Tema tema : componenteSeleccionado.getTemas()) {
                int valorCumplimientoTema = temaControlador.getValorCumplimientoTema(tema);
                String resultadoMedicion = temaControlador.obtenerResultadoMedicion(valorCumplimientoTema);

                model.addRow(new Object[]{tema.getNombreTema(), valorCumplimientoTema + "%", resultadoMedicion});
            }

            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(600, 150));
            JOptionPane.showMessageDialog(null, scrollPane, "Porcentaje de Cumplimiento del Componente " + codigo, JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el componente con el código: " + codigo, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //===============================================================================================

}
