package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Publisher;
import lt.vu.persistence.PublishersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Publishers {

    @Inject
    private PublishersDAO publishersDAO;

    @Getter @Setter
    private Publisher publisherToCreate = new Publisher();

    @Getter
    private List<Publisher> allPublishers;

    @PostConstruct
    public void init(){
        loadAllTeams();
    }

    @Transactional
    public void createPublisher(){
        this.publishersDAO.persist(publisherToCreate);
    }

    private void loadAllTeams(){
        this.allPublishers = publishersDAO.loadAll();
    }
}
