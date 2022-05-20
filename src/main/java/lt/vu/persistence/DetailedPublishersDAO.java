package lt.vu.persistence;

import lt.vu.entities.Publisher;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

@Specializes
@ApplicationScoped
public class DetailedPublishersDAO extends PublishersDAO{
    @PostConstruct
    private void LogPublisherBookIds(Publisher publisher) {
        System.out.println("Publisher's " +publisher.getName() + " books: " + publisher.getBooks());
    }

    @Override
    public Publisher findOne(Integer id){
        Publisher publisher = super.findOne(id);
        LogPublisherBookIds(publisher);
        return publisher;
    }
}
