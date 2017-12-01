/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.rnegocio.impljpa;

import ec.fin.coopsanjose.www.rnegocio.entidades.Categoria;
import ec.fin.coopsanjose.www.rnegocio.interfaces.ICategoriaDao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CategoriaImpl implements ICategoriaDao {

    private EntityManagerFactory emf;
    private EntityManager em;

   
    public  CategoriaImpl() {
        emf = Persistence.createEntityManagerFactory("facturacionPU");
        em = emf.createEntityManager();
    }

    @Override
    public boolean insertar(Categoria categoria) throws Exception {
        boolean resultado = false;
        try {
            em.getTransaction().begin();
            em.persist(categoria);
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
    public boolean modificar(Categoria categoria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(Categoria categoria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categoria obtener(int codigo) throws Exception {
         Categoria categoria = null;
        try {
            Query query = em.createQuery("FROM Categoria c "
                    + "WHERE c.codigo=?1");
            query.setParameter(1, codigo);
            categoria = (Categoria) query.getSingleResult();
        } catch (Exception e) {
            throw e;
        }
        return categoria;
    }

    @Override
    public List<Categoria> obtener() throws Exception {
        List<Categoria> lista = new ArrayList<>();
        try {
            Query query = em.createQuery("FROM Categoria c");
            lista = (List<Categoria>) query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

}
