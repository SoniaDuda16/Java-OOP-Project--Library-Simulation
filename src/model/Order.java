package model;

public class Order {
    public String username;
    public String title;

    public Order() {
    }

    public Order(String username, String title) {
        this.username = username;
        this.title = title;
    }

    //getters

    public String getUsername() {
        return username;
    }

    public String getTitle() {
        return title;
    }

    //setters

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
