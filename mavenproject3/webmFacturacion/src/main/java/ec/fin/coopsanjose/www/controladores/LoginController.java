/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.controladores;

import ec.fin.coopsanjose.www.rnegocio.entidades.Usuario;
import ec.fin.coopsanjose.www.rnegocio.impljpa.UsuarioImpl;
import ec.fin.coopsanjose.www.rnegocio.interfaces.IUsuarioDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

@ManagedBean
@ViewScoped
public class LoginController {

    private String strusuario;
    private String clave;
    @ManagedProperty(value = "#{sesionUsuario}")
    private SesionUsuario sesion;

    public String login() {
        String pagina = "/index?faces-redirect=true";
        Usuario usuario = null;
        try {
            IUsuarioDao usuariodao = new UsuarioImpl();
            usuario = usuariodao.autenticar(strusuario, clave);
            if (usuario != null) {
                sesion.setSesion(usuario);
                HttpSession session = Util.getSession();
                session.setAttribute("usuario", usuario);
                pagina = "privado/factura?faces-redirect=true";
            }
        } catch (Exception ex) {
            System.out.println("Error:" + ex.getMessage());
        }
        return pagina;
    }

    public String cerrarSesion() {
        HttpSession session = Util.getSession();
        session.setAttribute("usuario", null);

        return "/index.xhtml?faces-redirect=true";
    }

    public String getStrusuario() {
        return strusuario;
    }

    public void setStrusuario(String strusuario) {
        this.strusuario = strusuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public SesionUsuario getSesion() {
        return sesion;
    }

    public void setSesion(SesionUsuario sesion) {
        this.sesion = sesion;
    }

}
