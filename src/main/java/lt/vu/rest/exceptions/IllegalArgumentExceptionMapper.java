package lt.vu.rest.exceptions;

import javax.persistence.OptimisticLockException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        System.out.println("Caught the exception");
        if(e instanceof NotFoundException)
            return Response.status(Response.Status.NOT_FOUND).build();
        if(e instanceof BadRequestException)
            return Response.status(Response.Status.BAD_REQUEST).build();
        if(e instanceof OptimisticLockException)
            return Response.status(Response.Status.CONFLICT).build();
        return Response.status(Response.Status.CONFLICT).build();
    }
}