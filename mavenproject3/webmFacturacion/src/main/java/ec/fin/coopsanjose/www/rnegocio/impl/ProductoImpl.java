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
import ec.fin.coopsanjose.www.rnegocio.entidades.Producto;
import ec.fin.coopsanjose.www.rnegocio.interfaces.IProductoDao;


public class ProductoImpl implements IProductoDao{

   @Override
    public boolean insertar(Producto producto) throws Exception {
        boolean respuesta = false;
        String sql = "INSERT INTO producto VALUES (?,?,?,?)";
        ArrayList<Parametro> lstParametro = new ArrayList<>();
        lstParametro.add(new Parametro(1, producto.getCodigo()));
        lstParametro.add(new Parametro(2, producto.getDescripcion()));
        lstParametro.add(new Parametro(3, producto.getStock()));
        lstParametro.add(new Parametro(4, producto.getPrecio_venta()));
        try {
            respuesta = AccesoDatos.ejecutaComando(sql, lstParametro);
        } catch (Exception ex) {
            throw ex;
        }
        return respuesta;
    }

    @Override
    public boolean modificar(Producto producto) throws Exception {
        boolean respuesta = false;
        String sql = "UPDATE  producto SET codigo=?,"
                + " descripcion=?, stock=?, precio_venta=? "
                + "WHERE codigo=? ";
        ArrayList<Parametro> lstParametro = new ArrayList<>();
        lstParametro.add(new Parametro(1, producto.getCodigo()));
        lstParametro.add(new Parametro(2, producto.getDescripcion()));
        lstParametro.add(new Parametro(3, producto.getStock()));
        lstParametro.add(new Parametro(4, producto.getPrecio_venta()));
        lstParametro.add(new Parametro(5, producto.getCodigo()));

        try {
            respuesta = AccesoDatos.ejecutaComando(sql, lstParametro);
        } catch (Exception ex) {
            throw ex;
        }
        return respuesta;
    }

    @Override
    public boolean eliminar(Producto producto) throws Exception {
        boolean respuesta = false;
        String sql = "DELETE FROM  producto WHERE codigo=? ";
        ArrayList<Parametro> lstParametro = new ArrayList<>();
        lstParametro.add(new Parametro(1, producto.getCodigo()));        
        try {
            respuesta = AccesoDatos.ejecutaComando(sql, lstParametro);
        } catch (Exception ex) {
            throw ex;
        }
        return respuesta;
    }

    @Override
    public Producto obtener(int codigo) throws Exception {
        Producto producto = null;
        String sql = "SELECT codigo, descripcion, stock, precio_venta FROM"
                + " producto WHERE codigo =?";
        ArrayList<Parametro> lstParametro = new ArrayList<>();
        lstParametro.add(new Parametro(1, codigo));
        try {
            ConjuntoResultado crs = AccesoDatos.ejecutaQuery(sql, lstParametro);
            while (crs.next()) {
                producto = new Producto();
                producto.setCodigo(crs.getInt(1));
                producto.setDescripcion(crs.getString(2));
                producto.setStock(crs.getDouble(3));
                producto.setPrecio_venta(crs.getDouble(4));
                
            }
        } catch (Exception e) {
            throw e;
        }
        return producto;
    }

    @Override
    public List<Producto> obtener() throws Exception {
        List<Producto> lista = new ArrayList<>();
        Producto producto = null;
        String sql = "SELECT codigo, descripcion, stock, precio_venta FROM"
                + " producto ";
        try {
            ConjuntoResultado crs = AccesoDatos.ejecutaQuery(sql);
            while (crs.next()) {
                producto = new Producto();
                producto.setCodigo(crs.getInt(1));
                producto.setDescripcion(crs.getString(2));
                producto.setStock(crs.getDouble(3));
                producto.setPrecio_venta(crs.getDouble(4));
                lista.add(producto);
            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
}
