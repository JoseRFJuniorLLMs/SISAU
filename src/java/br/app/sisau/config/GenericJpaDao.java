package br.app.sisau.config;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Herick
 */
public class GenericJpaDao<tipo> {

    private Class<? extends tipo> clazz = null;
    //@PersistenceContext
    private EntityManager entityManager;   

    public GenericJpaDao(Class<? extends tipo> clazz) {
        init(clazz,"AppTestePU");
    }

    public GenericJpaDao(Class<? extends tipo> clazz, String EMF) {
        init(clazz,EMF);
    }

    private void init(Class<? extends tipo> clazz, String EMF){
        this.clazz = clazz;        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AppTestePU");
        entityManager = emf.createEntityManager();        
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(tipo p) {
        EntityManager em = null;
        em = getEntityManager();
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

    public void update(tipo p) {
        EntityManager em = null;
        em = getEntityManager();
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
    }

    public void delete(tipo p) {
        EntityManager em = null;
        em = getEntityManager();
        em.getTransaction().begin();
        Object c = em.merge(p);
        em.remove(c);
        em.getTransaction().commit();
    }

    public tipo findEntity(Object id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        tipo retorno = em.find(clazz, id);
        em.getTransaction().commit();
        return retorno;
    }

    public List<tipo> findEntities() {
        return findEntities(true, -1, -1);
    }

    public List<tipo> findEntities(int maxResults, int firstResult) {
        return findEntities(false, maxResults, firstResult);
    }

    public List<tipo> findEntities(boolean all, int maxResults, int firstResult, String... ordenacoes) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        StringBuffer sql = new StringBuffer();
        sql.append("select o from " + clazz.getSimpleName() + " o ");
        if (ordenacoes != null && ordenacoes.length > 0) {
            sql.append("ORDER BY");
            for (int i = 0; i < ordenacoes.length; i++) {
                sql.append(" " + ordenacoes[i]);
                if (i < ordenacoes.length - 1) {
                    sql.append(",");
                }
            }
        }
        Query q = em.createQuery(sql.toString());
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        em.getTransaction().commit();
        return q.getResultList();
    }
}
