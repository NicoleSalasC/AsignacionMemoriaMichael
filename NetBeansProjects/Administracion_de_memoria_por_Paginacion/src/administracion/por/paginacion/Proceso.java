/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package administracion.por.paginacion;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que presenta las caracteristicas de los procesos en un sistema de
 * paginacion y proporciona metodos para la asignacion de la memoria y su
 * estado, 0: disponible, 1: en ejecución en memoria principal, 2: en memoria
 * secundaria, -1: finalizado.
 *
 * @author Ashley Hernandez
 * @author Jeison Alvarez
 * @author Nicole Salas
 * @author Jocelyn Abarca
 *
 */
public class Proceso {

    private int NumPaginacion;
    private String NombreProceso;
    private int estado;
    /**
     * Constructor con parámetros para crear un nuevo proceso.
     *
     * @param NumPaginacion número de paginación del proceso
     * @param NombreProceso nombre del proceso
     * @param estado estado del proceso (0: disponible, 1: en ejecución en
     * memoria principal, 2: en memoria secundaria, -1: finalizado)
     */
    public Proceso(int NumPaginacion, String NombreProceso, int estado) {
        this.NumPaginacion = NumPaginacion;
        this.NombreProceso = NombreProceso;
        this.estado = estado;
    }
    /**
     * Constructor vacío para la creación de procesos sin inicialización de
     * atributos.
     */
    public Proceso() {
    }
    /**
     * Obtiene el número de paginación del proceso.
     *
     * @return número de paginación del proceso
     */
    public int getNumPaginacion() {
        return NumPaginacion;
    }
    /**
     * Establece el número de paginación del proceso.
     *
     * @param NumPaginacion número de paginación del proceso
     */
    public void setNumPaginacion(int NumPaginacion) {
        this.NumPaginacion = NumPaginacion;
    }
    /**
     * Obtiene el nombre del proceso.
     *
     * @return nombre del proceso
     */
    public String getNombreProceso() {
        return NombreProceso;
    }
    /**
     * Establece el nombre del proceso.
     *
     * @param NombreProceso nombre del proceso
     */
    public void setNombreProceso(String NombreProceso) {
        this.NombreProceso = NombreProceso;
    }
    public int isEstado() {
        return estado;
    }
    /**
     * Establece el estado del proceso.
     *
     * @param estado estado del proceso
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }
    /**
     * Obtiene el estado del proceso.
     *
     * @return estado del proceso
     */
    public int getEstado() {
        return estado;
    }
    /**
     * Devuelve una representación en cadena del proceso, que incluye el nombre,
     * número de paginación y estado.
     *
     * @return representación en cadena del proceso
     */
    @Override
    public String toString() {
        return "Nombre del proceso: " + NombreProceso + " -- Numero de paginacion: " + NumPaginacion + " -- Estado: " + estado + '}';
    }
    /**
     * Atributos para la gestion de procesos y memoria
     */
    ArrayList<Proceso> memoriaProcesos = new ArrayList<>();
    String[] memoriaPrincipal = new String[8];
    String[] memoriaSecundaria = new String[16];
    Scanner scanner = new Scanner(System.in);
    /**
     * Metodo para agregar un nuevo proceso a la lista de procesos.
     */
    public void addProcess() {
        System.out.println("Ingrese el nombre del proceso:");
        NombreProceso = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Ingrese el numero de paginacion:");
        NumPaginacion = scanner.nextInt();
        scanner.nextLine();
        estado = 0;
        Proceso nuevoProceso = new Proceso(NumPaginacion, NombreProceso, estado);
        memoriaProcesos.add(nuevoProceso);
        System.out.println("Proceso ingresado corretamente");
    }
    /**
     * Metodo para mostrar la lista de procesos disponibles para ejecutar.
     */
    public void mostrarProcesosDisponibles() {
        System.out.println("Lista de procesos almacenados:\n");
        for (Proceso proceso : memoriaProcesos) {
            if (proceso.getEstado() == 0) {
                System.out.println(proceso.toString());
            }
        }
    }
    /**
     * Metodo para mostrar los procesos que estan en ejecucion.
     */
    public void mostrarProcesosEj() {
        System.out.println("Lista de procesos almacenados:\n");
        for (Proceso proceso : memoriaProcesos) {
            if (proceso.getEstado() != 0) {
                System.out.println(proceso.toString());
            }
        }
    }
    /**
     * Metodo que muestra la lista de procesos almacenados.
     */
    public void mostrarProcesos() {
        System.out.println("Lista de procesos almacenados:\n");
        for (Proceso proceso : memoriaProcesos) {
            System.out.println(proceso.toString());
        }
    }
    /**
     * Metodo que muestra la lista de los procesos finalizados.
     */
    public void mostrarProcesosFinalizados() {
        System.out.println("Lista de procesos finalizados:\n");
        for (Proceso proceso : memoriaProcesos) {
            if (proceso.getEstado() == -1) {
                System.out.println(proceso.toString());
            }
        }
    }
    /**
     * Asigna un proceso a la memoria principal si hay suficiente espacio.
     *
     * @param procesito el proceso a asignar la memoria principal.
     */
    public void asignarMemoriaPrincipal(Proceso procesito) {
        int paginasAsignadas = 0;
        int marcosLibres = 0;
        System.out.println("-------------------------------");
        for (int i = 0; i < memoriaPrincipal.length; i++) {
            if (memoriaPrincipal[i] == null) {
                marcosLibres++;
            }
        }
        boolean procesoEnEjecucion = false;
        for (int i = 0; i < memoriaPrincipal.length; i++) {
            if (memoriaPrincipal[i] != null && memoriaPrincipal[i].equals(procesito.getNombreProceso())) {
                System.out.println("El proceso ya se esta ejecutando >:c");
                procesoEnEjecucion = true;
                break;
            }
        }
        if (!procesoEnEjecucion) {
            if (marcosLibres >= procesito.getNumPaginacion()) {
                for (int i = 0; i < memoriaPrincipal.length && paginasAsignadas < procesito.getNumPaginacion(); i++) {
                    if (memoriaPrincipal[i] == null) {
                        memoriaPrincipal[i] = procesito.NombreProceso;
                        paginasAsignadas++;
                        procesito.setEstado(1);
                    }
                }
            } else {
                System.out.println("No hay espacio en la memoria principal :(");
                AdministracionPorPaginacion.imprimirMenu();
            }
            System.out.println("Marco principal\n");
            for (int i = 0; i < memoriaPrincipal.length; i++) {
                System.out.println(" [" + i + "]: " + memoriaPrincipal[i]);
            }
        }
    }
    /**
     * Asigna un proceso a la memoria secundaria si hay suficiente espacio
     * disponible.
     *
     * @param procesito el proceso a asignar la memoria secundaria.
     *
     */
    public void asignarMemoriaSecundaria(Proceso procesito) {
        int paginasAsignadas = 0;
        int marcosLibres = 0;
        for (int i = 0; i < memoriaSecundaria.length; i++) {
            if (memoriaSecundaria[i] == null) {
                marcosLibres++;
            }
        }
        for (int i = 0; i < memoriaSecundaria.length; i++) {
            if (memoriaSecundaria[i] != null && memoriaSecundaria[i].equals(procesito.getNombreProceso())) {
                System.out.println("El proceso ya se esta ejecutando >:c");
                break;
            }
        }

        if (marcosLibres >= procesito.getNumPaginacion()) {
            for (int i = 0; i < memoriaSecundaria.length && paginasAsignadas < procesito.getNumPaginacion(); i++) {
                if (memoriaSecundaria[i] == null) {
                    memoriaSecundaria[i] = procesito.NombreProceso;
                    paginasAsignadas++;
                }
            }
            procesito.setEstado(2);
            System.out.println("El proceso '" + procesito.getNombreProceso() + "' ha sido movido a la memoria secundaria");
        } else {
            System.out.println("No hay suficiente espacio en la memoria secundaria para mover el proceso: " + procesito.getNombreProceso());
        }
    }
    /**
     * Ejecuta un proceso moviéndolo a la memoria principal o secundaria según
     * la elección del usuario.
     *
     * @param procesito el proceso a ejecutar
     */
    public void ejecutarProceso(Proceso procesito) {

        System.out.println("A donde quiere mandar este proceso? \n 1. Memoria principal \n 2. Memoria secundaria");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                asignarMemoriaPrincipal(procesito);
                break;
            case 2:
                asignarMemoriaSecundaria(procesito);
                break;
            default:
                System.out.println("Opcion invalida");
        }
    }
    /**
     * Valida la existencia de un proceso y lo ejecuta si está disponible.
     */
    public void validacion() {
        boolean validacion = false;
        Proceso procesito = null;
        mostrarProcesosDisponibles();
        System.out.println("-------------------------------");
        System.out.println("Cual proceso quiere ejecutar? \n");
        String respuesta = scanner.next().trim();
        for (Proceso m : memoriaProcesos) {
            if (m.getNombreProceso().equals(respuesta)) {
                procesito = m;
                validacion = true;
                break;
            }
        }
        if (validacion) {
            ejecutarProceso(procesito);
        } else {
            System.out.println("Proceso inexistente");
        }
    }
    /**
     * Libera los marcos ocupados por un proceso en la memoria.
     *
     * @param memoria arreglo de memoria principal o secundaria
     * @param nombreProceso nombre del proceso a liberar
     */
    public void liberarMarcos(String[] memoria, String nombreProceso) {
        for (int i = 0; i < memoria.length; i++) {
            if (memoria[i] != null && memoria[i].equals(nombreProceso)) {
                memoria[i] = null;
            }
        }
        System.out.println("Los marcos del proceso '" + nombreProceso + "' fueron liberados");
    }
    /**
     * Mueve un proceso entre memorias según la elección del usuario.
     */
    public void moverProceso() {
        Proceso procesito = null;
        boolean validacion = false;
        mostrarProcesosEj();
        System.out.println("-------------------------------");
        System.out.println("Cual proceso quiere mover?");
        String resp = scanner.next();
        for (Proceso m : memoriaProcesos) {
            if (m.getNombreProceso().equals(resp)) {
                procesito = m;
                validacion = true;
                break;
            }
        }

        if (validacion) {
            procesito.setEstado(-1);
            System.out.println("A que memoria lo desea mover?\n 1. Principal \n 2. Secundaria \n 3. A la basura ");
            int op = scanner.nextInt();

            switch (op) {

                case 1:
                    liberarMarcos(memoriaSecundaria, procesito.getNombreProceso());
                    asignarMemoriaPrincipal(procesito);
                    procesito.setEstado(1);
                    break;

                case 2:
                    liberarMarcos(memoriaPrincipal, procesito.getNombreProceso());
                    asignarMemoriaSecundaria(procesito);
                    procesito.setEstado(2);
                    break;

                case 3:
                    for (int i = 0; i < memoriaPrincipal.length; i++) {
                        if (procesito.getNombreProceso().equals(memoriaPrincipal[i])) {
                            liberarMarcos(memoriaPrincipal, procesito.getNombreProceso());
                            procesito.setEstado(-1);
                        }
                    }

                    for (int i = 0; i < memoriaSecundaria.length; i++) {
                        if (procesito.getNombreProceso().equals(memoriaSecundaria[i])) {
                            liberarMarcos(memoriaSecundaria, procesito.getNombreProceso());
                            procesito.setEstado(-1);
                        }
                    }
                    break;
            }
        } else {
            System.out.println("Ese proceso no existe...");
        }
    }
    /**
     * Imprime el estado actual de la memoria principal, mostrando el contenido
     * de cada marco de memoria.
     */
    public void imprimirRAM() {
        System.out.println("Memoria Principal:");
        for (int i = 0; i < memoriaPrincipal.length; i++) {
            System.out.println(" [" + i + "]: " + memoriaPrincipal[i]);
        }
    }
    /**
     * Imprime el estado actual de la memoria secundaria, mostrando el contenido
     * de cada marco de memoria.
     */
    public void imprimirDiscoDuro() {
        System.out.println("Memoria Secundaria:");
        for (int i = 0; i < memoriaSecundaria.length; i++) {
            System.out.println("[" + i + "]: " + memoriaSecundaria[i]);
        }
    }
}
