package lt.vu.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Author;
import lt.vu.entities.Book;
import lt.vu.persistence.AuthorsDAO;
import lt.vu.persistence.BooksDAO;
import lt.vu.persistence.PublishersDAO;
import lt.vu.entities.Publisher;

@Model
public class GenerateBooksForPublisher implements Serializable {

    @Inject
    private PublishersDAO publishersDAO;

    @Inject
    private BooksDAO booksDAO;

    @Getter @Setter
    private Publisher publisher;

    @Getter @Setter
    private Book bookToCreate = new Book();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer publisherId = Integer.parseInt(requestParameters.get("publisherId"));
        this.publisher = publishersDAO.findOne(publisherId);
    }

    @Transactional
    public void createBook() {
        bookToCreate.setPublisher(this.publisher);
        System.out.println(bookToCreate.getBookAuthors().size());
        booksDAO.persist(bookToCreate);
    }
}
