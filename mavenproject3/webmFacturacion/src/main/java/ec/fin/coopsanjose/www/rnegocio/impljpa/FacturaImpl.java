/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.rnegocio.impljpa;

import ec.fin.coopsanjose.www.rnegocio.entidades.Factura;
import ec.fin.coopsanjose.www.rnegocio.interfaces.IFacturaDao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FacturaImpl implements IFacturaDao {

    private EntityManagerFactory emf;
    private EntityManager em;

    public FacturaImpl() {
        emf = Persistence.createEntityManagerFactory("facturacionPU");
        em = emf.createEntityManager();
    }

    @Override
    public boolean insertar(Factura factura) throws Exception {
        boolean resultado = false;
        try {
            em.getTransaction().begin();
            em.persist(factura);
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
    public List<Factura> obtener() throws Exception {
        List<Factura> lista = new ArrayList<>();
        try {
            Query query = em.createQuery("FROM Factura c");
            lista = (List<Factura>) query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    @Override
    public boolean modificar(Factura factura) throws Exception {
        boolean resultado = false;
        try {
            em.getTransaction().begin();
            em.merge(factura);
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
    public boolean eliminar(Factura factura) throws Exception {
        boolean resultado = false;
        try {
          em.getTransaction().begin();
            Factura cli = em.getReference(Factura.class, 
                    factura.getNumero());
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
    public Factura obtener(int codigo) throws Exception {
        Factura factura = null;
        try {
            Query query = em.createQuery("FROM Factura c "
                    + "WHERE c.numero=?1");
            query.setParameter(1, codigo);
            factura = (Factura) query.getSingleResult();
        } catch (Exception e) {
            throw e;
        }
        return factura;
    }
}
