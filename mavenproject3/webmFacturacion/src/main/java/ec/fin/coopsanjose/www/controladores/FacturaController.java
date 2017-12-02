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
public class FacturaController {

    private List<Factura> lista;
    private Factura nuevo;
    private Factura seleccionado;
    private List<Cliente> listaClientes;
    private int codigocliente;

    public FacturaController() {
        Cliente cliente= new Cliente();
        nuevo = new Factura();
        seleccionado = new Factura();
        seleccionado.setCliente(cliente);

        IClienteDao clientedao = new ClienteImpl();
        try {
            listaClientes = clientedao.obtener();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        cargardatos();
    }

    private void cargardatos() {
        IFacturaDao facturadao = new FacturaImpl();
        try {
            lista = facturadao.obtener();
        } catch (Exception e) {
            System.out.println("Error al cargar los datos del factura!!" + e.getMessage());
        }
    }
      public void eliminar() {
        FacesContext context = FacesContext.getCurrentInstance();
        IFacturaDao facturadao = new FacturaImpl();
        try {
            if (facturadao.eliminar(seleccionado)) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito", "Eliminado correctamente"));
                cargardatos();
                Cliente cat = new Cliente();
                seleccionado = new Factura();
                seleccionado.setCliente(cat);

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
        IFacturaDao facturadao = new FacturaImpl();
        try {
            if (facturadao.modificar(seleccionado)) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito", "Modificado correctamente"));
                cargardatos();
                Cliente cat = new Cliente();
                seleccionado = new Factura();
                seleccionado.setCliente(cat);

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
        IFacturaDao facturadao = new FacturaImpl();
        try {
            IClienteDao clienteDao = new ClienteImpl();
            Cliente cliente = clienteDao.obtener(codigocliente);
            nuevo.setCliente(cliente);
            if (facturadao.insertar(nuevo)) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito", "Ingresado correctamente"));
                cargardatos();
                nuevo = new Factura();
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

    public List<Factura> getLista() {
        return lista;
    }

    public void setLista(List<Factura> lista) {
        this.lista = lista;
    }

    public Factura getNuevo() {
        return nuevo;
    }

    public void setNuevo(Factura nuevo) {
        this.nuevo = nuevo;
    }

    public Factura getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Factura seleccionado) {
        this.seleccionado = seleccionado;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public int getCodigocliente() {
        return codigocliente;
    }

    public void setCodigocliente(int codigocliente) {
        this.codigocliente = codigocliente;
    }

 

}
