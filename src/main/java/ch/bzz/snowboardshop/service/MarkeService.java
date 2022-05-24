package ch.bzz.snowboardshop.service;

import ch.bzz.snowboardshop.data.DataHandler;
import ch.bzz.snowboardshop.model.Marke;
import ch.bzz.snowboardshop.model.Snowboard;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Path("marke")
public class MarkeService {

    /**
     *
     * @param sort
     * @return all items of Marke sorted or unsorted
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listMarke(@QueryParam("sort") String sort) {
        List<Marke> markeList = DataHandler.getInstance().readAllMarke();
        List<Marke> cloned_markeList = markeList.stream().collect(Collectors.toList());
        if (sort!=null && !sort.isEmpty()) {
            if(sort == "hight"){
                cloned_markeList.sort(Comparator.comparing(Marke::getMarkeName));
            }
            return Response
                    .status(200)
                    .entity(cloned_markeList)
                    .build();
        }else {
            return Response
                    .status(200)
                    .entity(markeList)
                    .build();
        }
    }

    /**
     * reads an item by its UUID
     *
     * @return object of marke
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response marke(@QueryParam("uuid") String markeUUID) {
        if (markeUUID.isEmpty()) {
            return Response.status(400).build();
        }

        Marke marke = DataHandler.getInstance().readMarkeByUUID(markeUUID);

        if (marke == null) {
            return Response.status(404).entity(marke).build();
        }

        return Response
                .status(200)
                .entity(marke)
                .build();
    }

    /**
     * @return all itmes of Marke sorted by alphabet
     */
    @GET
    @Path("listsortname")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sortListMarke() {
        List<Marke> markeList = DataHandler.getInstance().readAllMarke();
        List<Marke> cloned_markeList = markeList.stream().collect(Collectors.toList());
        cloned_markeList.sort(Comparator.comparing(Marke::getMarkeName));
        return Response
                .status(200)
                .entity(cloned_markeList)
                .build();
    }

}
