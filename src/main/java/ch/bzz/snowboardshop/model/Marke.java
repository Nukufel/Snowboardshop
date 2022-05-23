package ch.bzz.snowboardshop.model;

public class Marke {
    private String markeUUID;
    private String markeName;

    /**
     * gets markeUUID
     *
     * @return value of markeUUID
     */
    public String getMarkeUUID() {return markeUUID; }

    /**
     * sets markeUUID
     *
     * @param markeUUID the value to set
     */
    public void setMarkeUUID(String markeUUID) {this.markeUUID = markeUUID;}

    /**
     * gets markeName
     *
     * @return value of markeName
     */
    public String getMarkeName() {return markeName;}

    /**
     * sets markeName
     *
     * @param markeName the value to set
     */
    public void setMarkeName(String markeName) {this.markeName = markeName;}
}
