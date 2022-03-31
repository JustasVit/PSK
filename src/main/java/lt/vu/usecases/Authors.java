package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Author;
import lt.vu.entities.Publisher;
import lt.vu.persistence.AuthorsDAO;
import lt.vu.persistence.PublishersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Authors {

    @Inject
    private AuthorsDAO authorsDAO;

    @Getter
    @Setter
    private Author authorToCreate = new Author();

    @Getter
    private List<Author> allAuthors;

    @PostConstruct
    public void init(){
        loadAllAuthors();
    }

    @Transactional
    public void createAuthor(){
        this.authorsDAO.persist(authorToCreate);
    }

    private void loadAllAuthors(){
        this.allAuthors = authorsDAO.loadAll();
    }
}
