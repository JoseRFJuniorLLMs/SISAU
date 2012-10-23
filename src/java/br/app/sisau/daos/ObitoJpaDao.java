package br.app.sisau.daos;

import br.app.sisau.beans.ObitosBean;
import br.app.sisau.beans.ObitosBean_;
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
public class ObitoJpaDao extends GenericJpaDao<ObitosBean> {

    private static Logger logger = Logger.getLogger(ObitoJpaDao.class);
    private List<ObitosBean> todos;

    public ObitoJpaDao() {
        super(ObitosBean.class);
    }

    public ObitosBean findByUsername(String username) throws Exception {
        ObitosBean medico = null;
        EntityManager em = getEntityManager();
        try {
            Query queryObitosByUsername = em.createNamedQuery("ObitosBean.findByUsername");
            queryObitosByUsername.setParameter("username", username);
            medico = (ObitosBean) queryObitosByUsername.getSingleResult();
        } catch (NoResultException nre) {
            throw ResultadoException();
        } catch (Exception e) {
            throw e;
        }

        return medico;
    }

    public List<ObitosBean> pesquisar() {
        List<ObitosBean> listaObitos = null;
        EntityManager em = getEntityManager();
        try {
            Query queryObitos = em.createNamedQuery("ObitosBean.findAll");
            listaObitos = queryObitos.getResultList();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaObitos;
    }
    
 public List<ObitosBean> todos() {
        List<ObitosBean> listaObitos = null;
        EntityManager em = getEntityManager();
        try {
            Query queryObitos = em.createNamedQuery("ObitosBean.findAll");
            listaObitos = queryObitos.getResultList();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaObitos;
    
}
 
public List<ObitosBean> buscarTodos(){
        List<ObitosBean> listaObitos = null;
        EntityManager em = getEntityManager();
        try {
            Query queryObitos = em.createNamedQuery("ObitosBean.findAll");
            listaObitos = queryObitos.getResultList();
          } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaObitos;
}
        
 public List<ObitosBean> pesquisar(String termoPesquisa) {
        List<ObitosBean> listaObitos = null;
        EntityManager em = getEntityManager();
        try {
            Query queryObitos = em.createNamedQuery("ObitosBean.findByKeyword");
            queryObitos.setParameter("keyword", "%" + termoPesquisa + "%");
            listaObitos = queryObitos.getResultList();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaObitos;
    }

    public ObitosBean findByEmail(String email) {
        ObitosBean medico = null;
        EntityManager em = getEntityManager();
        try {
            Query queryObitosByUsername = em.createNamedQuery("ObitosBean.findByEmail");
            queryObitosByUsername.setParameter("email", email);
            medico = (ObitosBean) queryObitosByUsername.getSingleResult();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }

        return medico;
    }

    public ObitosBean findByCpf(String cpf) {
        ObitosBean medico = null;
        EntityManager em = getEntityManager();
        try {
            Query queryObitosByUsername = em.createNamedQuery("ObitosBean.findByCpf");
            queryObitosByUsername.setParameter("cpf", cpf);
            medico = (ObitosBean) queryObitosByUsername.getSingleResult();

        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }

        return medico;
    }

    public List<ObitosBean> pesquisarCriteria(String termoPesquisa) {

        List<ObitosBean> listaObitos = null;

        EntityManager em = getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<ObitosBean> criteria = builder.createQuery(ObitosBean.class);
        Root<ObitosBean> root = criteria.from(ObitosBean.class);

        List<Predicate> predicados = new ArrayList<Predicate>();

        if (!"".equals(termoPesquisa) && termoPesquisa != null) {
            Predicate nome = builder.like(builder.upper(root.get(ObitosBean_.causa)), ("%" + termoPesquisa + "%").toUpperCase());
            Predicate crm = builder.like(root.get(ObitosBean_.causa), ("%" + termoPesquisa + "%"));
            Predicate soma = builder.or(nome, crm);
            predicados.add(soma);
        }

        criteria.where(builder.and(predicados.toArray(new Predicate[]{})));

        criteria.orderBy(builder.asc(root.get(ObitosBean_.causa)));

        Query queryObitoss = em.createQuery(criteria);
        listaObitos = queryObitoss.getResultList();

        return listaObitos;
    }

    private Exception ResultadoException() {
        throw new UnsupportedOperationException("Usuário não encontrado");
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger aLogger) {
        logPessoaogger;
    }

    public List<ObitosBean> getTodos() {
        return todos;
    }

    public void setTodos(List<ObitosBean> todos) {
        this.setTodos(todos);
    }
}
