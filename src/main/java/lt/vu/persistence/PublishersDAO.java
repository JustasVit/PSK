package lt.vu.persistence;

import lt.vu.entities.Publisher;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class PublishersDAO {

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
}
