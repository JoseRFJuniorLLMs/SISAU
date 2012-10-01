/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.sisau.security.daos;

import br.app.sisau.config.GenericJpaDao;
import br.app.sisau.security.beans.ObjetoProtegidoBean;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author Herick
 */
public class ObjetoProtegidoJpaDao extends GenericJpaDao<ObjetoProtegidoBean> {
    
    private static final Logger logger = Logger.getLogger(ObjetoProtegidoJpaDao.class);
    
    public ObjetoProtegidoJpaDao() {
        super(ObjetoProtegidoBean.class);
    }

    public List<ObjetoProtegidoBean> pesquisar() {
        List<ObjetoProtegidoBean> objetosProtegidos = null;
        EntityManager em = getEntityManager();
        try {
            Query queryObjetoProtegidoByKeyword = em.createNamedQuery("ObjetoProtegidoBean.findAll");
            objetosProtegidos = queryObjetoProtegidoByKeyword.getResultList();
        } catch (NoResultException nre) {
            logger.debug("Usuário não cadastrado");
        } catch (Exception e) {
            logger.error(e);
        }
        return objetosProtegidos;
    }

    public List<ObjetoProtegidoBean> pesquisar(String termoPesquisa) {
        List<ObjetoProtegidoBean> objetosProtegidos = null;
        EntityManager em = getEntityManager();
        try {
            Query queryObjetoProtegidoByKeyword = em.createNamedQuery("ObjetoProtegidoBean.findByKeyword");
            queryObjetoProtegidoByKeyword.setParameter("nome", "%" + termoPesquisa + "%");
            objetosProtegidos = queryObjetoProtegidoByKeyword.getResultList();
        } catch (NoResultException nre) {
            logger.debug("Usuário não cadastrado");
        } catch (Exception e) {
            logger.error(e);
        }

        return objetosProtegidos;
    }
}
