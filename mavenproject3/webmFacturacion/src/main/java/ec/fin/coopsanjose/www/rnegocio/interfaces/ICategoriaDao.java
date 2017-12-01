/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.rnegocio.interfaces;

import ec.fin.coopsanjose.www.rnegocio.entidades.Categoria;
import java.util.List;

public interface ICategoriaDao {
    public boolean insertar(Categoria categoria) throws Exception;
    public boolean modificar(Categoria categoria) throws Exception;
    public boolean eliminar(Categoria categoria)throws Exception;
    public Categoria obtener(int codigo) throws Exception;
    public List<Categoria> obtener() throws Exception;    
}
