package model;

import javax.swing.*;
import java.sql.*;

public class UserHomeModel extends JDialog {
    public UserHomeModel(){

    }
    public void registerOrder(String username, String title){
        if(title.isEmpty()){
            JOptionPane.showMessageDialog(this,
                "Please enter all Fields",
                "Try Again",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        int available=check(username);
        if(available>0){
            order=addOrderToDatabase(username,title);
            deleteOneBook(username);
            if(order!=null){
                JOptionPane.showMessageDialog(this,
                        "Order Received",
                        "Thank you",
                        JOptionPane.PLAIN_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(this,
                        "Failed to register this order",
                        "Try again",
                        JOptionPane.ERROR_MESSAGE);
             }
        }
        else{
            JOptionPane.showMessageDialog(this,
                    "Book not available",
                    "Search for another book",
                    JOptionPane.ERROR_MESSAGE);
            }
    }

    public Order order;

    private void deleteOneBook(String title){
        final String DB_URL="jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt=conn.createStatement();
            String sql="UPDATE books SET quantity=quantity-1 WHERE title=?";
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,title);
            int addedRows=preparedStatement.executeUpdate();

            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private int check(String title){
        final String DB_URL = "jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        int quantity=0;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "SELECT quantity FROM books WHERE title=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, title);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                quantity = resultSet.getInt("quantity");
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quantity;
    }
    private Order addOrderToDatabase(String username,String title){
        Order order=null;
        final String DB_URL="jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

            Statement stmt=conn.createStatement();
            String sql="INSERT INTO orders(username,title)"+
                    "VALUES(?,?)";
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,username);

            int addedRows=preparedStatement.executeUpdate();
            if(addedRows>0){
                order=new Order();
                order.username=username;
                order.title=title;
            }
            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return order;
    }
}
