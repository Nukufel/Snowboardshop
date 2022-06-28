package ch.bzz.snowboardshop.service;

import ch.bzz.snowboardshop.data.AESEncrypt;
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

/**
 * Services for Snowboard
 *
 * @author Niklas Vogel (Nukufel)
 * @version 1.2
 * @since 20220613
 */
@Path("snowboard")
public class SnowboardService {

    /**
     * list all items sorted or unsorted
     *
     * @param sort string for sorting
     * @return all items of Snowboards sorted or unsorted
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listSnowboards(@QueryParam("sort") String sort, @CookieParam("userRole") String userRole) {
        int httpStatus = 200;
        userRole = AESEncrypt.decrypt(userRole);

        List<Snowboard> snowboardList;
        List<Snowboard> cloned_snowboardList = null;
        if (userRole == null || !userRole.equals("admin") && !userRole.equals("user")) {
            httpStatus = 403;
        } else {
            snowboardList = DataHandler.readAllSnowboards();
            cloned_snowboardList = new ArrayList<>(snowboardList);
            if (sort != null && !sort.isEmpty()) {
                if (sort.equals("hight")) {
                    cloned_snowboardList.sort(Comparator.comparing(Snowboard::getSnowboardHight));
                } else if (sort.equals("price")) {
                    cloned_snowboardList.sort(Comparator.comparing(Snowboard::getSnowboardPrice));
                }
            }
        }
        return Response
                .status(httpStatus)
                .entity(cloned_snowboardList)
                .build();
    }


    /**
     * reads an item by its UUID
     *
     * @param snowboardUUID ID of the snowboard object
     * @return object of snowboard
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response snowboard(
            @NotEmpty @Pattern(regexp = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}")
            @QueryParam("uuid") String snowboardUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        userRole = AESEncrypt.decrypt(userRole);

        Snowboard snowboard = null;

        if (userRole == null || !userRole.equals("admin") && !userRole.equals("user")) {
            httpStatus = 403;
        } else {

            snowboard = DataHandler.readSnowboardByUUID(snowboardUUID);
            if (snowboard == null) {
                httpStatus = 404;
            }

        }

        return Response
                .status(httpStatus)
                .entity(snowboard)
                .build();
    }


    /**
     * daletes a snowboard by its uuid
     *
     * @param snowboardUUID the snowboard that is going to be deleted
     * @return empty String
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteSnowboard(
            @NotEmpty @Pattern(regexp = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}")
            @QueryParam("uuid") String snowboardUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        userRole = AESEncrypt.decrypt(userRole);

        if (userRole == null || !userRole.equals("admin")) {
            httpStatus = 403;
        } else {
            if (!DataHandler.deleteSnowboard(snowboardUUID)) {
                httpStatus = 410;
            }
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();


    }

    /**
     * creates a snowboard
     *
     * @param snowboard beanparam: has all atributs of class Snowboard
     * @return empty string
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createSnowboard(
            @Valid @BeanParam Snowboard snowboard,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        userRole = AESEncrypt.decrypt(userRole);

        if (userRole == null || !userRole.equals("admin")) {
            httpStatus = 403;
        } else {
            snowboard.setSnowboardUUID(UUID.randomUUID().toString());
            DataHandler.insertSnowboard(snowboard);
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * updates a snowboard by it's uuid
     *
     * @param snowboard     beanparam: has all atributs of class Snowboard
     * @param snowboardUUID the snowboard that is going to be updated
     * @return empty string
     */
    @PUT
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSnowboard(
            @Valid @BeanParam Snowboard snowboard,
            @NotEmpty @Pattern(regexp = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}") @FormParam("snowboardUUID") String snowboardUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        userRole = AESEncrypt.decrypt(userRole);

        if (userRole == null || !userRole.equals("admin")) {
            httpStatus = 403;
        } else {

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
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();


    }
}



