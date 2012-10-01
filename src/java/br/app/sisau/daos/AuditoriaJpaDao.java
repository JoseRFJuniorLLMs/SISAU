package br.app.sisau.daos;

import br.app.sisau.beans.AuditoriaBeanBean;
import br.app.sisau.config.GenericJpaDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author herick
 */
public class AuditoriaJpaDao extends GenericJpaDao<AuditoriaBeanBean> {

    private static final Logger logger = Logger.getLogger(AuditoriaJpaDao.class);
    
    public AuditoriaJpaDao() {
        super(AuditoriaBeanBean.class);
    }

    /**
     * Retorna os resultados ordenados de forma decrescente
     */
    public List<AuditoriaBeanBean> pesquisar() {
        List<AuditoriaBeanBean> auditoria = null;
        EntityManager em = getEntityManager();
        try {            
            Query queryProjetoByKeyword = em.createNamedQuery("AuditoriaBean.findAll");
            auditoria = queryProjetoByKeyword.getResultList();
        } catch (NoResultException nre) {
            logger.debug("Usuário não cadastrado");
        } catch (Exception e) {
            logger.error(e);
        }
        return auditoria;
    }
}
