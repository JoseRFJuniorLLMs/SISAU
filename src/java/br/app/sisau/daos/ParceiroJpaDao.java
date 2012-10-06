package br.app.sisau.daos;

import br.app.sisau.beans.ParceirosBean;
import br.app.sisau.beans.ParceirosBean_;
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
 * @author Ewerton
 */
public class ParceiroJpaDao extends GenericJpaDao<ParceirosBean> {
    private static Logger logger = Logger.getLogger(ParceiroJpaDao.class);
    private List<ParceirosBean> todos;
    private Predicate soma;
    
    public ParceiroJpaDao() {
        super(ParceirosBean.class);
    }
    
    public ParceirosBean findByUsername(String username) throws Exception {
        ParceirosBean parceiro = null;
        EntityManager em = getEntityManager();
        try {
            Query queryParceirosByUsername = em.createNamedQuery("ParceirosBean.findByUsername");
            queryParceirosByUsername.setParameter("username", username);
            parceiro = (ParceirosBean) queryParceirosByUsername.getSingleResult();
        } catch (NoResultException nre) {
            throw ResultadoException();
        } catch (Exception e) {
            throw e;
        }

        return parceiro;
    }
     public List<ParceirosBean> pesquisar() {
        List<ParceirosBean> listaParceiros = null;
        EntityManager em = getEntityManager();
        try {
            Query queryParceiros = em.createNamedQuery("ParceirosBean.findAll");
            listaParceiros = queryParceiros.getResultList();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaParceiros;
    }

public List<ParceirosBean> todos() {
        List<ParceirosBean> listaParceiros = null;
        EntityManager em = getEntityManager();
        try {
            Query queryParceiros = em.createNamedQuery("ParceirosBean.findAll");
            listaParceiros = queryParceiros.getResultList();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaParceiros;
    
}

public List<ParceirosBean> buscarTodos(){
        List<ParceirosBean> listaParceiros = null;
        EntityManager em = getEntityManager();
        try {
            Query queryParceiros = em.createNamedQuery("ParceirosBean.findAll");
            listaParceiros = queryParceiros.getResultList();
          } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaParceiros;
}
public List<ParceirosBean> pesquisar(String termoPesquisa) {
        List<ParceirosBean> listaParceiros = null;
        EntityManager em = getEntityManager();
        try {
            Query queryParceiros = em.createNamedQuery("ParceirosBean.findByKeyword");
            queryParceiros.setParameter("keyword", "%" + termoPesquisa + "%");
            listaParceiros = queryParceiros.getResultList();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaParceiros;
    }

public List<ParceirosBean> pesquisarCriteria(String termoPesquisa) {

        List<ParceirosBean> listaParceiros = null;

        EntityManager em = getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<ParceirosBean> criteria = builder.createQuery(ParceirosBean.class);
        Root<ParceirosBean> root = criteria.from(ParceirosBean.class);

        List<Predicate> predicados = new ArrayList<Predicate>();

        if (!"".equals(termoPesquisa) && termoPesquisa != null) {
            Predicate parceiro = builder.like(builder.upper(root.get(ParceirosBean_.parceiro)), ("%" + termoPesquisa + "%").toUpperCase());
            predicados.add(soma);
        }

        criteria.where(builder.and(predicados.toArray(new Predicate[]{})));

        criteria.orderBy(builder.asc(root.get(ParceirosBean_.parceiro)));

        Query queryMedicoss = em.createQuery(criteria);
        listaParceiros = queryMedicoss.getResultList();

        return listaParceiros;
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

    public List<ParceirosBean> getTodos() {
        return todos;
    }

    public void setTodos(List<ParceirosBean> todos) {
        this.setTodos(todos);
    }
}



