package ch.bzz.snowboardshop.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.util.ArrayList;
import java.util.List;

public class Shop {
    private String shopUUID;

    @Size(min=1, max=100)
    @FormParam("shopName")
    private String shopName;


    @Pattern(regexp = "0(2[1-246-7]|3[1-4]|4[13-4]|5[25-6]|6[1-2]|7[15-68-9]|8[17]|91)[0-9]{7}")
    @FormParam("shopTel")
    private String shopTel;


    @Size(min=1, max=100)
    @FormParam("shopAdresse")
    private String shopAdresse;

    @Size(min=1, max=15)
    @FormParam("shopPLZ")
    private String shopPLZ;

    //@Pattern(regexp="[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}")
    @FormParam("snowboardUUIDList")
    private List<String> snowboardUUIDList = new ArrayList<>();

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

