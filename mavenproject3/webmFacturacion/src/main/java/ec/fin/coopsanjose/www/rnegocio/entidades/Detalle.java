/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.rnegocio.entidades;

/**
 *
 * @author Paul
 */
public class Detalle {
    private Factura factura;
    private Producto producto;
    private double cantidad;
    private double precio_unitario;

    public Detalle() {
    }

    public Detalle(Factura factura, Producto producto, double cantidad, double precio_unitario) {
        this.factura = factura;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    
}
