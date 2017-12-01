/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.rnegocio.impl;

import ec.fin.coopsanjose.www.accesodatos.AccesoDatos;
import ec.fin.coopsanjose.www.accesodatos.ConjuntoResultado;
import ec.fin.coopsanjose.www.accesodatos.Parametro;
import java.util.ArrayList;
import java.util.List;
import ec.fin.coopsanjose.www.rnegocio.entidades.Detalle;
import ec.fin.coopsanjose.www.rnegocio.interfaces.IDetalleDao;
import ec.fin.coopsanjose.www.rnegocio.interfaces.IFacturaDao;
import ec.fin.coopsanjose.www.rnegocio.interfaces.IProductoDao;

public class DetalleImpl implements IDetalleDao {

    @Override
    public boolean insertar(Detalle detalle) throws Exception {
        boolean respuesta = false;
        String sql = "INSERT INTO detalle VALUES (?,?,?,?)";
        ArrayList<Parametro> lstParametro = new ArrayList<>();
        lstParametro.add(new Parametro(1, detalle.getFactura().getNumero()));
        lstParametro.add(new Parametro(2, detalle.getProducto().getCodigo()));
        lstParametro.add(new Parametro(3, detalle.getCantidad()));
        lstParametro.add(new Parametro(4, detalle.getPrecio_unitario()));
        try {
            respuesta = AccesoDatos.ejecutaComando(sql, lstParametro);
        } catch (Exception ex) {
            throw ex;
        }
        return respuesta;
    }

    @Override
    public boolean modificar(Detalle detalle) throws Exception {
        boolean respuesta = false;
        String sql = "UPDATE  detalle SET num_factura=?,"
                + " cod_producto=?, cantidad=?, precio_unitario=? "
                + " WHERE num_factura=? and cod_producto=? ";
        ArrayList<Parametro> lstParametro = new ArrayList<>();
        lstParametro.add(new Parametro(1, detalle.getFactura().getNumero()));
        lstParametro.add(new Parametro(2, detalle.getProducto().getCodigo()));
        lstParametro.add(new Parametro(3, detalle.getCantidad()));
        lstParametro.add(new Parametro(4, detalle.getPrecio_unitario()));
        lstParametro.add(new Parametro(5, detalle.getFactura().getNumero()));
        lstParametro.add(new Parametro(6, detalle.getProducto().getCodigo()));
        try {
            respuesta = AccesoDatos.ejecutaComando(sql, lstParametro);
        } catch (Exception ex) {
            throw ex;
        }
        return respuesta;
    }

    @Override
    public boolean eliminar(Detalle detalle) throws Exception {
        boolean respuesta = false;
        String sql = "DELETE FROM  detalle WHERE num_factura=? and cod_factura=? ";
        ArrayList<Parametro> lstParametro = new ArrayList<>();
        lstParametro.add(new Parametro(1, detalle.getFactura().getNumero()));
        lstParametro.add(new Parametro(1, detalle.getProducto().getCodigo()));
        try {
            respuesta = AccesoDatos.ejecutaComando(sql, lstParametro);
        } catch (Exception ex) {
            throw ex;
        }
        return respuesta;
    }

    @Override
    public Detalle obtener(int num_factura, int cod_producto) throws Exception {
        Detalle detalle = null;
        String sql = "SELECT num_factura, cod_producto, cantidad, precio_unitario FROM"
                + " detalle WHERE num_factura=? and cod_producto=?";
        ArrayList<Parametro> lstParametro = new ArrayList<>();
        lstParametro.add(new Parametro(1, num_factura));
        lstParametro.add(new Parametro(2, cod_producto));
        try {
            ConjuntoResultado crs = AccesoDatos.ejecutaQuery(sql, lstParametro);
            while (crs.next()) {
                detalle = new Detalle();
                IFacturaDao facturaDao= new FacturaImpl();
                IProductoDao productoDao= new ProductoImpl();
                detalle.setFactura( facturaDao.obtener(num_factura));
                detalle.setProducto(productoDao.obtener(cod_producto));
                detalle.setCantidad(crs.getDouble(3));
                detalle.setPrecio_unitario(crs.getInt(4));
            }
        } catch (Exception e) {
            throw e;
        }
        return detalle;
    }

    @Override
    public List<Detalle> obtener() throws Exception {
        List<Detalle> lista = new ArrayList<>();
        Detalle detalle = null;
        String sql = "SELECT num_factura, cod_producto, cantidad, precio_unitario FROM"
                + " detalle";
        try {
            ConjuntoResultado crs = AccesoDatos.ejecutaQuery(sql);
            while (crs.next()) {
                detalle = new Detalle();
                IFacturaDao facturaDao= new FacturaImpl();
                IProductoDao productoDao= new ProductoImpl();
                detalle.setFactura( facturaDao.obtener(crs.getInt(1)));
                detalle.setProducto(productoDao.obtener(crs.getInt(2)));
                detalle.setCantidad(crs.getDouble(3));
                detalle.setPrecio_unitario(crs.getDouble(4));
                lista.add(detalle);
            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
}
