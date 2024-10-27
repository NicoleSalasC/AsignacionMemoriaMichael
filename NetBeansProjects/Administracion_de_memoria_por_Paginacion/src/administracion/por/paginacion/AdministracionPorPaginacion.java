
package administracion.por.paginacion;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Ashley Hernandez
 * @author Jeison Alvarez
 * @author Nicole Salas
 * @author Jocelyn Abarca
 */
public class AdministracionPorPaginacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        imprimirMenu();
    }

    static Proceso pr = new Proceso();

    public static void imprimirMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------------------");
        System.out.println("          MENU");
        System.out.println("1. Ingresar proceso");
        System.out.println("2. Ejecutar proceso");
        System.out.println("3. Mostrar procesos");
        System.out.println("4. Mover un proceso");
        System.out.println("5. Ver la memoria principal");
        System.out.println("6. Ver la memoria secundaria");
        System.out.println("7. Ver los procesos finalizados");
        System.out.println("00. Cerrar programa");
        System.out.println("-------------------------------");

        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                pr.addProcess();
                System.out.println("-------------------------------");

                pr.mostrarProcesos();

                imprimirMenu();
                break;

            case 2:

                pr.validacion();
                imprimirMenu();
                break;

            case 3:
                pr.mostrarProcesos();

                imprimirMenu();
                break;

            case 4:

                pr.moverProceso();
                imprimirMenu();
                break;

            case 5:

                pr.imprimirRAM();
                imprimirMenu();
                break;

            case 6:

                pr.imprimirDiscoDuro();
                imprimirMenu();
                break;

            case 7:

                pr.mostrarProcesosFinalizados();
                imprimirMenu();
                break;

            case 00:

                System.exit(opcion);

            default:
                System.out.println("Opcion invalida");
                imprimirMenu();

        }
    }

}
