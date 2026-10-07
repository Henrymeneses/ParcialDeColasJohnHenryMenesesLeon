/* 9. Servicio posventa
Una tienda recibe clientes que necesitan realizar cambios, garantías o devoluciones.
Los clientes esperan para ser atendidos.
Durante la espera pueden cambiar el motivo de la solicitud, cancelar el proceso o presentar nueva información.
Una vez iniciada la atención, las condiciones cambian. */

import java.util.Queue;
import java.util.Scanner;

public class MetodosPosventa {

    public Queue<ObjSolicitudPosventa> RegistrarSolicitud(Queue<ObjSolicitudPosventa> cola, MetodosPosventa m, Scanner sc) {
        boolean continuar = true;
        while (continuar) {
            ObjSolicitudPosventa solicitud = new ObjSolicitudPosventa();
            solicitud.setTurno(m.GenerarTurno(cola));

            System.out.println("Ingrese el nombre del cliente:");
            solicitud.setCliente(sc.next());

            solicitud.setMotivo(m.MenuMotivo(sc));

            System.out.println("Ingrese el número de factura o nombre del producto:");
            solicitud.setProductoFactura(sc.next());

            System.out.println("Cual es la causa por la que esta generando la solicitud:");
            solicitud.setObservaciones(sc.next());

            solicitud.setEstado(1);
            cola.offer(solicitud);

            System.out.println("¿Desea registrar otra solicitud posventa? (1: Sí / 2: No)");
            int opt = m.ValidarEntero(sc);
            if (opt == 2) {
                continuar = false;
            }
        }
        return cola;
    }

    public int GenerarTurno(Queue<ObjSolicitudPosventa> cola) {
        if (cola.isEmpty()) {
            return 1;
        }
        return cola.size() + 1;
    }

    public int MenuMotivo(Scanner sc) {
        System.out.println("Seleccione el motivo del servicio posventa:");
        System.out.println("1) Cambio de Producto");
        System.out.println("2) Garantías");
        System.out.println("3) Devoluciones");
        int opt = ValidarEntero(sc);
        while (opt < 1 || opt > 3) {
            System.out.println("Opción inválida, solo hay opciones del uno al tres");
            opt = ValidarEntero(sc);
        }
        return opt;
    }

    public Queue<ObjSolicitudPosventa> ModificarDatosEnEspera(Queue<ObjSolicitudPosventa> cola, Scanner sc) {
        System.out.println("Ingrese el número de turno del cliente a modificar:");
        int turnoBuscado = ValidarEntero(sc);
        boolean turnoEncontrado = false;

        for (ObjSolicitudPosventa solicitud : cola) {
            if (solicitud.getTurno() == turnoBuscado) {
                if (solicitud.getEstado() == 1) {
                    System.out.println("¿Qué datos desea actualizar antes de la atención?");
                    System.out.println("1) Cambiar Motivo de la Solicitud");
                    System.out.println("2) Anexar Nueva Información / Observaciones");
                    int opt = ValidarEntero(sc);

                    if (opt == 1) {
                        solicitud.setMotivo(MenuMotivo(sc));
                        System.out.println("Motivo modificado con éxito.");
                    } else if (opt == 2) {
                        System.out.println("Ingrese la nueva información que presenta el cliente:");
                        String infoAdicional = sc.next();
                        solicitud.setObservaciones(solicitud.getObservaciones() + " Adicionamlente: " + infoAdicional);
                        System.out.println("Información anexada con éxito.");
                    }
                } else {
                    System.out.println("La atención ya ha iniciado o el caso finalizó. Las condiciones no se pueden alterar.");
                }
                turnoEncontrado = true;
                break;
            }
        }

        if (turnoEncontrado) {
            System.out.println("No se encontró una solicitud con el turno ingresado.");
        }
        return cola;
    }

    public Queue<ObjSolicitudPosventa> CancelarSolicitud(Queue<ObjSolicitudPosventa> cola, Scanner sc) {
        System.out.println("Ingrese el número de turno de la solicitud a cancelar:");
        int turnoBuscado = ValidarEntero(sc);
        boolean encontrado = false;

        for (ObjSolicitudPosventa solicitud : cola) {
            if (solicitud.getTurno() == turnoBuscado) {
                if (solicitud.getEstado() == 1) { 
                    solicitud.setEstado(4);
                    System.out.println("Proceso de posventa del Turno #" + turnoBuscado + " CANCELADO exitosamente.");
                } else {
                    System.out.println("No se puede cancelar la solicitud porque ya se encuentra en atención o finalizada.");
                }
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            System.out.println("No se encontró una solicitud con ese número de turno.");
        }
        return cola;
    }

    public String IniciarAtencion(Queue<ObjSolicitudPosventa> cola) {
        for (ObjSolicitudPosventa solicitud : cola) {
            if (solicitud.getEstado() == 1) {
                solicitud.setEstado(2);
                return "ATENCIÓN INICIADA: Cliente " + solicitud.getCliente() + " Turno #" + solicitud.getTurno() + " | Motivo: " + solicitud.getMotivo();
                        
                        
            }
        }
        return "No hay clientes esperando en la fila de servicio posventa.";
    }

    public Queue<ObjSolicitudPosventa> FinalizarTramite(Queue<ObjSolicitudPosventa> cola, Scanner sc) {
        System.out.println("Ingrese el número de turno del cliente cuya atención finalizará:");
        int turnoBuscado = ValidarEntero(sc);
        boolean encontrado = false;

        for (ObjSolicitudPosventa solicitud : cola) {
            if (solicitud.getTurno() == turnoBuscado && solicitud.getEstado() == 2) {
                solicitud.setEstado(3);
                System.out.println("Tramite posventa del Turno #" + turnoBuscado + " solctud cerrada.");
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            System.out.println("No se encontró un cliente en de atención con ese turno.");
        }
        return cola;
    }

    public String MostrarReporte(Queue<ObjSolicitudPosventa> cola, int filtroEstado) {
        if (cola.isEmpty()) {
            return "El módulo de servicio posventa no registra clientes.";
        }

        for (ObjSolicitudPosventa solicitud : cola) {
            if (filtroEstado == 0 || solicitud.getEstado() == filtroEstado) {
                System.out.println("Turno: " + solicitud.getTurno());
                System.out.println("Cliente: " + solicitud.getCliente());
                System.out.println("Factura/Producto: " + solicitud.getProductoFactura());
                System.out.println("Motivo: " + solicitud.getMotivo());
                System.out.println("Observaciones: " + solicitud.getObservaciones());
                System.out.println("Estado: " + solicitud.getEstado());
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            }
        }
        return "Reporte impreso.";
    }

  

    

    public int ValidarEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Por favor ingrese un número entero válido:");
            sc.next();
        }
        return sc.nextInt();
    }
}
