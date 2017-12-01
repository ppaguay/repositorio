/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.rnegocio.interfaces;

import ec.fin.coopsanjose.www.rnegocio.entidades.Cliente;
import java.util.List;

public interface IClienteDao {
    public boolean insertar(Cliente cliente) throws Exception;
    public boolean modificar(Cliente cliente) throws Exception;
    public boolean eliminar(Cliente cliente)throws Exception;
    public Cliente obtener(int codigo) throws Exception;
    public List<Cliente> obtener() throws Exception;    
}
