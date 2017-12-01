/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.accesodatos;

import java.sql.*;
import java.util.*;


public class AccesoDatos {

    public static boolean ejecutaComando(String comando, ArrayList<Parametro> parametros) throws Exception {
        boolean respuesta = false;
        PreparedStatement ptrs = null;
        Connection con = null;
        Global global = new Global();

        try {

            Class.forName(global.getDRIVER());
            try {
                con = DriverManager.getConnection(global.getURL(), global.getUSER(), global.getPASS());
                ptrs = con.prepareStatement(comando);
                for (Parametro parametro : parametros) {

                    if (parametro.getValor() instanceof java.util.Date) {
                        java.sql.Date fecha = new java.sql.Date(((java.util.Date) parametro.getValor()).getTime());
                        ptrs.setObject(parametro.getPosicion(), fecha);
                    } else {
                        ptrs.setObject(parametro.getPosicion(), parametro.getValor());
                    }

                }
                int i = ptrs.executeUpdate();
                if (i > 0) {
                    respuesta = true;
                }
                ptrs.close();
                ptrs = null;
            } catch (SQLException exConec) {
                throw exConec;
            } finally {
                try {
                    if (con != null) {
                        if (!(con.isClosed())) {
                            con.close();
                        }
                        con = null;
                    }
                } catch (Exception ex) {
                    throw ex;
                }
            }
        } catch (ClassNotFoundException exCarga) {
            throw exCarga;
        }
        return respuesta;
    }

    public static Object ejecutaComandoObjeto(String comando, ArrayList<Parametro> parametros) throws Exception {

        Object respuesta = null;
        PreparedStatement ptrs = null;
        Connection con = null;
        Global global = new Global();
        try {
            Class.forName(global.getDRIVER());
            try {
                con = DriverManager.getConnection(global.getURL(), global.getUSER(), global.getPASS());
                ptrs = con.prepareStatement(comando);
                for (Parametro parametro : parametros) {
                    if (parametro.getValor() instanceof java.util.Date) {
                        java.sql.Date fecha = new java.sql.Date(((java.util.Date) parametro.getValor()).getTime());
                        ptrs.setObject(parametro.getPosicion(), fecha);
                    } else {
                        ptrs.setObject(parametro.getPosicion(), parametro.getValor());
                    }
                }
                ResultSet rst = ptrs.executeQuery();
                if (rst.next()) {
                   respuesta=rst.getObject(1);
                }
                rst.close();
                ptrs.close();
                rst = null;
                ptrs = null;

            } catch (SQLException exConec) {
                throw exConec;
            } finally {
                try {
                    if (con != null) {
                        if (!(con.isClosed())) {
                            con.close();
                        }
                        con = null;
                    }
                } catch (Exception ex) {
                    throw ex;
                }
            }
        } catch (ClassNotFoundException exCarga) {
            throw exCarga;
        }
        return respuesta;
    }

    public static ConjuntoResultado ejecutaQuery(String query) throws Exception {
        Global global = new Global();
        ResultSet rs = null;

        PreparedStatement pst = null;
        ConjuntoResultado conj = new ConjuntoResultado();
        Connection con = null;
        try {
            Class.forName(global.getDRIVER());
            try {
                con = DriverManager.getConnection(global.getURL(), global.getUSER(), global.getPASS());
                pst = con.prepareStatement(query);
                rs = pst.executeQuery();

                conj.Fill(rs);
                rs.close();
                pst.close();
                rs = null;
                pst = null;

            } catch (SQLException exConec) {
                throw exConec;
            } finally {
                try {
                    if (con != null) {
                        if (!(con.isClosed())) {
                            con.close();
                        }
                        con = null;
                    }
                } catch (Exception ex) {
                    throw ex;
                }
            }
        } catch (ClassNotFoundException exCarga) {
            throw exCarga;
        }
        return conj;
    }

    public static ConjuntoResultado ejecutaQuery(String query, ArrayList<Parametro> parametros) throws Exception {

        ResultSet rs = null;
        PreparedStatement ptrs = null;
        ConjuntoResultado conj = new ConjuntoResultado();
        Connection con = null;
        Global global = new Global();
        try {
            Class.forName(global.getDRIVER());
            try {
                String url = global.getURL();
                con = DriverManager.getConnection(url, global.getUSER(), global.getPASS());
                ptrs = con.prepareStatement(query);
                for (Parametro parametro : parametros) {
                    if (parametro.getValor() instanceof java.util.Date) {
                        java.sql.Date fecha = new java.sql.Date(((java.util.Date) parametro.getValor()).getTime());
                        ptrs.setObject(parametro.getPosicion(), fecha);
                    } else {
                        ptrs.setObject(parametro.getPosicion(), parametro.getValor());
                    }
                }
                rs = ptrs.executeQuery();
                conj.Fill(rs);
                rs.close();
                ptrs.close();
                rs = null;
                ptrs = null;
            } catch (SQLException exConec) {
                throw exConec;
            } finally {
                try {
                    if (con != null) {
                        if (!(con.isClosed())) {
                            con.close();
                        }
                        con = null;
                    }
                } catch (Exception ex) {
                    throw ex;
                }
            }
        } catch (ClassNotFoundException exCarga) {
            throw exCarga;
        }
        return conj;
    }

}
