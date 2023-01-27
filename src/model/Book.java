package model;

public class Book {
    public String title;
    public String author;
    public int price;
    public int quantity;

    public Book() {
    }

    //getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    //setters

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void duplicateStock(){

    }
    public int findPrice(int quantity){
        return 0;
    }
}
