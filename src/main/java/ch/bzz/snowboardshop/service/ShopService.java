package ch.bzz.snowboardshop.service;

import ch.bzz.snowboardshop.data.DataHandler;
import ch.bzz.snowboardshop.model.Marke;
import ch.bzz.snowboardshop.model.Shop;
import ch.bzz.snowboardshop.model.Snowboard;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
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




    /**
     * daletes a shop
     * @return  empty String
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteShop(@QueryParam("uuid") String shopUUID) {
        int httpStatus;
        if(shopUUID != null){
            Shop shop = DataHandler.readShopByUUID(shopUUID);
            if (!shopUUID.isEmpty()){
                DataHandler.deleteShop(shopUUID);
                DataHandler.updateShop();
                httpStatus = 200;
            }else{
                httpStatus = 404;
            }
        }else{
            httpStatus = 404;
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();


    }

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createShop(@FormParam("snowboardUUIDList")List<String> snowboardUUIDList,@FormParam("shopPLZ")String shopPLZ ,@FormParam("shopAdresse")String shopAdresse ,@FormParam("shopTel")String shopTel ,@FormParam("shopName")String shopName) {
        Shop shop = new Shop();
        shop.setShopAdresse(shopAdresse);
        shop.setShopName(shopName);
        shop.setShopPLZ(shopPLZ);
        shop.setShopTel(shopTel);
        shop.setSnowboardUUIDList(snowboardUUIDList);
        shop.setShopUUID(UUID.randomUUID().toString());
        DataHandler.insertShop(shop);

        return Response
                .status(200)
                .entity("")
                .build();
    }





    @PUT
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMarke(@FormParam("snowboardUUIDList")List<String> snowboardUUIDList ,@FormParam("shopUUID")String shopUUID,@FormParam("shopPLZ")String shopPLZ ,@FormParam("shopAdresse")String shopAdresse ,@FormParam("shopTel")String shopTel ,@FormParam("shopName")String shopName) {
        int httpStatus;
        if (shopUUID != null) {
            Shop shop = DataHandler.readShopByUUID(shopUUID);
            if (!shopUUID.isEmpty()) {
                shop.setShopUUID(shopUUID);
                shop.setShopTel(shopTel);
                shop.setShopPLZ(shopPLZ);
                shop.setShopAdresse(shopAdresse);
                shop.setShopName(shopName);
                shop.setSnowboardUUIDList(snowboardUUIDList);
                DataHandler.insertShop(shop);
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


