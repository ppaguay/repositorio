/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.rnegocio.interfaces;

import java.util.List;
import ec.fin.coopsanjose.www.rnegocio.entidades.Detalle;


public interface IDetalleDao {
    public boolean insertar(Detalle detalle) throws Exception;
    public boolean modificar(Detalle detalle)throws Exception;
    public boolean eliminar(Detalle detalle)throws Exception;
    public Detalle obtener(int num_factura, int cod_producto)throws Exception;
    public List<Detalle> obtener()throws Exception;  
}
