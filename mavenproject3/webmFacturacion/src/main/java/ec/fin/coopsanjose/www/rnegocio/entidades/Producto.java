/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.rnegocio.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Paul
 */
@Entity
@Table(name="producto")
public class Producto {
    @Id
    private int codigo;
    private String descripcion;
    private double stock;
    private double precio_venta;

    public Producto() {
    }

    public Producto(int codigo, String descripcion, double stock, double precio_venta) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio_venta = precio_venta;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }
    
}
