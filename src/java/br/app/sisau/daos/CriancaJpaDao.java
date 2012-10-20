package br.app.sisau.daos;

import br.app.sisau.beans.CriancasBean;
import br.app.sisau.beans.CriancasBean_;
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
public class CriancaJpaDao extends GenericJpaDao<CriancasBean> {

    private static Logger logger = Logger.getLogger(CriancaJpaDao.class);
    private List<CriancasBean> todos;

    public CriancaJpaDao() {
        super(CriancasBean.class);
    }

    public CriancasBean findByUsername(String username) throws Exception {
        CriancasBean crianca = null;
        EntityManager em = getEntityManager();
        try {
            Query queryCriancasByUsername = em.createNamedQuery("CriancasBean.findByUsername");
            queryCriancasByUsername.setParameter("username", username);
            crianca = (CriancasBean) queryCriancasByUsername.getSingleResult();
        } catch (NoResultException nre) {
            throw ResultadoException();
        } catch (Exception e) {
            throw e;
        }

        return crianca;
    }

    public List<CriancasBean> pesquisar() {
        List<CriancasBean> listaCriancas = null;
        EntityManager em = getEntityManager();
        try {
            Query queryCriancas = em.createNamedQuery("CriancasBean.findAll");
            listaCriancas = queryCriancas.getResultList();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaCriancas;
    }

    public List<CriancasBean> todos() {
        List<CriancasBean> listaCriancas = null;
        EntityManager em = getEntityManager();
        try {
            Query queryCriancas = em.createNamedQuery("CriancasBean.findAll");
            listaCriancas = queryCriancas.getResultList();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaCriancas;
    }

    public List<CriancasBean> buscarTodos() {
        List<CriancasBean> listaCriancas = null;
        EntityManager em = getEntityManager();
        try {
            Query queryCriancas = em.createNamedQuery("CriancasBean.findAll");
            listaCriancas = queryCriancas.getResultList();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaCriancas;
    }

    public List<CriancasBean> pesquisar(String termoPesquisa) {
        List<CriancasBean> listaCriancas = null;
        EntityManager em = getEntityManager();
        try {
            Query queryCriancas = em.createNamedQuery("CriancaBean.findByKeyword");
            queryCriancas.setParameter("keyword", "%" + termoPesquisa + "%");
            listaCriancas = queryCriancas.getResultList();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaCriancas;
    }

    public CriancasBean findByNDeclaracaoNascidoVivo(String nDeclaracaoNascidoVivo) {
        CriancasBean crianca = null;
        EntityManager em = getEntityManager();
        try {
            Query queryCriancasByNDeclaracaoNascidoVivo = em.createNamedQuery("CriancasBean.CriancasBean.findByNDeclaracaoNascidoVivo");
            queryCriancasByNDeclaracaoNascidoVivo.setParameter("nDeclaracaoNascidoVivo", nDeclaracaoNascidoVivo);
            crianca = (CriancasBean) queryCriancasByNDeclaracaoNascidoVivo.getSingleResult();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }

        return crianca;
    }

    public CriancasBean findByNCartaoSus(String nCartaoSus) {
        CriancasBean crianca = null;
        EntityManager em = getEntityManager();
        try {
            Query queryCriancasByNCartaoSus = em.createNamedQuery("CriancasBean.CriancasBean.findByNCartaoSus");
            queryCriancasByNCartaoSus.setParameter("nCartaoSus", nCartaoSus);
            crianca = (CriancasBean) queryCriancasByNCartaoSus.getSingleResult();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }

        return crianca;
    }

    public CriancasBean findByNRegistroCivil(String nRegistroCivil) {
        CriancasBean crianca = null;
        EntityManager em = getEntityManager();
        try {
            Query queryCriancasByNRegistroCivil = em.createNamedQuery("CriancasBean.findByNRegistroCivil");
            queryCriancasByNRegistroCivil.setParameter("nRegistroCivil", nRegistroCivil);
            crianca = (CriancasBean) queryCriancasByNRegistroCivil.getSingleResult();

        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }

        return crianca;
    }

    public List<CriancasBean> pesquisarCriteria(String termoPesquisa) {

        List<CriancasBean> listaCriancas = null;

        EntityManager em = getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<CriancasBean> criteria = builder.createQuery(CriancasBean.class);
        Root<CriancasBean> root = criteria.from(CriancasBean.class);

        List<Predicate> predicados = new ArrayList<Predicate>();

        if (!"".equals(termoPesquisa) && termoPesquisa != null) {
            /*
             Predicate nome = builder.like(builder.upper(root.get(CriancasBean_.nDeclaracaoNascidoVivo)), ("%" + termoPesquisa + "%").toUpperCase());
             Predicate crm = builder.like(root.get(CriancasBean_.nCartaoSus), ("%" + termoPesquisa + "%"));
             Predicate soma = builder.or(nome, crm);
             predicados.add(soma);
             */
        }

        criteria.where(builder.and(predicados.toArray(new Predicate[]{})));

        //criteria.orderBy(builder.asc(root.get(CriancasBean_.nDeclaracaoNascidoVivo)));

        Query queryCriancass = em.createQuery(criteria);
        listaCriancas = queryCriancass.getResultList();

        return listaCriancas;
    }

    private Exception ResultadoException() {
        throw new UnsupportedOperationException("Criança não encontrada");
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger aLogger) {
        logger = aLogger;
    }

    public List<CriancasBean> getTodos() {
        return todos;
    }

    public void setTodos(List<CriancasBean> todos) {
        this.setTodos(todos);
    }
}
