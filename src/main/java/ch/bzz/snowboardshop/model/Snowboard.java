package ch.bzz.snowboardshop.model;

public class Snowboard {
    private String snowboardUUID;
    private Double snowboardLänge;
    private String snowboardArt;
    private Double snowboardPreis;
    private Marke snowboardMarke;

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

    public Marke getSnowboardMarke() {
        return snowboardMarke;
    }

    public void setSnowboardMarke(Marke snowboardMarke) {
        this.snowboardMarke = snowboardMarke;
    }
}
