/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.controladores;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.*;
import ec.fin.coopsanjose.www.rnegocio.entidades.*;
import ec.fin.coopsanjose.www.rnegocio.interfaces.*;
import ec.fin.coopsanjose.www.rnegocio.impljpa.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class DetalleController {

    private List<Detalle> lista;
    private Detalle nuevo;
    private Detalle seleccionado;
    private List<Factura> listaFacturas;
    private List<Producto> listaProductos;
    private int codigofactura;
    private int codigoproducto;

    public DetalleController() {
        Factura factura = new Factura();
        Producto producto = new Producto();
        nuevo = new Detalle();
        seleccionado = new Detalle();
        seleccionado.setFactura(factura);
        seleccionado.setProducto(producto);

        IFacturaDao facturadao = new FacturaImpl();
        IProductoDao productodao= new ProductoImpl();
        try {
            listaFacturas = facturadao.obtener();
            listaProductos = productodao.obtener();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        cargardatos();
    }

    private void cargardatos() {
        IDetalleDao detalledao = new DetalleImpl();
        try {
            lista = detalledao.obtener();
        } catch (Exception e) {
            System.out.println("Error al cargar los datos del detalle!!" + e.getMessage());
        }
    }
      public void eliminar() {
        FacesContext context = FacesContext.getCurrentInstance();
        IDetalleDao detalledao = new DetalleImpl();
        try {
            if (detalledao.eliminar(seleccionado)) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito", "Eliminado correctamente"));
                cargardatos();
                Factura cat = new Factura();
                seleccionado = new Detalle();
                seleccionado.setFactura(cat);

                RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('wdgEliminar').hide()");
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error", "Error en la transaccion"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
                    "Error", "Error desconocido.." + e.getMessage()));
        }
    }

    public void modificar() {
        FacesContext context = FacesContext.getCurrentInstance();
        IDetalleDao detalledao = new DetalleImpl();
        try {
            if (detalledao.modificar(seleccionado)) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito", "Modificado correctamente"));
                cargardatos();
                Factura cat = new Factura();
                Producto prod = new Producto();
                seleccionado = new Detalle();
                seleccionado.setFactura(cat);
                seleccionado.setProducto(prod);

                RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('wdgModificar').hide()");
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error", "Error en la transaccion"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
                    "Error", "Error desconocido.." + e.getMessage()));
        }
    }

    public void insertar() {
        FacesContext context = FacesContext.getCurrentInstance();
        IDetalleDao detalledao = new DetalleImpl();
        try {
            IFacturaDao facturaDao = new FacturaImpl();
            IProductoDao productoDao = new ProductoImpl();
            Factura factura = facturaDao.obtener(codigofactura);
            Producto producto = productoDao.obtener(codigoproducto);
            nuevo.setFactura(factura);
            nuevo.setProducto(producto);
            DetallePK detallePk = new DetallePK(codigofactura, codigoproducto);
            nuevo.setDetallePK(detallePk);
            if (detalledao.insertar(nuevo)) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito", "Ingresado correctamente"));
                cargardatos();
                nuevo = new Detalle();
                RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('wdgNuevo').hide()");
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error", "Error en la transaccion"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
                    "Error", "Error desconocido.."));
        }
    }

    public List<Detalle> getLista() {
        return lista;
    }

    public void setLista(List<Detalle> lista) {
        this.lista = lista;
    }

    public Detalle getNuevo() {
        return nuevo;
    }

    public void setNuevo(Detalle nuevo) {
        this.nuevo = nuevo;
    }

    public Detalle getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Detalle seleccionado) {
        this.seleccionado = seleccionado;
    }

    public List<Factura> getListaFacturas() {
        return listaFacturas;
    }

    public void setListaFacturas(List<Factura> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }

    public int getCodigofactura() {
        return codigofactura;
    }

    public void setCodigofactura(int codigofactura) {
        this.codigofactura = codigofactura;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public int getCodigoproducto() {
        return codigoproducto;
    }

    public void setCodigoproducto(int codigoproducto) {
        this.codigoproducto = codigoproducto;
    }

}
