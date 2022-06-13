package ch.bzz.snowboardshop.model;

import javax.validation.constraints.*;
import javax.ws.rs.FormParam;

/**
 * Modelclass Snowboard
 * @author Niklas Vogel (Nukufel)
 * @version 1.2
 * @since 20220613
 */
public class Snowboard {
    private String snowboardUUID;

    @NotNull
    @DecimalMax(value = "300")
    @DecimalMin(value = "50")
    @FormParam("snowboardHight")
    private Double snowboardHight;

    @NotNull
    @Size(min=1, max=100)
    @FormParam("snowboardArt")
    private String snowboardArt;

    @NotNull
    @DecimalMax(value = "10000")
    @DecimalMin(value = "1")
    @FormParam("snowboardPrice")
    private Double snowboardPrice;

    @NotNull
    @Pattern(regexp = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}")
    @FormParam("snowboardMarke")
    private String snowboardMarke;

    /**
     * gets snowboardUUID
     *
     * @return value of snowboardUUID
     */
    public String getSnowboardUUID() {
        return snowboardUUID;
    }

    /**
     * sets snowboardUUID
     *
     * @param snowboardUUID the value to set
     */
    public void setSnowboardUUID(String snowboardUUID) {
        this.snowboardUUID = snowboardUUID;
    }

    /**
     * gets snowboardLänge
     *
     * @return value of snowboardLänge
     */
    public Double getSnowboardHight() {
        return snowboardHight;
    }

    /**
     * sets snowboardLänge
     *
     * @param snowboardHight the value to set
     */
    public void setSnowboardHight(Double snowboardHight) {
        this.snowboardHight = snowboardHight;
    }

    /**
     * gets snowboardArt
     *
     * @return value of snowboardArt
     */
    public String getSnowboardArt() {
        return snowboardArt;
    }

    /**
     * sets snowboardArt
     *
     * @param snowboardArt the value to set
     */
    public void setSnowboardArt(String snowboardArt) {
        this.snowboardArt = snowboardArt;
    }

    /**
     * gets snowboardPreis
     *
     * @return value of snowboardPreis
     */
    public Double getSnowboardPrice() {
        return snowboardPrice;
    }

    /**
     * sets snowboardPreis
     *
     * @param snowboardPrice the value to set
     */
    public void setSnowboardPrice(Double snowboardPrice) {
        this.snowboardPrice = snowboardPrice;
    }

    /**
     * gets snowboardMarke
     *
     * @return value of snowboardMarke
     */
    public String getSnowboardMarke() {
        return snowboardMarke;
    }

    /**
     * sets snowboardMarke
     *
     * @param snowboardMarke the value to set
     */
    public void setSnowboardMarke(String snowboardMarke) {
        this.snowboardMarke = snowboardMarke;
    }
}
