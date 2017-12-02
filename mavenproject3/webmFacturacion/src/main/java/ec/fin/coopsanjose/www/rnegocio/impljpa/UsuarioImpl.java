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
import ec.fin.coopsanjose.www.rnegocio.entidades.Usuario;
import ec.fin.coopsanjose.www.rnegocio.interfaces.IUsuarioDao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class UsuarioImpl implements IUsuarioDao {
    private EntityManagerFactory emf;
    private EntityManager em;

    public UsuarioImpl() {
        emf = Persistence.createEntityManagerFactory("facturacionPU");
        em = emf.createEntityManager();
    }
     @Override
    public boolean insertar(Usuario usuario) throws Exception {
        boolean resultado = false;
        try {
            em.getTransaction().begin();
            em.persist(usuario);
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
    public boolean modificar(Usuario usuario) throws Exception {
        boolean resultado = false;
        try {
            em.getTransaction().begin();
            em.merge(usuario);
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
    public boolean eliminar(Usuario usuario) throws Exception {
        boolean resultado = false;
        try {
          em.getTransaction().begin();
            Usuario cli = em.getReference(Usuario.class, 
                    usuario.getCodigo());
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
    public Usuario obtener(int codigo) throws Exception {
         Usuario usuario = null;
        try {
            Query query = em.createQuery("FROM Usuario c "
                    + "WHERE c.codigo=?1");
            query.setParameter(1, codigo);
            usuario = (Usuario) query.getSingleResult();
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }

    @Override
    public List<Usuario> obtener() throws Exception {
        List<Usuario> lista = new ArrayList<>();
        try {
            Query query = em.createQuery("FROM Usuario c");
            lista = (List<Usuario>) query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
}
