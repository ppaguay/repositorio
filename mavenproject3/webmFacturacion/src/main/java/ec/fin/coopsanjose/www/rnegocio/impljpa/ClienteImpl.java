/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.www.rnegocio.impljpa;

import ec.fin.coopsanjose.www.rnegocio.entidades.Cliente;
import ec.fin.coopsanjose.www.rnegocio.interfaces.IClienteDao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ClienteImpl implements IClienteDao {

    private EntityManagerFactory emf;
    private EntityManager em;

    public ClienteImpl() {
        emf = Persistence.createEntityManagerFactory("facturacionPU");
        em = emf.createEntityManager();
    }

    @Override
    public boolean insertar(Cliente cliente) throws Exception {
        boolean resultado = false;
        try {
            em.getTransaction().begin();
            em.persist(cliente);
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
    public List<Cliente> obtener() throws Exception {
        List<Cliente> lista = new ArrayList<>();
        try {
            Query query = em.createQuery("FROM Cliente c");
            lista = (List<Cliente>) query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    @Override
    public boolean modificar(Cliente cliente) throws Exception {
        boolean resultado = false;
        try {
            em.getTransaction().begin();
            em.merge(cliente);
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
    public boolean eliminar(Cliente cliente) throws Exception {
        boolean resultado = false;
        try {
          em.getTransaction().begin();
            Cliente cli = em.getReference(Cliente.class, 
                    cliente.getCodigo());
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
    public Cliente obtener(int codigo) throws Exception {
        Cliente cliente = null;
        try {
            Query query = em.createQuery("FROM Cliente c "
                    + "WHERE c.codigo=?");
            query.setParameter(1, codigo);
            cliente = (Cliente) query.getSingleResult();
        } catch (Exception e) {
            throw e;
        }
        return cliente;
    }
}
