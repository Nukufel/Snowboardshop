package ch.bzz.snowboardshop.model;

public class Snowboard {
    private String snowboardUUID;
    private Double snowboardLänge;
    private String snowboardArt;
    private Double snowboardPreis;
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
    public Double getSnowboardLänge() {
        return snowboardLänge;
    }

    /**
     * sets snowboardLänge
     *
     * @param snowboardLänge the value to set
     */
    public void setSnowboardLänge(Double snowboardLänge) {
        this.snowboardLänge = snowboardLänge;
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
    public Double getSnowboardPreis() {
        return snowboardPreis;
    }

    /**
     * sets snowboardPreis
     *
     * @param snowboardPreis the value to set
     */
    public void setSnowboardPreis(Double snowboardPreis) {
        this.snowboardPreis = snowboardPreis;
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
