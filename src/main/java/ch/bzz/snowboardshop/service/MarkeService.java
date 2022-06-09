package ch.bzz.snowboardshop.service;

import ch.bzz.snowboardshop.data.DataHandler;
import ch.bzz.snowboardshop.model.Marke;
import ch.bzz.snowboardshop.model.Snowboard;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
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
        List<Marke> markeList = DataHandler.readAllMarke();
        List<Marke> cloned_markeList = markeList.stream().collect(Collectors.toList());
        if (sort!=null && !sort.isEmpty()) {
            if(sort.equals("name")){
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
    public Response marke(@NotEmpty @QueryParam("uuid") String markeUUID) {
        if (markeUUID.isEmpty()) {
            return Response.status(400).build();
        }

        Marke marke = DataHandler.readMarkeByUUID(markeUUID);

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
        List<Marke> markeList = DataHandler.readAllMarke();
        List<Marke> cloned_markeList = markeList.stream().collect(Collectors.toList());
        cloned_markeList.sort(Comparator.comparing(Marke::getMarkeName));
        return Response
                .status(200)
                .entity(cloned_markeList)
                .build();
    }

    /**
     * daletes a snowboard by its uuid
     * @return  empty String
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteMarke(@NotEmpty @Pattern(regexp = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}") @QueryParam("uuid") String markeUUID) {
        int httpStatus = 200;
        if (!DataHandler.deleteMarke(markeUUID)){
            httpStatus = 410;
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();


    }

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createMarke(@Valid @BeanParam Marke marke) {

        marke.setMarkeUUID(UUID.randomUUID().toString());

        DataHandler.insertMarke(marke);

        return Response
                .status(200)
                .entity("")
                .build();
    }





    @PUT
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMarke(@Valid @BeanParam Marke marke,
                                @NotEmpty @Pattern(regexp = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}") @FormParam("markeUUID") String markeUUID) {
        int httpStatus;
        if (markeUUID != null) {
            Marke oldMarke = DataHandler.readMarkeByUUID(markeUUID);
            if (!markeUUID.isEmpty()) {
                oldMarke.setMarkeName(marke.getMarkeName());

                DataHandler.updateMarke();

                httpStatus = 200;
            } else {
                httpStatus = 404;
            }
        } else {
            httpStatus = 404;
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();


    }


}
