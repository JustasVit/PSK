package lt.vu.persistence;

import lt.vu.entities.Publisher;

import javax.persistence.EntityManager;
import java.util.List;

public interface IPublisher {
    public List<Publisher> loadAll();

    public void setEm(EntityManager em);

    public void persist(Publisher publisher);

    public Publisher findOne(Integer id);

    public Publisher update(Publisher publisher);

    public void delete(Publisher publisher);
}
