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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Path("shop")
public class ShopService {

    /**
     *
     * @param sort
     * @return all items of Shop sorted or unsorted
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listShops(@QueryParam("sort") String sort ) {
        List<Shop> shopList = DataHandler.readAllShops();
        List<Shop> cloned_shopList = new ArrayList<>(shopList);
        if (sort!=null && !sort.isEmpty()) {
            if(sort.equals("name")){
                cloned_shopList.sort(Comparator.comparing(Shop::getShopName));
            }else if(sort.equals("snowboards")){
                cloned_shopList.sort(Comparator.comparing(Shop::getSnowboardUUIDListLength));
            }
            return Response
                    .status(200)
                    .entity(cloned_shopList)
                    .build();
        }else {
            return Response
                    .status(200)
                    .entity(shopList)
                    .build();
        }
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

        Shop shop = DataHandler.readShopByUUID(shopUUID);

        if (shop==null) {
            return Response.status(404).entity(shop).build();
        }

        return Response
                .status(200)
                .entity(shop)
                .build();
    }
}


