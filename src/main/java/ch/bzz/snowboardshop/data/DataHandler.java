package ch.bzz.snowboardshop.data;


import ch.bzz.snowboardshop.model.Shop;
import ch.bzz.snowboardshop.model.Snowboard;
import ch.bzz.snowboardshop.model.Marke;
import ch.bzz.snowboardshop.service.Config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static List<Shop> shopList;
    private static List<Snowboard> snowboardList;
    private static List<Marke> markeList;


    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
    }




    /**
     * reads all shops
     * @return list of shops
     */
    public static List<Shop> readAllShops() {
        return getShopList();
    }

    /**
     * reads a shop by its uuid
     * @param shopUUID
     * @return the Shop (null=not found)
     */
    public static Shop readShopByUUID(String shopUUID) {
        Shop shop = null;
        for (Shop entry : getShopList()) {
            if (entry.getShopUUID().equals(shopUUID)) {
                shop = entry;
            }
        }
        return shop;
    }

    /**
     * reads all Snowboards
     * @return list of snowboards
     */
    public static List<Snowboard> readAllSnowboards() {

        return getSnowboardList();
    }

    /**
     * reads a snowboard by its uuid
     * @param snowboardUUID
     * @return the Snowboard (null=not found)
     */
    public static Snowboard readSnowboardByUUID(String snowboardUUID) {
        Snowboard snowboard = null;
        for (Snowboard entry : getSnowboardList()) {
            if (entry.getSnowboardUUID().equals(snowboardUUID)) {
                 snowboard = entry;
            }
        }
        return snowboard;
    }

    /**
     * reads all Marken
     * @return list of makren
     */
    public static List<Marke> readAllMarke() {
        return getMarkeList();
    }

    /**
     * reads a marke by its uuid
     * @param markeUUID
     * @return the Marke (null=not found)
     */
    public static Marke readMarkeByUUID(String markeUUID) {
        Marke marke = null;
        for (Marke entry : getMarkeList()) {
            if (entry.getMarkeUUID().equals(markeUUID)) {
                marke = entry;
            }
        }
        return marke;
    }

    /**
     * reads the shops from the JSON-file
     */
    private static void readShopJSON() {
        try {
            String path = Config.getProperty("shopJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Shop[] shops = objectMapper.readValue(jsonData, Shop[].class);
            for (Shop shop : shops) {
                getShopList().add(shop);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the publishers from the JSON-file
     */
    private static void readSnowboardJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("snowboardJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Snowboard[] snowboards = objectMapper.readValue(jsonData, Snowboard[].class);
            for (Snowboard snowboard : snowboards) {
                getSnowboardList().add(snowboard);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void readMarkeJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("markeJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Marke[] marken = objectMapper.readValue(jsonData, Marke[].class);
            for (Marke marke : marken) {
                getMarkeList().add(marke);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets shopList
     *
     * @return value of shopList
     */
    private static List<Shop> getShopList() {
        return shopList;
    }

    /**
     * sets shopList
     *
     * @param shopList the value to set
     */
    private static void setShopList(List<Shop> shopList) {
        DataHandler.shopList = shopList;
    }

    /**
     * gets snowboardList
     *
     * @return value of snowboardList
     */
    private static List<Snowboard> getSnowboardList() {
        return snowboardList;
    }

    /**
     * sets snowboardList
     *
     * @param snowboardList the value to set
     */
    private static void setSnowboardList(List<Snowboard> snowboardList) {
        DataHandler.snowboardList = snowboardList;
    }

    /**
     * gets markeList
     *
     * @return value of snowboardList
     */
    public static List<Marke> getMarkeList() {return markeList; }

    /**
     * sets markeList
     *
     * @param markeList the value to set
     */
    public static void setMarkeList(List<Marke> markeList) {DataHandler.markeList = markeList; }

}
