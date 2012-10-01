package br.app.sisau.daos;

import br.app.sisau.beans.PessoaBean;
import br.app.sisau.beans.PessoaBean_;
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
 * @author Herick
 */
public class PessoaJpaDao extends GenericJpaDao<PessoaBean> {

    private static Logger logger = Logger.getLogger(PessoaJpaDao.class);
    private List<PessoaBean> todos;

    public PessoaJpaDao() {
        super(PessoaBean.class);
    }

    public PessoaBean findByUsername(String username) throws Exception {
        PessoaBean pessoa = null;
        EntityManager em = getEntityManager();
        try {
            Query queryPessoaByUsername = em.createNamedQuery("PessoaBean.findByUsername");
            queryPessoaByUsername.setParameter("username", username);
            pessoa = (PessoaBean) queryPessoaByUsername.getSingleResult();
        } catch (NoResultException nre) {
            throw ResultadoException();
        } catch (Exception e) {
            throw e;
        }

        return pessoa;
    }

    public List<PessoaBean> pesquisar() {
        List<PessoaBean> lstPessoas = null;
        EntityManager em = getEntityManager();
        try {
            Query queryPessoa = em.createNamedQuery("PessoaBean.findAll");
            lstPessoas = queryPessoa.getResultList();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return lstPessoas;
    }
    
 public List<PessoaBean> todos() {
        List<PessoaBean> lstPessoas = null;
        EntityManager em = getEntityManager();
        try {
            Query queryPessoa = em.createNamedQuery("PessoaBean.findAll");
            lstPessoas = queryPessoa.getResultList();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return lstPessoas;
    
}
         
    public List<PessoaBean> pesquisar(String termoPesquisa) {
        List<PessoaBean> lstPessoas = null;
        EntityManager em = getEntityManager();
        try {
            Query queryPessoa = em.createNamedQuery("PessoaBean.findByKeyword");
            queryPessoa.setParameter("keyword", "%" + termoPesquisa + "%");
            lstPessoas = queryPessoa.getResultList();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return lstPessoas;
    }

    public PessoaBean findByEmail(String email) {
        PessoaBean pessoa = null;
        EntityManager em = getEntityManager();
        try {
            Query queryPessoaByUsername = em.createNamedQuery("PessoaBean.findByEmail");
            queryPessoaByUsername.setParameter("email", email);
            pessoa = (PessoaBean) queryPessoaByUsername.getSingleResult();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }

        return pessoa;
    }

    public PessoaBean findByCpf(String cpf) {
        PessoaBean pessoa = null;
        EntityManager em = getEntityManager();
        try {
            Query queryPessoaByUsername = em.createNamedQuery("PessoaBean.findByCpf");
            queryPessoaByUsername.setParameter("cpf", cpf);
            pessoa = (PessoaBean) queryPessoaByUsername.getSingleResult();

        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }

        return pessoa;
    }

    public List<PessoaBean> pesquisarCriteria(String termoPesquisa) {

        List<PessoaBean> lstPessoas = null;

        EntityManager em = getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<PessoaBean> criteria = builder.createQuery(PessoaBean.class);
        Root<PessoaBean> root = criteria.from(PessoaBean.class);

        List<Predicate> predicados = new ArrayList<Predicate>();

        if (!"".equals(termoPesquisa) && termoPesquisa != null) {
            Predicate username = builder.like(builder.upper(root.get(PessoaBean_.username)), ("%" + termoPesquisa + "%").toUpperCase());
            Predicate nome = builder.like(builder.upper(root.get(PessoaBean_.nome)), ("%" + termoPesquisa + "%").toUpperCase());
            Predicate rg = builder.like(root.get(PessoaBean_.rg), ("%" + termoPesquisa + "%"));
            Predicate cpf = builder.like(root.get(PessoaBean_.cpf), ("%" + termoPesquisa + "%"));
            //Predicate email = builder.like(root.get(PessoaBean_.email), ("%" + termoPesquisa + "%"));

            Predicate soma = builder.or(username, nome, rg, cpf);
            predicados.add(soma);
        }

        criteria.where(builder.and(predicados.toArray(new Predicate[]{})));

        criteria.orderBy(builder.asc(root.get(PessoaBean_.nome)));

        Query queryPessoas = em.createQuery(criteria);
        lstPessoas = queryPessoas.getResultList();

        return lstPessoas;
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

    public List<PessoaBean> getTodos() {
        return todos;
    }

    public void setTodos(List<PessoaBean> todos) {
        this.todos = todos;
    }
}
