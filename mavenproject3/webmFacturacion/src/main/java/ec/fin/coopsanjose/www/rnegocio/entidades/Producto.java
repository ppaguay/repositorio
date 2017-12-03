/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.rnegocio.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Paul
 */
@Entity
@Table(name="producto")
public class Producto implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
     @Basic(optional = false)
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
       @Override
    public int hashCode() {
        int hash = 0;
        hash += codigo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if (this.codigo!=other.codigo) {
            return false;
        }
        return true;
    }
}
