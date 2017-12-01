/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.rnegocio.interfaces;

import java.util.List;
import ec.fin.coopsanjose.www.rnegocio.entidades.Producto;

public interface IProductoDao {

    public boolean insertar(Producto producto) throws Exception;

    public boolean modificar(Producto producto)throws Exception;

    public boolean eliminar(Producto producto)throws Exception;

    public Producto obtener(int codigo)throws Exception;

    public List<Producto> obtener()throws Exception;
}
