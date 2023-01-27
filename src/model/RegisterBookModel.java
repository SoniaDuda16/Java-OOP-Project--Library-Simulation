package model;
import view.RegisterBookForm;
import controller.RegisterBookController;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class RegisterBookModel  extends JDialog {

    public RegisterBookModel(){
    }

    public void registerBook(String title, String author, String genre, int price, int quantity){
        if(title.isEmpty()|| author.isEmpty()  || genre.isEmpty() || price==0 ||quantity==0){
            JOptionPane.showMessageDialog(this,
                    "Please enter all Fields",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean result=author.matches("[a-zA-Z]+");
        if(result==false){
            JOptionPane.showMessageDialog(this,
                    "Invalid Author",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        book=addBookToDatabase(title,author,genre,price,quantity);

        if(book!=null){
            JOptionPane.showMessageDialog(this,
                    "Book was registered",
                    "Thank you",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(this,
                    "Failed to register new book",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    public Book book;

    private Book addBookToDatabase(String title,String author,String genre, int price,int quantity){
        Book book=null;
        final String DB_URL="jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

            Statement stmt=conn.createStatement();
            String sql="INSERT INTO books(title,author,genre,price,quantity)"+
                    "VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,author);
            preparedStatement.setString(3,genre);
            preparedStatement.setInt(4,price);
            preparedStatement.setInt(5,quantity);

            int addedRows=preparedStatement.executeUpdate();
            if(addedRows>0){
                book=new Book();
                book.title=title;
                book.author=author;
                book.quantity=quantity;
                book.price=price;
            }
            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return book;
    }
}
