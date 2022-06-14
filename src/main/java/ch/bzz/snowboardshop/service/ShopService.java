package ch.bzz.snowboardshop.service;

import ch.bzz.snowboardshop.annotation.UniqueListUUID;
import ch.bzz.snowboardshop.data.DataHandler;
import ch.bzz.snowboardshop.model.Marke;
import ch.bzz.snowboardshop.model.Shop;
import ch.bzz.snowboardshop.model.Snowboard;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
 * Services for Shop
 * @author Niklas Vogel (Nukufel)
 * @version 1.2
 * @since 20220613
 */
@Path("shop")
public class ShopService {

    /**
     *sorts the all shops after sort param
     * @param sort string for sorting
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
     * @param shopUUID ID of the object
     * @return object of shop
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response shop(@NotEmpty @Pattern(regexp = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}") @QueryParam("uuid") String shopUUID) {
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
     * daletes a shop by its uuid
     * @return  empty String
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteShop(@NotEmpty @Pattern(regexp = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}")  @QueryParam("uuid") String shopUUID) {
        int httpStatus = 200;
        if (!DataHandler.deleteShop(shopUUID)){
            httpStatus = 410;
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();


    }

    /**
     * creats a new shop
     * @param shop beanparam: has all atributs of class shop
     * @return empty string
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createShop(
            @Valid @BeanParam Shop shop,

            @UniqueListUUID
            @NotNull
            @FormParam("snowboardUUIDList") List< @Pattern(regexp = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}") String> snowboardUUIDList

    ) {
        shop.setSnowboardUUIDList(snowboardUUIDList);
        shop.setShopUUID(UUID.randomUUID().toString());

        DataHandler.insertShop(shop);

        return Response
                .status(200)
                .entity("")
                .build();
    }


    /**
     * updates a shop
     * @param shop beanparam: has all atributs of class shop
     * @param shopUUID the shop that is going to be updated
     * @param snowboardUUIDList not in beanparam for validation
     * @return empty string
     */

    @PUT
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateShop(
            @Valid @BeanParam Shop shop,
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}")
            @FormParam("shopUUID") String shopUUID,

            @UniqueListUUID
            @NotNull
            @FormParam("snowboardUUIDList") List< @Pattern(regexp = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}") String> snowboardUUIDList

    ) {
        int httpStatus;
        Shop oldShop = DataHandler.readShopByUUID(shopUUID);
        if (oldShop!=null) {
            oldShop.setShopTel(shop.getShopTel());
            oldShop.setShopPLZ(shop.getShopPLZ());
            oldShop.setShopAdresse(shop.getShopAdresse());
            oldShop.setShopName(shop.getShopName());
            oldShop.setSnowboardUUIDList(snowboardUUIDList);
            DataHandler.updateShop();
            httpStatus = 200;
        } else {
            httpStatus = 404;
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();


    }


}


