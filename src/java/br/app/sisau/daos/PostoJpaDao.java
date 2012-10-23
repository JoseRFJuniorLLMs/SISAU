package br.app.sisau.daos;

import br.app.sisau.beans.PostoBean;
import br.app.sisau.config.GenericJpaDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author Paulo
 */
public class PostoJpaDao extends GenericJpaDao<PostoBean> {

    private static Logger logger = Logger.getLogger(PessoaJpaDao.class);

    public PostoJpaDao() {
        super(PostoBean.class);
    }  
    
    public void gravar(PostoBean postobean){
       EntityManager em = getEntityManager();
       em.getTransaction().begin();
       em.merge(postobean);
       em.getTransaction().commit();
       em.close();      
    }
    
    public List<PostoBean> listaPostosTodos(){
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("PostoBean.findAll");
        return query.getResultList();
    }
    
    public void excluirPosto(PostoBean posto){
        EntityManager em = getEntityManager();
        PostoBean postoTemp;
        em.getTransaction().begin();
        postoTemp = em.merge(posto);
        em.remove(postoTemp);
        em.getTransaction().commit();
        em.close();
    }
    
    public List<PostoBean> pesquisarPosto(String nomePosto){
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("PostoBean.findByNome");
        query.setParameter("nome","%" + nomePosto + "%");
        return query.getResultList();
    }
   
}