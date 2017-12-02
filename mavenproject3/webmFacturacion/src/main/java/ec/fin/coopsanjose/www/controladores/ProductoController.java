/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.controladores;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import ec.fin.coopsanjose.www.rnegocio.entidades.*;
import ec.fin.coopsanjose.www.rnegocio.impljpa.*;
import ec.fin.coopsanjose.www.rnegocio.interfaces.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class ProductoController {
    private List<Producto> lista;
    private Producto nuevo;
    private Producto seleccionado;
    public ProductoController(){
     cargardatos();
    }
    public void cargardatos(){
        IProductoDao productoDao= new ProductoImpl();
        try {
            lista= productoDao.obtener();
            nuevo= new Producto();
        } catch (Exception e) {
            System.out.println("Error:"+e.getMessage());
        }        
    }
     public void insertar() {
        FacesContext context = FacesContext.getCurrentInstance();      
        IProductoDao productodao = new ProductoImpl();
        try {            
            if (productodao.insertar(nuevo)) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito", "Ingresado correctamente"));
                cargardatos();
                nuevo = new Producto();
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
     public void eliminar() {
        FacesContext context = FacesContext.getCurrentInstance();
        IProductoDao productodao = new ProductoImpl();
        try {
            if (productodao.eliminar(seleccionado)) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito", "Eliminado correctamente"));
                cargardatos();
               
                seleccionado = new Producto();

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
        IProductoDao productodao = new ProductoImpl();
        try {
            if (productodao.modificar(seleccionado)) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito", "Modificado correctamente"));
                cargardatos();
                
                seleccionado = new Producto();

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

    public List<Producto> getLista() {
        return lista;
    }

    public void setLista(List<Producto> lista) {
        this.lista = lista;
    }

    public Producto getNuevo() {
        return nuevo;
    }

    public void setNuevo(Producto nuevo) {
        this.nuevo = nuevo;
    }

    public Producto getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Producto seleccionado) {
        this.seleccionado = seleccionado;
    }
     
    
}
