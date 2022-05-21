package ch.bzz.snowboardshop.service;

import ch.bzz.snowboardshop.data.DataHandler;
import ch.bzz.snowboardshop.model.Marke;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("marke")
public class MarkeService {
    /**
     * confirms the application runs
     * @return  message
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listMarke() {
        List<Marke> markeList = DataHandler.getInstance().readAllMarke();
        return Response
                .status(200)
                .entity(markeList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response marke(@QueryParam("uuid") String markeUUID) {
        if (markeUUID.isEmpty()) {
            return Response.status(400).build();
        }

        Marke marke = DataHandler.getInstance().readMarkeByUUID(markeUUID);

        if (marke==null) {
            return Response.status(404).entity(marke).build();
        }

        return Response
                .status(200)
                .entity(marke)
                .build();
    }

}
