package ch.bzz.snowboardshop.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

/**
 * Modelclass Marke
 * @author Niklas Vogel (Nukufel)
 * @version 1.2
 * @since 20220613
 */
public class Marke {
    private String markeUUID;

    @NotNull
    @Size(min=1, max=100)
    @FormParam("markeName")
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
