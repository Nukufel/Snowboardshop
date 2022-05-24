package ch.bzz.snowboardshop.model;

import java.util.List;

public class Shop {
    private String shopUUID;
    private String shopName;
    private String shopTel;
    private String shopAdresse;
    private String shopPLZ;
    private List<String> snowboardUUIDList;

    /**
     * gets snowboardUUID
     *
     * @return value of snowboardUUID
     */
    public String getShopUUID() {
        return shopUUID;
    }

    public void setShopUUID(String shopUUID) {
        this.shopUUID = shopUUID;
    }

    /**
     * gets shopName
     *
     * @return value of shopName
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * sets shopName
     *
     * @param shopName the value to set
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * gets shopTel
     *
     * @return value of shopTel
     */
    public String getShopTel() {
        return shopTel;
    }

    /**
     * sets shopTel
     *
     * @param shopTel the value to set
     */
    public void setShopTel(String shopTel) {
        this.shopTel = shopTel;
    }

    /**
     * gets shopAdresse
     *
     * @return value of shopAdresse
     */
    public String getShopAdresse() {
        return shopAdresse;
    }

    /**
     * sets shopAdresse
     *
     * @param shopAdresse the value to set
     */
    public void setShopAdresse(String shopAdresse) {
        this.shopAdresse = shopAdresse;
    }

    /**
     * gets shopPLZ
     *
     * @return value of shopPLZ
     */
    public String getShopPLZ() {
        return shopPLZ;
    }

    /**
     * sets shopPLZ
     *
     * @param shopPLZ the value to set
     */
    public void setShopPLZ(String shopPLZ) {
        this.shopPLZ = shopPLZ;
    }

    /**
     * gets snowboardUUIDList
     *
     * @return value of snowboardUUIDList
     */
    public List<String> getSnowboardUUIDList() {return snowboardUUIDList;}

    /**
     * sets snowboardUUIDList
     *
     * @param snowboardUUIDList the value to set
     */
    public void setSnowboardUUIDList(List<String> snowboardUUIDList) {this.snowboardUUIDList = snowboardUUIDList; }

    public int getSnowboardUUIDListLength(){return snowboardUUIDList.size();}
}

