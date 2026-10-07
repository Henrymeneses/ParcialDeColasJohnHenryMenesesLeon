/* 9. Servicio posventa
Una tienda recibe clientes que necesitan realizar cambios, garantías o devoluciones.
Los clientes esperan para ser atendidos.
Durante la espera pueden cambiar el motivo de la solicitud, cancelar el proceso o presentar nueva información.
Una vez iniciada la atención, las condiciones cambian. */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MenuPosventa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<ObjSolicitudPosventa> cola = new LinkedList<>();
        MetodosPosventa m = new MetodosPosventa();
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n MENU DE SERVICIO POSVENTA ");
            System.out.println("1) Registrar cliente en fila de espera");
            System.out.println("2) Iniciar atención de siguiente cliente ");
            System.out.println("3) Modificar motivo o anexar información ");
            System.out.println("4) Cancelar proceso");
            System.out.println("5) Finalizar y cerrar tramite de atención");
            System.out.println("6) Ver reporte de solicitudes");
            System.out.println("7) Ver solo clientes en espera");
            System.out.println("8) Salir");

            int opt = m.ValidarEntero(sc);
            switch (opt) {
                case 1:
                    cola = m.RegistrarSolicitud(cola, m, sc);
                    break;
                case 2:
                    System.out.println("\n" + m.IniciarAtencion(cola));
                    break;
                case 3:
                    cola = m.ModificarDatosEnEspera(cola, sc);
                    break;
                case 4:
                    cola = m.CancelarSolicitud(cola, sc);
                    break;
                case 5:
                    cola = m.FinalizarTramite(cola, sc);
                    break;
                case 6:
                    System.out.println("\n" + m.MostrarReporte(cola, 0)); 
                    break;
                case 7:
                    System.out.println("\n" + m.MostrarReporte(cola, 1)); 
                    break;
                case 8:
                    System.out.println("Cerrando el módulo de servicio posventa...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
}
