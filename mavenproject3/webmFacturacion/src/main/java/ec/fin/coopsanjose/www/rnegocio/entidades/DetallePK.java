/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.rnegocio.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Paul
 */
@Embeddable
public class DetallePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "num_factura")
    private int numFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_producto")
    private int codProducto;

    public DetallePK() {
    }

    public DetallePK(int numFactura, int codProducto) {
        this.numFactura = numFactura;
        this.codProducto = codProducto;
    }

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public int getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numFactura;
        hash += (int) codProducto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallePK)) {
            return false;
        }
        DetallePK other = (DetallePK) object;
        if (this.numFactura != other.numFactura) {
            return false;
        }
        if (this.codProducto != other.codProducto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.DetallePK[ numFactura=" + numFactura + ", codProducto=" + codProducto + " ]";
    }
    
}
