package model;

import controller.AdminController;
import controller.LoginController;
import controller.UserHomeController;
import view.AdminForm;
import view.LoginForm;
import view.UserHome;

import javax.swing.*;
import java.sql.*;

public class LoginModel  extends JDialog {
    public LoginModel(){
    }

    public void loginUser(String username,String password){
        if(username.isEmpty()|| password.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please enter all Fields",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);

            LoginModel loginModel=new LoginModel();
            LoginForm loginForm=new LoginForm(loginModel);
            LoginController loginController=new LoginController(loginModel,loginForm);
            return;
        }

        user=getAuthenticatedUser(username,password);

        if(user!=null){
            dispose();
            if(user.username.equals("admin")&& user.password.equals("admin")){
                AdminModel model=new AdminModel();
                AdminForm view=new AdminForm(model);
                AdminController controller=new AdminController(model,view);

            }
            else {
                UserHomeModel model=new UserHomeModel();
                UserHome view=new UserHome(model,user.username);
                UserHomeController controller=new UserHomeController(model,view);
            }
        }
        else{
            JOptionPane.showMessageDialog(this,
                    "Email or Password Invalid",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            LoginModel loginModel=new LoginModel();
            LoginForm loginForm=new LoginForm(loginModel);
            LoginController loginController=new LoginController(loginModel,loginForm);
        }
    }
    public User user;

    public User getAuthenticatedUser(String username,String password){
        User user=null;

        final String DB_URL="jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt=conn.createStatement();
            String sql="SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                user=new User();
                user.name=resultSet.getString("name");
                user.username=resultSet.getString("username");
                user.phone=resultSet.getString("phone");
                user.password=resultSet.getString("password");
            }
            stmt.close();
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }

}
