package kwic.frontend;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import kwic.backend.IndexedString;

/**
 *
 */
@Stateless(mappedName="web/IndexedString/remote")
public class IndexedStringFacade implements IndexedStringFacadeRemote {
    @PersistenceContext(unitName = "webPU")
    private EntityManager em;

    public void create(IndexedString indexedString) {
        em.persist(indexedString);
    }

    public void edit(IndexedString indexedString) {
        em.merge(indexedString);
    }

    public void remove(IndexedString indexedString) {
        em.remove(em.merge(indexedString));
    }

    public IndexedString find(Object id) {
        return em.find(IndexedString.class, id);
    }

    public List<IndexedString> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(IndexedString.class));
        return em.createQuery(cq).getResultList();
    }

    public List<IndexedString> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(IndexedString.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<IndexedString> rt = cq.from(IndexedString.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
