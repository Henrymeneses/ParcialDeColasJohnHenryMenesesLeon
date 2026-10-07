/*9. Servicio posventa
Una tienda recibe clientes que necesitan realizar cambios, garantías o devoluciones.
Los clientes esperan para ser atendidos.
Durante la espera pueden cambiar el motivo de la solicitud, cancelar el proceso o presentar nueva información.
Una vez iniciada la atención, las condiciones cambian.*/
public class ObjSolicitudPosventa {
    private int turno;
    private String cliente;
    private int motivo; 
    private String productoFactura;
    private String observaciones;
    private int estado; /*  Vamos a tener 4 estados que son 1: En Espera, 2: En Atención, 3: Finalizado, 4: Cancelado */

    public ObjSolicitudPosventa() {
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getMotivo() {
        return motivo;
    }

    public void setMotivo(int motivo) {
        this.motivo = motivo;
    }

    public String getProductoFactura() {
        return productoFactura;
    }

    public void setProductoFactura(String productoFactura) {
        this.productoFactura = productoFactura;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
