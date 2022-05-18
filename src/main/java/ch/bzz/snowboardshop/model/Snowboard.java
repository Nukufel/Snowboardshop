package ch.bzz.snowboardshop.model;

public class Snowboard {
    private String snowboardUUID;
    private Double snowboardLänge;
    private String snowboardArt;
    private Double snowboardPreis;
    private String snowboardMarke;

    public String getSnowboardUUID() {
        return snowboardUUID;
    }

    public void setSnowboardUUID(String snowboardUUID) {
        this.snowboardUUID = snowboardUUID;
    }

    public Double getSnowboardLänge() {
        return snowboardLänge;
    }

    public void setSnowboardLänge(Double snowboardLänge) {
        this.snowboardLänge = snowboardLänge;
    }

    public String getSnowboardArt() {
        return snowboardArt;
    }

    public void setSnowboardArt(String snowboardArt) {
        this.snowboardArt = snowboardArt;
    }

    public Double getSnowboardPreis() {
        return snowboardPreis;
    }

    public void setSnowboardPreis(Double snowboardPreis) {
        this.snowboardPreis = snowboardPreis;
    }

    public String getSnowboardMarke() {
        return snowboardMarke;
    }

    public void setSnowboardMarke(String snowboardMarke) {
        this.snowboardMarke = snowboardMarke;
    }
}
