package ch.bzz.snowboardshop.service;

import ch.bzz.snowboardshop.data.AESEncrypt;
import ch.bzz.snowboardshop.data.UserData;
import ch.bzz.snowboardshop.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.awt.*;


/**
 * logs in a person by its name and password
 */
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
        int httpStatus = 200;
        NewCookie cookie = null;
        User user = UserData.findUser(username, password);
        if (user == null || user.getRole() == null || user.getRole().equals("guest")){
            httpStatus = 404;
        }else{
            httpStatus = 200;
             cookie = new NewCookie(
                    "userRole",
                     AESEncrypt.encrypt(user.getRole()),
                    "/",
                    "",
                    "Login-Cookie",
                    600,
                    false

            );
        }

        Response response = Response
                .status(httpStatus)
                .entity("")
                .cookie(cookie)
                .build();
        return response;
    }


    /**
     * logs out currently loged in person
     * @return String empty
     */
    @DELETE
    @Path("logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout()
    {
        NewCookie cookie = new NewCookie(
            "userRole",
            "guest",
            "/",
            "",
            "Login-Cookie",
            1,
            false
        );
        Response response = Response
                .status(200)
                .entity("")
                .cookie(cookie)
                .build();
        return response;
    }

}
