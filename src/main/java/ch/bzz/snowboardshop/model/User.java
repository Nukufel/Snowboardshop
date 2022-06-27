package ch.bzz.snowboardshop.model;

public class User {
    private String userUUID;
    private String username;
    private String password;
    private String role;

    /**
     * Constructor
     * sets  role of user default to guest
     */
    public User() {
        setRole("guest");
    }

    /**
     * gets userUUID
     * @return String userUUID
     */
    public String getUserUUID() {
        return userUUID;
    }

    /**
     * set userUUID
     * @param userUUID
     */
    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    /**
     * gets username
     * @return String usernam
     */
    public String getUsername() {
        return username;
    }

    /**
     * sets username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * gets password
     * @return String password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * gets role
     * @return String role
     */
    public String getRole() {
        return role;
    }

    /**
     * sets role
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }
}
