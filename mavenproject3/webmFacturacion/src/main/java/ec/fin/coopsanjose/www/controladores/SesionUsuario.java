/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.controladores;

import ec.fin.coopsanjose.www.rnegocio.entidades.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class SesionUsuario {
    private Usuario sesion;

    public Usuario getSesion() {
        return sesion;
    }

    public void setSesion(Usuario sesion) {
        this.sesion = sesion;
    }
}
