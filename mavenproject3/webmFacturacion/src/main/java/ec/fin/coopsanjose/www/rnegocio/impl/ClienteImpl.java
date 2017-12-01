/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.rnegocio.impl;

import ec.fin.coopsanjose.www.accesodatos.AccesoDatos;
import ec.fin.coopsanjose.www.accesodatos.ConjuntoResultado;
import ec.fin.coopsanjose.www.accesodatos.Parametro;
import java.util.List;
import ec.fin.coopsanjose.www.rnegocio.entidades.Cliente;
import ec.fin.coopsanjose.www.rnegocio.interfaces.ICategoriaDao;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ec.fin.coopsanjose.www.rnegocio.interfaces.IClienteDao;

public class ClienteImpl implements IClienteDao {

    @Override
    public boolean insertar(Cliente cliente) throws Exception {
        boolean respuesta = false;
        String sql = "INSERT INTO cliente VALUES (?,?,?,?,?,?)";
        ArrayList<Parametro> lstParametro = new ArrayList<>();
        lstParametro.add(new Parametro(1, cliente.getCodigo()));
        lstParametro.add(new Parametro(2, cliente.getCedula()));
        lstParametro.add(new Parametro(3, cliente.getNombres()));
        lstParametro.add(new Parametro(4, cliente.getApellidos()));
        lstParametro.add(new Parametro(5, cliente.getTelefono()));
        lstParametro.add(new Parametro(6, cliente.getCategoria().getCodigo()));

        try {
            respuesta = AccesoDatos.ejecutaComando(sql, lstParametro);
        } catch (Exception ex) {
            throw ex;
        }
        return respuesta;
    }

    @Override
    public boolean modificar(Cliente cliente) throws Exception {
        boolean respuesta = false;
        String sql = "UPDATE  cliente SET codigo=?,"
                + " cedula=?, nombres=?, apellidos=?, telefono=?,"
                + "cod_categoria=? "
                + "WHERE codigo=? ";
        ArrayList<Parametro> lstParametro = new ArrayList<>();
        lstParametro.add(new Parametro(1, cliente.getCodigo()));
        lstParametro.add(new Parametro(2, cliente.getCedula()));
        lstParametro.add(new Parametro(3, cliente.getNombres()));
        lstParametro.add(new Parametro(4, cliente.getApellidos()));
        lstParametro.add(new Parametro(5, cliente.getTelefono()));
        lstParametro.add(new Parametro(6, cliente.getCategoria().
                getCodigo()));
        lstParametro.add(new Parametro(7, cliente.getCodigo()));

        try {
            respuesta = AccesoDatos.ejecutaComando(sql, lstParametro);
        } catch (Exception ex) {
            throw ex;
        }
        return respuesta;
    }

    @Override
    public boolean eliminar(Cliente cliente) throws Exception {
        boolean respuesta = false;
        String sql = "DELETE FROM  cliente WHERE codigo=? ";
        ArrayList<Parametro> lstParametro = new ArrayList<>();
        lstParametro.add(new Parametro(1, cliente.getCodigo()));        
        try {
            respuesta = AccesoDatos.ejecutaComando(sql, lstParametro);
        } catch (Exception ex) {
            throw ex;
        }
        return respuesta;
    }

    @Override
    public Cliente obtener(int codigo) throws Exception {
        Cliente cliente = null;
        String sql = "SELECT codigo, cedula, nombres, apellidos, telefono,"
                + " cod_categoria FROM"
                + " cliente WHERE codigo =?";
        ArrayList<Parametro> lstParametro = new ArrayList<>();
        lstParametro.add(new Parametro(1, codigo));
        try {
            ConjuntoResultado crs = AccesoDatos.ejecutaQuery(sql, lstParametro);
            while (crs.next()) {
                cliente = new Cliente();
                cliente.setCodigo(crs.getInt(1));
                cliente.setCedula(crs.getString(2));
                cliente.setNombres(crs.getString(3));
                cliente.setApellidos(crs.getString(4));
                cliente.setTelefono(crs.getString(5));
                ICategoriaDao categoriadao = new CategoriaImpl();
                cliente.setCategoria(categoriadao.obtener(crs.getInt(6)));
            }
        } catch (Exception e) {
            throw e;
        }
        return cliente;
    }

    @Override
    public List<Cliente> obtener() throws Exception {
        List<Cliente> lista = new ArrayList<>();
        Cliente cliente = null;
        String sql = "SELECT codigo, cedula, nombres, apellidos, telefono, "
                + " cod_categoria FROM"
                + " cliente ";
        try {
            ConjuntoResultado crs = AccesoDatos.ejecutaQuery(sql);
            while (crs.next()) {
                cliente = new Cliente();
                cliente.setCodigo(crs.getInt(1));
                cliente.setCedula(crs.getString(2));
                cliente.setNombres(crs.getString(3));
                cliente.setApellidos(crs.getString(4));
                cliente.setTelefono(crs.getString(5));
                ICategoriaDao categoriadao = new CategoriaImpl();
                cliente.setCategoria(categoriadao.obtener(crs.getInt(6)));
                lista.add(cliente);
            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
}
