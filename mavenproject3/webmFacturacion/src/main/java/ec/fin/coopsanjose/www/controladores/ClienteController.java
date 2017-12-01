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
public class ClienteController {

    private List<Cliente> lista;
    private Cliente nuevo;
    private Cliente seleccionado;
    private List<Categoria> listaCategorias;
    private int codigocategoria;

    public ClienteController() {
        Categoria categoria = new Categoria();
        nuevo = new Cliente();
        seleccionado = new Cliente();
        seleccionado.setCategoria(categoria);

        ICategoriaDao categoriadao = new CategoriaImpl();
        try {
            listaCategorias = categoriadao.obtener();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        cargardatos();
    }

    private void cargardatos() {
        IClienteDao clientedao = new ClienteImpl();
        try {
            lista = clientedao.obtener();
        } catch (Exception e) {
            System.out.println("Error al cargar los datos del cliente!!" + e.getMessage());
        }
    }
      public void eliminar() {
        FacesContext context = FacesContext.getCurrentInstance();
        IClienteDao clientedao = new ClienteImpl();
        try {
            if (clientedao.eliminar(seleccionado)) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito", "Eliminado correctamente"));
                cargardatos();
                Categoria cat = new Categoria();
                seleccionado = new Cliente();
                seleccionado.setCategoria(cat);

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
        IClienteDao clientedao = new ClienteImpl();
        try {
            if (clientedao.modificar(seleccionado)) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito", "Modificado correctamente"));
                cargardatos();
                Categoria cat = new Categoria();
                seleccionado = new Cliente();
                seleccionado.setCategoria(cat);

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
        IClienteDao clientedao = new ClienteImpl();
        try {
            ICategoriaDao categoriaDao = new CategoriaImpl();
            Categoria categoria = categoriaDao.obtener(codigocategoria);
            nuevo.setCategoria(categoria);
            if (clientedao.insertar(nuevo)) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito", "Ingresado correctamente"));
                cargardatos();
                nuevo = new Cliente();
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

    public List<Cliente> getLista() {
        return lista;
    }

    public void setLista(List<Cliente> lista) {
        this.lista = lista;
    }

    public Cliente getNuevo() {
        return nuevo;
    }

    public void setNuevo(Cliente nuevo) {
        this.nuevo = nuevo;
    }

    public Cliente getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Cliente seleccionado) {
        this.seleccionado = seleccionado;
    }

    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public int getCodigocategoria() {
        return codigocategoria;
    }

    public void setCodigocategoria(int codigocategoria) {
        this.codigocategoria = codigocategoria;
    }

}
