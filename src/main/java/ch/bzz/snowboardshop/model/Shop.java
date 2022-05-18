package ch.bzz.snowboardshop.model;

import java.util.List;

public class Shop {
    private String shopUUID;
    private String shopName;
    private String shopTel;
    private String shopAdresse;
    private String shopPLZ;
    private List<String> snowboardUUIDList;

    public String getShopUUID() {
        return shopUUID;
    }

    public void setShopUUID(String shopUUID) {
        this.shopUUID = shopUUID;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopTel() {
        return shopTel;
    }

    public void setShopTel(String shopTel) {
        this.shopTel = shopTel;
    }

    public String getShopAdresse() {
        return shopAdresse;
    }

    public void setShopAdresse(String shopAdresse) {
        this.shopAdresse = shopAdresse;
    }

    public String getShopPLZ() {
        return shopPLZ;
    }

    public void setShopPLZ(String shopPLZ) {
        this.shopPLZ = shopPLZ;
    }

    public List<String> getSnowboardUUIDList() {
        return snowboardUUIDList;
    }

    public void setSnowboardUUIDList(List<String> snowboardUUIDList) {
        this.snowboardUUIDList = snowboardUUIDList;
    }
}
