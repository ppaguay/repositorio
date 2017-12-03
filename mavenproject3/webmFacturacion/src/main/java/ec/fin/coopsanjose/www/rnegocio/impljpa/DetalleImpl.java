/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.rnegocio.impljpa;

import ec.fin.coopsanjose.www.rnegocio.entidades.Detalle;
import ec.fin.coopsanjose.www.rnegocio.interfaces.IDetalleDao;
import ec.fin.coopsanjose.www.rnegocio.interfaces.IDetalleDao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DetalleImpl implements IDetalleDao {

    private EntityManagerFactory emf;
    private EntityManager em;

    public DetalleImpl() {
        emf = Persistence.createEntityManagerFactory("facturacionPU");
        em = emf.createEntityManager();
    }

    @Override
    public boolean insertar(Detalle detalle) throws Exception {
        boolean resultado = false;
        try {
            em.getTransaction().begin();
            em.persist(detalle);
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
    public List<Detalle> obtener() throws Exception {
        List<Detalle> lista = new ArrayList<>();
        try {
            Query query = em.createQuery("FROM Detalle c");
            lista = (List<Detalle>) query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    @Override
    public boolean modificar(Detalle detalle) throws Exception {
        boolean resultado = false;
        try {
            em.getTransaction().begin();
            em.merge(detalle);
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
    public boolean eliminar(Detalle detalle) throws Exception {
        boolean resultado = false;
        try {
          em.getTransaction().begin();
            Detalle cli = em.getReference(Detalle.class, 
                    detalle.getDetallePK());
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
    public Detalle obtener(int numero, int cod_producto) throws Exception {
        Detalle detalle = null;
        try {
            Query query = em.createQuery("FROM Detalle c "
                    + "WHERE c.num_factura=?1 and c.cod_producto=?2");
            query.setParameter(1, numero);
            query.setParameter(2, cod_producto);
            detalle = (Detalle) query.getSingleResult();
        } catch (Exception e) {
            throw e;
        }
        return detalle;
    }
}
