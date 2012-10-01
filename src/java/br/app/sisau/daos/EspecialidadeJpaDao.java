package br.app.sisau.daos;

import br.app.sisau.beans.EspecialidadesBean;
import br.app.sisau.beans.EspecialidadesBean_;
import br.app.sisau.config.GenericJpaDao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;

/**
 *
 * @author JUNIOR.
 */
public class EspecialidadeJpaDao extends GenericJpaDao<EspecialidadesBean> {

    private static Logger logger = Logger.getLogger(EspecialidadeJpaDao.class);
    private List<EspecialidadesBean> todos;

    public EspecialidadeJpaDao() {
        super(EspecialidadesBean.class);
    }

    public EspecialidadesBean findByUsername(String username) throws Exception {
        EspecialidadesBean especialidade = null;
        EntityManager em = getEntityManager();
        try {
            Query queryEspecialidadesByUsername = em.createNamedQuery("EspecialidadesBean.findByUsername");
            queryEspecialidadesByUsername.setParameter("username", username);
            especialidade = (EspecialidadesBean) queryEspecialidadesByUsername.getSingleResult();
        } catch (NoResultException nre) {
            throw ResultadoException();
        } catch (Exception e) {
            throw e;
        }

        return especialidade;
    }

    public List<EspecialidadesBean> pesquisar() {
        List<EspecialidadesBean> listaEspecialidades = null;
        EntityManager em = getEntityManager();
        try {
            Query queryEspecialidades = em.createNamedQuery("EspecialidadesBean.findAll");
            listaEspecialidades = queryEspecialidades.getResultList();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaEspecialidades;
    }
    
 public List<EspecialidadesBean> todos() {
        List<EspecialidadesBean> listaEspecialidades = null;
        EntityManager em = getEntityManager();
        try {
            Query queryEspecialidades = em.createNamedQuery("EspecialidadesBean.findAll");
            listaEspecialidades = queryEspecialidades.getResultList();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaEspecialidades;
    
}
 
public List<EspecialidadesBean> buscarTodos(){
        List<EspecialidadesBean> listaEspecialidades = null;
        EntityManager em = getEntityManager();
        try {
            Query queryEspecialidades = em.createNamedQuery("EspecialidadesBean.findAll");
            listaEspecialidades = queryEspecialidades.getResultList();
          } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaEspecialidades;
}
        
 public List<EspecialidadesBean> pesquisar(String termoPesquisa) {
        List<EspecialidadesBean> listaEspecialidades = null;
        EntityManager em = getEntityManager();
        try {
            Query queryEspecialidades = em.createNamedQuery("EspecialidadesBean.findByKeyword");
            queryEspecialidades.setParameter("keyword", "%" + termoPesquisa + "%");
            listaEspecialidades = queryEspecialidades.getResultList();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaEspecialidades;
    }

    public EspecialidadesBean findByEmail(String email) {
        EspecialidadesBean especialidade = null;
        EntityManager em = getEntityManager();
        try {
            Query queryEspecialidadesByUsername = em.createNamedQuery("EspecialidadesBean.findByEmail");
            queryEspecialidadesByUsername.setParameter("email", email);
            especialidade = (EspecialidadesBean) queryEspecialidadesByUsername.getSingleResult();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }

        return especialidade;
    }

    public EspecialidadesBean findByCpf(String cpf) {
        EspecialidadesBean especialidade = null;
        EntityManager em = getEntityManager();
        try {
            Query queryEspecialidadesByUsername = em.createNamedQuery("EspecialidadesBean.findByCpf");
            queryEspecialidadesByUsername.setParameter("cpf", cpf);
            especialidade = (EspecialidadesBean) queryEspecialidadesByUsername.getSingleResult();

        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }

        return especialidade;
    }

    public List<EspecialidadesBean> pesquisarCriteria(String termoPesquisa) {

        List<EspecialidadesBean> listaEspecialidades = null;

        EntityManager em = getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<EspecialidadesBean> criteria = builder.createQuery(EspecialidadesBean.class);
        Root<EspecialidadesBean> root = criteria.from(EspecialidadesBean.class);

        List<Predicate> predicados = new ArrayList<Predicate>();

        if (!"".equals(termoPesquisa) && termoPesquisa != null) {
           // Predicate nome = builder.like(builder.upper(root.get(EspecialidadesBean_.nome)), ("%" + termoPesquisa + "%").toUpperCase());
           // Predicate crm = builder.like(root.get(EspecialidadesBean_.crm), ("%" + termoPesquisa + "%"));
           // Predicate soma = builder.or(nome, crm);
          //  predicados.add(soma);
        }

        criteria.where(builder.and(predicados.toArray(new Predicate[]{})));

        //criteria.orderBy(builder.asc(root.get(EspecialidadesBean_.nome)));

        Query queryEspecialidadess = em.createQuery(criteria);
        listaEspecialidades = queryEspecialidadess.getResultList();

        return listaEspecialidades;
    }

    private Exception ResultadoException() {
        throw new UnsupportedOperationException("Usuário não encontrado");
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger aLogger) {
        logger = aLogger;
    }

    public List<EspecialidadesBean> getTodos() {
        return todos;
    }

    public void setTodos(List<EspecialidadesBean> todos) {
        this.setTodos(todos);
    }
}
