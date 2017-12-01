/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.rnegocio.interfaces;

import java.util.List;
import ec.fin.coopsanjose.www.rnegocio.entidades.Factura;


public interface IFacturaDao {
    public boolean insertar(Factura factura) throws Exception;
    public boolean modificar(Factura factura)throws Exception;
    public boolean eliminar(Factura factura)throws Exception;
    public Factura obtener(int numero) throws Exception;
    public List<Factura> obtener()throws Exception;  
}
