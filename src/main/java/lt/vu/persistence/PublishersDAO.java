package lt.vu.persistence;

import lt.vu.entities.Publisher;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.List;

@ApplicationScoped
@Default
public class PublishersDAO implements IPublisher {

    @Inject
    private EntityManager em;

    public List<Publisher> loadAll() {
        return em.createNamedQuery("Publisher.findAll", Publisher.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Publisher publisher){
        this.em.persist(publisher);
    }

    public Publisher findOne(Integer id) {
        return em.find(Publisher.class, id);
    }

    public Publisher update(Publisher publisher){
        return em.merge(publisher);
    }

    public void delete(Publisher publisher) {em.remove(publisher);}
}
