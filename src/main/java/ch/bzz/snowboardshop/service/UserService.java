package ch.bzz.snowboardshop.service;

import ch.bzz.snowboardshop.data.UserData;
import ch.bzz.snowboardshop.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@Path("user")
public class UserService {

    @POST
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(
            @FormParam("username") String username,
            @FormParam("password") String password
    )
    {
        int httpStatus;

        User user = UserData.findUser(username, password);
        if (user == null || user.getRole() == null || user.getRole().equals("guest")){
            httpStatus = 404;
        }else{
            httpStatus = 200;
        }

        Response response = Response
                .status(httpStatus)
                .entity("")
                .build();
        return response;
    }


    @DELETE
    @Path("logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout()
    {
        Response response = Response
                .status(200)
                .entity("")
                .build();
        return response;
    }

}
