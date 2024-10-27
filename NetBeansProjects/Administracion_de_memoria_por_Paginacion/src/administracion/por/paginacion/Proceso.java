/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package administracion.por.paginacion;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ESTUDIANTE
 */
public class Proceso {

    private int NumPaginacion;
    private String NombreProceso;
    private int estado;

    public Proceso(int NumPaginacion, String NombreProceso, int estado) {
        this.NumPaginacion = NumPaginacion;
        this.NombreProceso = NombreProceso;
        this.estado = estado;
    }

    public Proceso() {
    }

    public int getNumPaginacion() {
        return NumPaginacion;
    }

    public void setNumPaginacion(int NumPaginacion) {
        this.NumPaginacion = NumPaginacion;
    }

    public String getNombreProceso() {
        return NombreProceso;
    }

    public void setNombreProceso(String NombreProceso) {
        this.NombreProceso = NombreProceso;
    }

    public int isEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Nombre del proceso: " + NombreProceso + " -- Numero de paginacion: " + NumPaginacion + " -- Estado: " + estado + '}';
    }
    
    ArrayList<Proceso> memoriaProcesos = new ArrayList<>();
    String[] memoriaPrincipal = new String[8];
    String[] memoriaSecundaria = new String[16];
    Scanner scanner = new Scanner(System.in);

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

    public void mostrarProcesosDisponibles() {
        System.out.println("Lista de procesos almacenados:\n");
        for (Proceso proceso : memoriaProcesos) {
            if (proceso.getEstado() == 0) {
                System.out.println(proceso.toString());
            }
        }
    }

    public void mostrarProcesosEj() {
        System.out.println("Lista de procesos almacenados:\n");
        for (Proceso proceso : memoriaProcesos) {
            if (proceso.getEstado() != 0) {
                System.out.println(proceso.toString());
            }
        }
    }

    public void mostrarProcesos() {
        System.out.println("Lista de procesos almacenados:\n");
        for (Proceso proceso : memoriaProcesos) {
            System.out.println(proceso.toString());
        }
    }

    public void mostrarProcesosFinalizados() {
        System.out.println("Lista de procesos finalizados:\n");
        for (Proceso proceso : memoriaProcesos) {
            if (proceso.getEstado() == -1) {
                System.out.println(proceso.toString());
            }
        }
    }
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

    public void ejecutarProceso(Proceso procesito) {
        int paginasAsignadas = 0;
        int marcosLibres = 0;

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

    public void liberarMarcos(String[] memoria, String nombreProceso) {
        for (int i = 0; i < memoria.length; i++) {
            if (memoria[i] != null && memoria[i].equals(nombreProceso)) {
                memoria[i] = null;
            }
        }
        System.out.println("Los marcos del proceso '" + nombreProceso + "' fueron liberados");
    }

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

    public void imprimirRAM() {
        System.out.println("Memoria Principal:");
        for (int i = 0; i < memoriaPrincipal.length; i++) {
            System.out.println(" [" + i + "]: " + memoriaPrincipal[i]);
        }
    }

    public void imprimirDiscoDuro() {
        System.out.println("Memoria Secundaria:");
        for (int i = 0; i < memoriaSecundaria.length; i++) {
            System.out.println("[" + i + "]: " + memoriaSecundaria[i]);
        }
    }
}