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
import ec.fin.coopsanjose.www.rnegocio.entidades.Cliente;
import ec.fin.coopsanjose.www.rnegocio.entidades.Factura;
import ec.fin.coopsanjose.www.rnegocio.interfaces.IClienteDao;
import ec.fin.coopsanjose.www.rnegocio.interfaces.IFacturaDao;

public class FacturaImpl implements IFacturaDao {

    @Override
    public boolean insertar(Factura factura) throws Exception {
        boolean resultado = false;
        String sql = "INSERT INTO factura VALUES (?,?,?,?)";
        ArrayList<Parametro> lstParametro = new ArrayList<>();
        lstParametro.add(new Parametro(1, factura.getNumero()));
        lstParametro.add(new Parametro(2,
                new java.sql.Date(factura.getFecha().getTime())
        ));
        lstParametro.add(new Parametro(3, factura.getTotal()));
        lstParametro.add(new Parametro(4, factura.getCliente().getCodigo()));
        try {
            resultado = AccesoDatos.ejecutaComando(sql, lstParametro);
        } catch (Exception ex) {
            throw ex;
        }
        return resultado;
    }

    @Override
    public boolean modificar(Factura factura) throws Exception {
          boolean resultado = false;
        String sql = "UPDATE  factura SET numero=?, fecha=?,total=?,cod_cliente=?"
                + "WHERE numero=?";
        ArrayList<Parametro> lstParametro = new ArrayList<>();
        lstParametro.add(new Parametro(1, factura.getNumero()));
        lstParametro.add(new Parametro(2,factura.getFecha().getTime()));
        lstParametro.add(new Parametro(3, factura.getTotal()));
        lstParametro.add(new Parametro(4, factura.getCliente().getCodigo()));
        lstParametro.add(new Parametro(5, factura.getNumero()));
        try {
            resultado = AccesoDatos.ejecutaComando(sql, lstParametro);
        } catch (Exception ex) {
            throw ex;
        }
        return resultado;
    }

    @Override
    public boolean eliminar(Factura factura) throws Exception {
        boolean resultado = false;
        String sql = "DELETE FROM factura "
                + "WHERE numero=?";
        ArrayList<Parametro> lstParametro = new ArrayList<>();
        lstParametro.add(new Parametro(1, factura.getNumero()));
        try {
            resultado = AccesoDatos.ejecutaComando(sql, lstParametro);
        } catch (Exception ex) {
            throw ex;
        }
        return resultado;
    }

    @Override
    public Factura obtener(int numero) throws Exception {

        Factura factura = null;
        String sql = "SELECT numero, fecha, total, cod_cliente FROM"
                + " factura  where numero=?";
        try {
            ArrayList<Parametro> lstParametro = new ArrayList<>();
            lstParametro.add(new Parametro(1, numero));
            ConjuntoResultado crs = AccesoDatos.ejecutaQuery(sql,lstParametro);
            while (crs.next()) {
                factura = new Factura();
                factura.setNumero(crs.getInt(1));
                factura.setFecha(crs.getDate(2));
                factura.setTotal(crs.getDouble(3));
                IClienteDao clienteDao = new ClienteImpl();
                Cliente cliente = clienteDao.obtener(crs.getInt(4));
                factura.setCliente(cliente);

            }
        } catch (Exception e) {
            throw e;
        }

        return factura;
    }

    @Override
    public List<Factura> obtener() throws Exception {
        List<Factura> lista = new ArrayList<>();
        Factura factura = null;
        String sql = "SELECT numero, fecha, total, cod_cliente FROM"
                + " factura ";
        try {
            ConjuntoResultado crs = AccesoDatos.ejecutaQuery(sql);
            while (crs.next()) {
                factura = new Factura();
                factura.setNumero(crs.getInt(1));
                factura.setFecha(crs.getDate(2));
                factura.setTotal(crs.getDouble(3));
                IClienteDao clienteDao = new ClienteImpl();
                Cliente cliente = clienteDao.obtener(crs.getInt(4));
                factura.setCliente(cliente);
                lista.add(factura);
            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

}
