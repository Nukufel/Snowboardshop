package ch.bzz.snowboardshop.service;

import ch.bzz.snowboardshop.data.AESEncrypt;
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


/**
 * Services for Marke
 *
 * @author Niklas Vogel (Nukufel)
 * @version 1.2
 * @since 20220613
 */
@Path("marke")
public class MarkeService {

    /**
     * lsits all objects sorted or unsorted
     *
     * @param sort string for sorting
     * @return all items of Marke sorted or unsorted
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listMarke(
            @QueryParam("sort") String sort,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        userRole = AESEncrypt.decrypt(userRole);

        List<Marke> markeList = null;
        List<Marke> cloned_markeList = null;

        if (userRole == null || !userRole.equals("admin") || !userRole.equals("user")) {
            httpStatus = 403;
        } else {
            markeList = DataHandler.readAllMarke();
            cloned_markeList = markeList.stream().collect(Collectors.toList());
            if (sort != null && !sort.isEmpty()) {
                if (sort.equals("name")) {
                    cloned_markeList.sort(Comparator.comparing(Marke::getMarkeName));
                }
            } else {
                httpStatus = 404;
            }
        }
        return Response
                .status(httpStatus)
                .entity(cloned_markeList)
                .build();
    }

    /**
     * reads an item by its UUID
     *
     * @param markeUUID ID of the object
     * @return object of marke
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response marke(
            @NotEmpty @Pattern(regexp = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}")
            @QueryParam("uuid") String markeUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        Marke marke = null;
        userRole = AESEncrypt.decrypt(userRole);

        if (userRole == null || !userRole.equals("admin") || !userRole.equals("user")) {
            httpStatus = 403;
        } else {
            if (markeUUID.isEmpty()) {
                httpStatus = 400;
            }
            marke = DataHandler.readMarkeByUUID(markeUUID);
            if (marke == null) {
                httpStatus = 404;
            }
        }
        return Response
                .status(httpStatus)
                .entity(marke)
                .build();
    }

    /**
     * daletes a Brand by its uuid
     *
     * @param markeUUID ID of the brand that is going to be deleted
     * @return empty string
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteMarke(
            @Pattern(regexp = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}")
            @QueryParam("uuid") String markeUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        userRole = AESEncrypt.decrypt(userRole);

        if (userRole == null || !userRole.equals("admin")) {
            httpStatus = 403;
        } else {
            if (!DataHandler.deleteMarke(markeUUID)) {
                httpStatus = 410;
            }
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();


    }

    /**
     * creats a new Brand
     *
     * @param marke beanparam: has all atributs of class brand
     * @return emty string
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createMarke(
            @Valid @BeanParam Marke marke,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        userRole = AESEncrypt.decrypt(userRole);

        if (userRole == null || !userRole.equals("admin")) {
            httpStatus = 403;
        } else {
            marke.setMarkeUUID(UUID.randomUUID().toString());
            DataHandler.insertMarke(marke);
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }


    /**
     * @param marke     beanparam: has all atributs of class brand
     * @param markeUUID the brand that is going to be updated
     * @return
     */
    @PUT
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMarke(
            @Valid @BeanParam Marke marke,
            @NotEmpty @Pattern(regexp = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}")
            @FormParam("markeUUID") String markeUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        userRole = AESEncrypt.decrypt(userRole);

        Marke oldMarke = null;
        if (userRole == null || !userRole.equals("admin")) {
            httpStatus = 403;
        } else {
            if (markeUUID != null) {
                oldMarke = DataHandler.readMarkeByUUID(markeUUID);
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
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}
