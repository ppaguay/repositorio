/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.rnegocio.interfaces;

import ec.fin.coopsanjose.www.rnegocio.entidades.Usuario;
import java.util.List;

public interface IUsuarioDao {
    public boolean insertar(Usuario usuario) throws Exception;
    public boolean modificar(Usuario usuario) throws Exception;
    public boolean eliminar(Usuario usuario)throws Exception;
    public Usuario obtener(int codigo) throws Exception;
    public List<Usuario> obtener() throws Exception; 
    public Usuario autenticar(String strusuario, String clave) throws Exception ;
}
