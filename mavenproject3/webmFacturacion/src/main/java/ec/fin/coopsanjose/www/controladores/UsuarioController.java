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
public class UsuarioController {
    private List<Usuario> lista;
    private Usuario nuevo;
    private Usuario seleccionado;
    public UsuarioController(){
     cargardatos();
    }
    public void cargardatos(){
        IUsuarioDao usuarioDao= new UsuarioImpl();
        try {
            lista= usuarioDao.obtener();
            nuevo= new Usuario();
        } catch (Exception e) {
            System.out.println("Error:"+e.getMessage());
        }        
    }
     public void insertar() {
        FacesContext context = FacesContext.getCurrentInstance();      
        IUsuarioDao usuariodao = new UsuarioImpl();
        try {            
            if (usuariodao.insertar(nuevo)) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito", "Ingresado correctamente"));
                cargardatos();
                nuevo = new Usuario();
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
        IUsuarioDao usuariodao = new UsuarioImpl();
        try {
            if (usuariodao.eliminar(seleccionado)) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito", "Eliminado correctamente"));
                cargardatos();
                Categoria cat = new Categoria();
                seleccionado = new Usuario();

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
        IUsuarioDao usuariodao = new UsuarioImpl();
        try {
            if (usuariodao.modificar(seleccionado)) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito", "Modificado correctamente"));
                cargardatos();
                seleccionado = new Usuario();

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

    public List<Usuario> getLista() {
        return lista;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }

    public Usuario getNuevo() {
        return nuevo;
    }

    public void setNuevo(Usuario nuevo) {
        this.nuevo = nuevo;
    }

    public Usuario getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Usuario seleccionado) {
        this.seleccionado = seleccionado;
    }
     
    
}
