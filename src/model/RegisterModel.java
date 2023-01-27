package model;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class RegisterModel extends JDialog {
    private int m_total;
    public RegisterModel(){

    }

    public void reset(){
        m_total=0;
    }

    public void registerUser(String name, String username, String phone, String password, String confirmPassword){
        if(name.isEmpty()|| username.isEmpty() || phone.isEmpty() || password.isEmpty() ||confirmPassword.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please enter all Fields",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Passwords do not match",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean ok = phone.matches("[0-9]+");
        if(ok==false){
            JOptionPane.showMessageDialog(this,
                    "Invalid Phone Number",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        ok=name.matches("[a-zA-Z]+");
        if(ok==false){
            JOptionPane.showMessageDialog(this,
                    "Invalid Name",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        user=addUserToDatabase(name,username,phone,password);
        if(user!=null){
            JOptionPane.showMessageDialog(this,
                    "New account was recreated",
                    "Thank you",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(this,
                    "Failed to register new user",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("Registration Canceled");
        }
    }

    public User user;
    private User addUserToDatabase(String name,String username,String phone, String password) {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO users(name,username,phone,password)" +
                    "VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, password);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.name = name;
                user.username = username;
                user.phone = phone;
                user.password = password;
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
