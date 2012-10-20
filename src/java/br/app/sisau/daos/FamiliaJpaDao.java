package br.app.sisau.daos;

import br.app.sisau.beans.FamiliasBean;
import br.app.sisau.beans.FamiliasBean_;
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
 * @author Jonh Lennon
 */
public class FamiliaJpaDao extends GenericJpaDao<FamiliasBean> {

    private static Logger logger = Logger.getLogger(FamiliaJpaDao.class);
    private List<FamiliasBean> todos;

    public FamiliaJpaDao() {
        super(FamiliasBean.class);
    }

    public FamiliasBean findByUsername(String username) throws Exception {
        FamiliasBean familia = null;
        EntityManager em = getEntityManager();
        try {
            Query queryFamiliasByUsername = em.createNamedQuery("FamiliasBean.findByUsername");
            queryFamiliasByUsername.setParameter("username", username);
            familia = (FamiliasBean) queryFamiliasByUsername.getSingleResult();
        } catch (NoResultException nre) {
            throw ResultadoException();
        } catch (Exception e) {
            throw e;
        }

        return familia;
    }

    public List<FamiliasBean> pesquisar() {
        List<FamiliasBean> listaFamilias = null;
        EntityManager em = getEntityManager();
        try {
            Query queryFamilias = em.createNamedQuery("FamiliasBean.findAll");
            listaFamilias = queryFamilias.getResultList();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaFamilias;
    }
    
 public List<FamiliasBean> todos() {
        List<FamiliasBean> listaFamilias = null;
        EntityManager em = getEntityManager();
        try {
            Query queryFamilias = em.createNamedQuery("FamiliasBean.findAll");
            listaFamilias = queryFamilias.getResultList();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaFamilias;
    
}
 
public List<FamiliasBean> buscarTodos(){
        List<FamiliasBean> listaFamilias = null;
        EntityManager em = getEntityManager();
        try {
            Query queryFamilias = em.createNamedQuery("FamiliasBean.findAll");
            listaFamilias = queryFamilias.getResultList();
          } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaFamilias;
}
        
 public List<FamiliasBean> pesquisar(String termoPesquisa) {
        List<FamiliasBean> listaFamilias = null;
        EntityManager em = getEntityManager();
        try {
            Query queryFamilias = em.createNamedQuery("FamiliasBean.findByKeyword");
            queryFamilias.setParameter("keyword", "%" + termoPesquisa + "%");
            listaFamilias = queryFamilias.getResultList();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaFamilias;
    }

    public FamiliasBean findByNome(String nome) {
        FamiliasBean familia = null;
        EntityManager em = getEntityManager();
        try {
            Query queryFamiliasByNome = em.createNamedQuery("FamiliasBean.findByNome");
            queryFamiliasByNome.setParameter("nome", nome);
            familia = (FamiliasBean) queryFamiliasByNome.getSingleResult();

        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }

        return familia;
    }

    public List<FamiliasBean> pesquisarCriteria(String termoPesquisa) {

        List<FamiliasBean> listaFamilias = null;

        EntityManager em = getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<FamiliasBean> criteria = builder.createQuery(FamiliasBean.class);
        Root<FamiliasBean> root = criteria.from(FamiliasBean.class);

        List<Predicate> predicados = new ArrayList<Predicate>();

        if (!"".equals(termoPesquisa) && termoPesquisa != null) {
           // Predicate nome = builder.like(builder.upper(root.get(FamiliasBean_.nome)), ("%" + termoPesquisa + "%").toUpperCase());
           // Predicate crm = builder.like(root.get(FamiliasBean_.crm), ("%" + termoPesquisa + "%"));
           // Predicate soma = builder.or(nome, crm);
          //  predicados.add(soma);
        }

        criteria.where(builder.and(predicados.toArray(new Predicate[]{})));

        //criteria.orderBy(builder.asc(root.get(FamiliasBean_.nome)));

        Query queryFamiliass = em.createQuery(criteria);
        listaFamilias = queryFamiliass.getResultList();

        return listaFamilias;
    }

    private Exception ResultadoException() {
        throw new UnsupportedOperationException("Família não encontrada");
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger aLogger) {
        logger = aLogger;
    }

    public List<FamiliasBean> getTodos() {
        return todos;
    }

    public void setTodos(List<FamiliasBean> todos) {
        this.setTodos(todos);
    }
}
