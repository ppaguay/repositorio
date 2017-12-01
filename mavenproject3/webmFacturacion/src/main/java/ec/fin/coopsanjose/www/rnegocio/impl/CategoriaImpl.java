/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.rnegocio.impl;

import ec.fin.coopsanjose.www.accesodatos.AccesoDatos;
import ec.fin.coopsanjose.www.accesodatos.ConjuntoResultado;
import ec.fin.coopsanjose.www.accesodatos.Parametro;
import ec.fin.coopsanjose.www.rnegocio.entidades.Categoria;
import ec.fin.coopsanjose.www.rnegocio.interfaces.ICategoriaDao;
import java.util.ArrayList;
import java.util.List;


public class CategoriaImpl implements ICategoriaDao {
     @Override
    public boolean insertar(Categoria categoria) throws Exception {
        boolean respuesta = false;
        String sql = "INSERT INTO categoria VALUES (?,?)";
        ArrayList<Parametro> lstParametro = new ArrayList<>();
        lstParametro.add(new Parametro(1, categoria.getCodigo()));
        lstParametro.add(new Parametro(2, categoria.getDescripcion()));
        
        try {
            respuesta = AccesoDatos.ejecutaComando(sql, lstParametro);
        } catch (Exception ex) {
            throw ex;
        }
        return respuesta;
    }

    @Override
    public boolean modificar(Categoria categoria) throws Exception {
        boolean respuesta = false;
        String sql = "UPDATE  categoria SET codigo=?,"
                + " descripcion=? "
                + "WHERE codigo=? ";
        ArrayList<Parametro> lstParametro = new ArrayList<>();
        lstParametro.add(new Parametro(1, categoria.getCodigo()));
        lstParametro.add(new Parametro(2, categoria.getDescripcion()));
        
        try {
            respuesta = AccesoDatos.ejecutaComando(sql, lstParametro);
        } catch (Exception ex) {
            throw ex;
        }
        return respuesta;
    }

    @Override
    public boolean eliminar(Categoria categoria) throws Exception {
        boolean respuesta = false;
        String sql = "DELETE FROM  categoria WHERE codigo=? ";
        ArrayList<Parametro> lstParametro = new ArrayList<>();
        lstParametro.add(new Parametro(1, categoria.getCodigo()));        
        try {
            respuesta = AccesoDatos.ejecutaComando(sql, lstParametro);
        } catch (Exception ex) {
            throw ex;
        }
        return respuesta;
    }

    @Override
    public Categoria obtener(int codigo) throws Exception {
        Categoria categoria = null;
        String sql = "SELECT codigo, descripcion FROM"
                + " categoria WHERE codigo =?";
        ArrayList<Parametro> lstParametro = new ArrayList<>();
        lstParametro.add(new Parametro(1, codigo));
        try {
            ConjuntoResultado crs = AccesoDatos.ejecutaQuery(sql, lstParametro);
            while (crs.next()) {
                categoria = new Categoria();
                categoria.setCodigo(crs.getInt(1));
                categoria.setDescripcion(crs.getString(2));
                
                
            }
        } catch (Exception e) {
            throw e;
        }
        return categoria;
    }

    @Override
    public List<Categoria> obtener() throws Exception {
        List<Categoria> lista = new ArrayList<>();
        Categoria categoria = null;
        String sql = "SELECT codigo, descripcion FROM"
                + " categoria ";
        try {
            ConjuntoResultado crs = AccesoDatos.ejecutaQuery(sql);
            while (crs.next()) {
                categoria = new Categoria();
                categoria.setCodigo(crs.getInt(1));
                categoria.setDescripcion(crs.getString(2));                
                lista.add(categoria);
            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

}
