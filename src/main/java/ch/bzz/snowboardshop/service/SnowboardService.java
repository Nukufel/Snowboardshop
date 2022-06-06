package ch.bzz.snowboardshop.service;

import ch.bzz.snowboardshop.data.DataHandler;
import ch.bzz.snowboardshop.model.Snowboard;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Path("snowboard")
public class SnowboardService {

    /**
     * @param sort
     * @return all items of Snowboards sorted or unsorted
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listSnowboards(@QueryParam("sort") String sort) {
        List<Snowboard> snowboardList = DataHandler.readAllSnowboards();
        List<Snowboard> cloned_snowboardList = new ArrayList<>(snowboardList);
        if (sort != null && !sort.isEmpty()) {
            if (sort.equals("hight")) {
                cloned_snowboardList.sort(Comparator.comparing(Snowboard::getSnowboardHight));
            } else if (sort.equals("price")) {
                cloned_snowboardList.sort(Comparator.comparing(Snowboard::getSnowboardPrice));
            }
            return Response
                    .status(200)
                    .entity(cloned_snowboardList)
                    .build();
        } else {
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
    public Response snowboard(@NotEmpty @Pattern(regexp = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}") @QueryParam("uuid") String snowboardUUID) {
        if (snowboardUUID.isEmpty()) {
            return Response.status(400).build();
        }

        Snowboard snowboard = DataHandler.readSnowboardByUUID(snowboardUUID);

        if (snowboard == null) {
            return Response.status(404).entity(snowboard).build();
        }

        return Response
                .status(200)
                .entity(snowboard)
                .build();
    }


    /**
     * daletes a snowboard by its uuid
     *
     * @return empty String
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteSnowboard(@NotEmpty @Pattern(regexp = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}") @QueryParam("uuid") String snowboardUUID) {
        int httpStatus = 200;
        if (!DataHandler.deleteSnowboard(snowboardUUID)) {
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
    public Response createSnowboard(
            @Valid @BeanParam Snowboard snowboard
    ) {
        snowboard.setSnowboardUUID(UUID.randomUUID().toString());

        DataHandler.insertSnowboard(snowboard);

        return Response
                .status(200)
                .entity("")
                .build();
    }


    @PUT
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSnowboard(
            @Valid @BeanParam Snowboard snowboard,
            @NotEmpty @Pattern(regexp = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}") @FormParam("snowboardUUID") String snowboardUUID) {
        int httpStatus = 200;

        Snowboard oldSnowboard = DataHandler.readSnowboardByUUID(snowboardUUID);

        if (oldSnowboard != null) {
            oldSnowboard.setSnowboardHight(snowboard.getSnowboardHight());
            oldSnowboard.setSnowboardArt(snowboard.getSnowboardArt());
            oldSnowboard.setSnowboardPrice(snowboard.getSnowboardPrice());
            oldSnowboard.setSnowboardMarke(snowboard.getSnowboardMarke());

            DataHandler.updateSnowboard();
        } else {
            httpStatus = 410;
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();


    }
}



