package ch.bzz.snowboardshop.service;

import ch.bzz.snowboardshop.data.DataHandler;
import ch.bzz.snowboardshop.model.Shop;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("shop")
public class ShopService {

    /**
     * @return all itmes of Shop
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listShops() {
        List<Shop> shopList = DataHandler.getInstance().readAllShops();
        return Response
                .status(200)
                .entity(shopList)
                .build();
    }

    /**
     * reads an item by its UUID
     *
     * @return object of shop
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response shop(@QueryParam("uuid") String shopUUID) {
        if (shopUUID.isEmpty()) {
            return Response.status(400).build();
        }

        Shop shop = DataHandler.getInstance().readShopByUUID(shopUUID);

        if (shop==null) {
            return Response.status(404).entity(shop).build();
        }

        return Response
                .status(200)
                .entity(shop)
                .build();
    }


}


