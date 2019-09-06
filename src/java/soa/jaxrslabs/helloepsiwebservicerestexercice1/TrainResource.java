package soa.jaxrslabs.helloepsiwebservicerestexercice1;

import java.util.ArrayList;
import static java.util.Collections.list;
import soa.jaxrslabs.helloepsiwebservicerestexercice1.Train;
import soa.jaxrslabs.helloepsiwebservicerestexercice1.BookTrainBD;
import java.util.List;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/trains")
@Produces("application/xml")
public class TrainResource {

    public TrainResource() {
    }

    @GET // Méthode HTTP utilisée pour déclencher cette méthode
    public Response getTrains() {
        System.out.println("getTrains");

        List<Train> list = BookTrainBD.getTrains();
        GenericEntity<List<Train>> entity = new GenericEntity<List<Train>>(list) {
        };
        Response response = Response.ok(entity).build();
        System.out.println(list);
        return response;

        /* return Response
                .status(Status.OK)
                .entity(BookTrainBD.getTrains())
                .build();*/
    }

    @GET // Méthode HTTP utilisée pour déclencher cette méthode
    @Path("numTrain-{id}") // Chemin suivant ce template numTrain-TR123 où TR123 est une chaine variable
    public Response getTrain(@PathParam("id") String numTrain) {
        System.out.println("getTrain >");

        for (Train current : BookTrainBD.getTrains()) {
            if (numTrain.equals(current.getNumTrain())) {
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

    @GET // Méthode HTTP utilisée pour déclencher cette méthode
    @Path("/search") // Chemin suivant /trains/search pour invoquer cette méthode 
    public Response searchTrainsByCriteria(@DefaultValue("noParam") @QueryParam("departure") String departure
   
    ) {
        System.out.println("searchTrainsByCriteria >>>>>>>>>>>>>>>>> ");

        List<Train> listTrain = new ArrayList<Train>();
        GenericEntity<List<Train>> entityTrain = new GenericEntity<List<Train>>(listTrain) {
        };

        if (departure.equals("noParam")) {
            System.out.println(" je renvoie tous les trains : " + listTrain.toString());
            //listTrain = BookTrainBD.getTrains();
            for (Train current : BookTrainBD.getTrains()) {
                listTrain.add(current);
            }

        } else {
            for (Train current : BookTrainBD.getTrains()) {

                if (departure.equals(current.getVilleDepart())) {
                    listTrain.add(current);
                    System.out.println(current.getVilleDepart() + ">> + " + current.toString());
                }

            }
        }

        System.out.println(listTrain);

        return Response
                .status(Status.OK)
                .entity(entityTrain)
                .build();
        /*
        return Response
                .status(Status.OK)
                .entity(BookTrainBD.getTrains().subList(0, 2))
                .build();*/
    }
}
