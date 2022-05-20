package lt.vu.persistence;

import lt.vu.entities.Publisher;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
@Alternative
public class SlowPublishersDAO implements IPublisher {
    @Inject
    private EntityManager em;

    public List<Publisher> loadAll() {
        try {
            Thread.sleep(5000);
            return em.createNamedQuery("Publisher.findAll", Publisher.class).getResultList();
        } catch (Exception e) {
            System.out.println("Unexpected exception has occured.");
        }
        return null;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Publisher publisher){
        try {
            Thread.sleep(5000);
            this.em.persist(publisher);
        } catch (Exception e) {
            System.out.println("Unexpected exception has occured");
        }
    }

    public Publisher findOne(Integer id) {
        try {
            Thread.sleep(5000);
            return em.find(Publisher.class, id);
        } catch (Exception e) {
            System.out.println("Unexpected exception has occured");
        }
        return null;
    }

    public Publisher update(Publisher publisher){
        try {
            Thread.sleep(5000);
            return em.merge(publisher);
        } catch (Exception e) {
            System.out.println("Unexpected exception has occured");
        }
        return null;
    }

    public void delete(Publisher publisher) {
        try {
            Thread.sleep(5000);
            em.remove(publisher);
        } catch (Exception e) {
            System.out.println("Unexpected exception has occured");
        }
    }
}
