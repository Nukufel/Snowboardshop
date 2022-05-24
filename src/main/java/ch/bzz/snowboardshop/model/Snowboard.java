package ch.bzz.snowboardshop.model;

public class Snowboard {
    private String snowboardUUID;
    private Double snowboardHight;
    private String snowboardArt;
    private Double snowboardPrice;
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
