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
    private static DataHandler instance = null;
    private List<Shop> shopList;
    private List<Snowboard> snowboardList;


    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setSnowboardList(new ArrayList<>());
        readSnowboardJSON();
        setShopList(new ArrayList<>());
        readShopJSON();
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all shops
     * @return list of shops
     */
    public List<Shop> readAllShops() {
        return getShopList();
    }

    /**
     * reads a shop by its uuid
     * @param shopUUID
     * @return the Shop (null=not found)
     */
    public Shop readShopByUUID(String shopUUID) {
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
    public List<Snowboard> readAllSnowboards() {

        return getSnowboardList();
    }

    /**
     * reads a snowboard by its uuid
     * @param snowboardUUID
     * @return the Snowboard (null=not found)
     */
    public Snowboard readSnowboardByUUID(String snowboardUUID) {
        Snowboard snowboard = null;
        for (Snowboard entry : getSnowboardList()) {
            if (entry.getSnowboardUUID().equals(snowboardUUID)) {
                 snowboard = entry;
            }
        }
        return snowboard;
    }

    /**
     * reads the shops from the JSON-file
     */
    private void readShopJSON() {
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
    private void readSnowboardJSON() {
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
    /**
     * gets shopList
     *
     * @return value of shopList
     */
    private List<Shop> getShopList() {
        return shopList;
    }

    /**
     * sets shopList
     *
     * @param shopList the value to set
     */
    private void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }

    /**
     * gets snowboardList
     *
     * @return value of snowboardList
     */
    private List<Snowboard> getSnowboardList() {
        return snowboardList;
    }

    /**
     * sets snowboardList
     *
     * @param snowboardList the value to set
     */
    private void setSnowboardList(List<Snowboard> snowboardList) {
        this.snowboardList = snowboardList;
    }


}
