package ch.bzz.snowboardshop.service;

import ch.bzz.snowboardshop.data.DataHandler;
import ch.bzz.snowboardshop.model.Snowboard;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Path("snowboard")
public class SnowboardService {

    /**
     *
     * @param sort
     * @return all items of Snowboards sorted or unsorted
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listSnowboards(@QueryParam("sort") String sort) {
        List<Snowboard> snowboardList = DataHandler.getInstance().readAllSnowboards();
        List<Snowboard> cloned_snowboardList = snowboardList.stream().collect(Collectors.toList());
        if (sort!=null && !sort.isEmpty()) {
            if(sort.equals("hight")){
                cloned_snowboardList.sort(Comparator.comparing(Snowboard::getSnowboardHight));
            }else if(sort.equals("price")){
                cloned_snowboardList.sort(Comparator.comparing(Snowboard::getSnowboardPrice));
            }
            return Response
                    .status(200)
                    .entity(cloned_snowboardList)
                    .build();
            }else {
            return Response
                    .status(200)
                    .entity(snowboardList)
                    .build();
            }

    }

    /**
     * reads an item by its UUID
     *
     * @return object of snowboard
     */
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



