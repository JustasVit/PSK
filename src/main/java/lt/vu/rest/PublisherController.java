package lt.vu.rest;

import lt.vu.decorators.IGenerator;
import lt.vu.entities.Book;
import lt.vu.entities.Publisher;
import lt.vu.interceptors.MethodLogger;
import lt.vu.persistence.BooksDAO;
import lt.vu.persistence.IPublisher;
import lt.vu.rest.dto.PublisherDTO;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Path("/rest/publishers")
public class PublisherController {

    @Inject
    private IPublisher publishersDAO;

    @Inject
    private BooksDAO booksDAO;

    @Inject
    private IGenerator randomStringGenerator;

    @PersistenceContext
    private EntityManager em;

    @Path("/{id}")
    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @MethodLogger
    public Response getById(@PathParam("id") final Integer id) {
        try {
            Publisher publisher = publishersDAO.findOne(id);

        if (publisher == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        PublisherDTO publisherDTO = new PublisherDTO(publisher);
        return Response.ok(publisherDTO).build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(PublisherDTO publisherDTO) {
        Publisher publisher = new Publisher();
        publisher.setName(publisherDTO.getName());
        publisher.setBooks(new ArrayList<>());
        publishersDAO.persist(publisher);
        return Response.ok(publisherDTO).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer publisherId, PublisherDTO publisherData) {
        try {
           Publisher existingPublisher = publishersDAO.findOne(publisherId);
           Thread.sleep(10000);
            if (existingPublisher == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            //existingPublisher.setName(publisherData.getName());
            existingPublisher.setName(randomStringGenerator.generateRandomString());
            List<Book> books = new ArrayList<>();
            for(Integer id : publisherData.getBookIds()){
                Book book = booksDAO.findOne(id);
                if(book != null) {
                    book.setPublisher(existingPublisher);
                    em.flush();
                    books.add(booksDAO.findOne(id));
                }
            }
            existingPublisher.setBooks(books);
            //publishersDAO.update(existingPublisher);
            em.flush();
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}