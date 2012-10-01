package br.app.sisau.daos;

import br.app.sisau.beans.MedicosBean;
import br.app.sisau.beans.MedicosBean_;
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
public class MedicoJpaDao extends GenericJpaDao<MedicosBean> {

    private static Logger logger = Logger.getLogger(MedicoJpaDao.class);
    private List<MedicosBean> todos;

    public MedicoJpaDao() {
        super(MedicosBean.class);
    }

    public MedicosBean findByUsername(String username) throws Exception {
        MedicosBean medico = null;
        EntityManager em = getEntityManager();
        try {
            Query queryMedicosByUsername = em.createNamedQuery("MedicosBean.findByUsername");
            queryMedicosByUsername.setParameter("username", username);
            medico = (MedicosBean) queryMedicosByUsername.getSingleResult();
        } catch (NoResultException nre) {
            throw ResultadoException();
        } catch (Exception e) {
            throw e;
        }

        return medico;
    }

    public List<MedicosBean> pesquisar() {
        List<MedicosBean> listaMedicos = null;
        EntityManager em = getEntityManager();
        try {
            Query queryMedicos = em.createNamedQuery("MedicosBean.findAll");
            listaMedicos = queryMedicos.getResultList();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaMedicos;
    }
    
 public List<MedicosBean> todos() {
        List<MedicosBean> listaMedicos = null;
        EntityManager em = getEntityManager();
        try {
            Query queryMedicos = em.createNamedQuery("MedicosBean.findAll");
            listaMedicos = queryMedicos.getResultList();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaMedicos;
    
}
 
public List<MedicosBean> buscarTodos(){
        List<MedicosBean> listaMedicos = null;
        EntityManager em = getEntityManager();
        try {
            Query queryMedicos = em.createNamedQuery("MedicosBean.findAll");
            listaMedicos = queryMedicos.getResultList();
          } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaMedicos;
}
        
 public List<MedicosBean> pesquisar(String termoPesquisa) {
        List<MedicosBean> listaMedicos = null;
        EntityManager em = getEntityManager();
        try {
            Query queryMedicos = em.createNamedQuery("MedicosBean.findByKeyword");
            queryMedicos.setParameter("keyword", "%" + termoPesquisa + "%");
            listaMedicos = queryMedicos.getResultList();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }
        return listaMedicos;
    }

    public MedicosBean findByEmail(String email) {
        MedicosBean medico = null;
        EntityManager em = getEntityManager();
        try {
            Query queryMedicosByUsername = em.createNamedQuery("MedicosBean.findByEmail");
            queryMedicosByUsername.setParameter("email", email);
            medico = (MedicosBean) queryMedicosByUsername.getSingleResult();
        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }

        return medico;
    }

    public MedicosBean findByCpf(String cpf) {
        MedicosBean medico = null;
        EntityManager em = getEntityManager();
        try {
            Query queryMedicosByUsername = em.createNamedQuery("MedicosBean.findByCpf");
            queryMedicosByUsername.setParameter("cpf", cpf);
            medico = (MedicosBean) queryMedicosByUsername.getSingleResult();

        } catch (NoResultException nre) {
            getLogger().debug(nre.getStackTrace());
        } catch (Exception e) {
            getLogger().debug(e.getStackTrace());
        }

        return medico;
    }

    public List<MedicosBean> pesquisarCriteria(String termoPesquisa) {

        List<MedicosBean> listaMedicos = null;

        EntityManager em = getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<MedicosBean> criteria = builder.createQuery(MedicosBean.class);
        Root<MedicosBean> root = criteria.from(MedicosBean.class);

        List<Predicate> predicados = new ArrayList<Predicate>();

        if (!"".equals(termoPesquisa) && termoPesquisa != null) {
            Predicate nome = builder.like(builder.upper(root.get(MedicosBean_.nome)), ("%" + termoPesquisa + "%").toUpperCase());
            Predicate crm = builder.like(root.get(MedicosBean_.crm), ("%" + termoPesquisa + "%"));
            Predicate soma = builder.or(nome, crm);
            predicados.add(soma);
        }

        criteria.where(builder.and(predicados.toArray(new Predicate[]{})));

        criteria.orderBy(builder.asc(root.get(MedicosBean_.nome)));

        Query queryMedicoss = em.createQuery(criteria);
        listaMedicos = queryMedicoss.getResultList();

        return listaMedicos;
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

    public List<MedicosBean> getTodos() {
        return todos;
    }

    public void setTodos(List<MedicosBean> todos) {
        this.setTodos(todos);
    }
}
