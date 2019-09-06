package soa.jaxrslabs.helloepsiwebservicerestexercice1;

import soa.jaxrslabs.helloepsiwebservicerestexercice1.BookTrainBD;
import soa.jaxrslabs.helloepsiwebservicerestexercice1.BookTrain;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author M34LMAR
 */
public class BookTrainResource {

    @Path("/booktrains") // Chemin suivant /trains/booktrains pour invoquer cette m�thode
    public BookTrainResource getBookTrainResource() {
        return new BookTrainResource();
    }

    @POST // M�thode HTTP utilis�e pour d�clencher cette m�thode
    public Response createBookTrain(String numTrain, int numberPlaces) {
        Train currentTrain = null;
        for (Train current : BookTrainBD.getTrains()) {
            if (current.getNumTrain().equals(numTrain)) {
                currentTrain = current;
            }
        }
        if (currentTrain == null) {
            return Response
                    .status(Status.NO_CONTENT)
                    .build();
        }
        BookTrain newBookTrain = new BookTrain();
        newBookTrain.setNumberPlaces(numberPlaces);
        newBookTrain.setCurrentTrain(currentTrain);
        newBookTrain.setBookNumber(Long.toString(System.currentTimeMillis()));
        BookTrainBD.getBookTrains().add(newBookTrain);
        
        
        return Response
                .status(Status.OK)
                .entity(newBookTrain.getBookNumber())
                .build();
    }

    @GET // M�thode HTTP utilis�e pour d�clencher cette m�thode
    public Response getBookTrains() {
        System.out.println("getBookTrains");
        return Response
                .status(Status.OK)
                .entity(BookTrainBD.getBookTrains())
                .build();
    }

    @GET // M�thode HTTP utilis�e pour d�clencher cette m�thode
    @Path("{id}") // Chemin de fa�on � int�grer un template parameter (id)
    public Response getBookTrain(@PathParam("id") String bookNumber) {
        List<BookTrain> bookTrains = BookTrainBD.getBookTrains();
        for (BookTrain current : bookTrains) {
            System.out.println("> mon train " + current.toString());
            if (current.getBookNumber().equals(bookNumber)) {
                return Response
                        .status(Status.OK)
                        .entity(current)
                        .build();
            }
        }
        return Response
                .status(Status.NO_CONTENT)
                .build();
    }

    @DELETE // M�thode HTTP utilis�e pour d�clencher cette m�thode
    @Path("{id}") // Chemin de fa�on � int�grer un template parameter (id)
    public Response removeBookTrain(@PathParam("id") String bookNumber) {
        BookTrain currentBookTrain = null;
        for (BookTrain current : BookTrainBD.getBookTrains()) {
            if (current.getBookNumber().equals(bookNumber)) {
                currentBookTrain = current;
            }
        }
        return Response.status(Status.ACCEPTED).build();
    }
}
