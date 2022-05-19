package ch.bzz.snowboardshop.service;

import ch.bzz.snowboardshop.data.DataHandler;
import ch.bzz.snowboardshop.model.Shop;
import ch.bzz.snowboardshop.model.Snowboard;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("snowboard")
public class SnowboardService {

    /**
     * confirms the application runs
     * @return  message
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listSnowboards() {
        List<Snowboard> snowboardList = DataHandler.getInstance().readAllSnowboards();
        return Response
                .status(200)
                .entity(snowboardList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response snowboard(@QueryParam("uuid") String snowboardUUID) {
        if (snowboardUUID.isEmpty()) {
            return Response.status(400).build();
        }

        Snowboard snowboard = DataHandler.getInstance().readSnowboardByUUID(snowboardUUID);

        if (snowboard==null) {
            return Response.status(404).entity(snowboard).build();
        }

        return Response
                .status(200)
                .entity(snowboard)
                .build();
    }


}



