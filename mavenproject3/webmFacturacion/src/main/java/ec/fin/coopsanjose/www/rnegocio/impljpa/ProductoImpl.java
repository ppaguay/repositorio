/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.rnegocio.impljpa;

import ec.fin.coopsanjose.www.rnegocio.impl.*;
import ec.fin.coopsanjose.www.accesodatos.AccesoDatos;
import ec.fin.coopsanjose.www.accesodatos.ConjuntoResultado;
import ec.fin.coopsanjose.www.accesodatos.Parametro;
import ec.fin.coopsanjose.www.rnegocio.entidades.Producto;
import ec.fin.coopsanjose.www.rnegocio.interfaces.IProductoDao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class ProductoImpl implements IProductoDao {
    private EntityManagerFactory emf;
    private EntityManager em;

    public ProductoImpl() {
        emf = Persistence.createEntityManagerFactory("facturacionPU");
        em = emf.createEntityManager();
    }
     @Override
    public boolean insertar(Producto producto) throws Exception {
        boolean resultado = false;
        try {
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();
            resultado = true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
            emf.close();
        }
        return resultado;
    }

    @Override
    public boolean modificar(Producto producto) throws Exception {
        boolean resultado = false;
        try {
            em.getTransaction().begin();
            em.merge(producto);
            em.getTransaction().commit();
            resultado = true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
            emf.close();
        }
        return resultado;
    }

    @Override
    public boolean eliminar(Producto producto) throws Exception {
        boolean resultado = false;
        try {
          em.getTransaction().begin();
            Producto cli = em.getReference(Producto.class, 
                    producto.getCodigo());
            em.remove(cli);  
            em.getTransaction().commit();
            resultado = true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
            emf.close();
        }
        return resultado;
    }



    @Override
    public Producto obtener(int codigo) throws Exception {
         Producto producto = null;
        try {
            Query query = em.createQuery("FROM Producto c "
                    + "WHERE c.codigo=?1");
            query.setParameter(1, codigo);
            producto = (Producto) query.getSingleResult();
        } catch (Exception e) {
            throw e;
        }
        return producto;
    }

    @Override
    public List<Producto> obtener() throws Exception {
        List<Producto> lista = new ArrayList<>();
        try {
            Query query = em.createQuery("FROM Producto c");
            lista = (List<Producto>) query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
}
