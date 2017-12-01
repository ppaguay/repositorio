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
public class CategoriaController {
    private List<Categoria> lista;
    private Categoria nuevo;
    private Categoria seleccionado;
    public CategoriaController(){
     cargardatos();
    }
    public void cargardatos(){
        ICategoriaDao categoriaDao= new CategoriaImpl();
        try {
            lista= categoriaDao.obtener();
            nuevo= new Categoria();
        } catch (Exception e) {
            System.out.println("Error:"+e.getMessage());
        }        
    }
     public void insertar() {
        FacesContext context = FacesContext.getCurrentInstance();      
        ICategoriaDao categoriadao = new CategoriaImpl();
        try {            
            if (categoriadao.insertar(nuevo)) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito", "Ingresado correctamente"));
                cargardatos();
                nuevo = new Categoria();
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

    public List<Categoria> getLista() {
        return lista;
    }

    public void setLista(List<Categoria> lista) {
        this.lista = lista;
    }

    public Categoria getNuevo() {
        return nuevo;
    }

    public void setNuevo(Categoria nuevo) {
        this.nuevo = nuevo;
    }

    public Categoria getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Categoria seleccionado) {
        this.seleccionado = seleccionado;
    }
     
    
}
