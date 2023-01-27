package view;

import controller.UserOrdersController;
import model.StartModel;
import model.UserHomeModel;
import model.UserOrdersModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UserHome extends JDialog {
    private JPanel userHomePanel;
    private JButton btnLogout;
    private JComboBox cmbBook;
    private JButton btnOrder;
    private JButton btnMyOrders;
    private JLabel lblUser;
    private UserHomeModel m_model;
    public String username;

    public UserHome(UserHomeModel model,String username){
        m_model=model;
        this.username=username;
        this.lblUser.setText(username);
        JPanel content=new JPanel();
        content=userHomePanel;

        final String DB_URL="jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt=conn.createStatement();
            String sql="SELECT title FROM books";
            PreparedStatement preparedStatement=conn.prepareStatement(sql);

            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                cmbBook.addItem(resultSet.getString("title"));
            }
            stmt.close();
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        this.setContentPane(content);
        this.pack();

        this.setTitle("User Home");

        setMinimumSize(new Dimension(600,500));
        setLocationRelativeTo(btnLogout);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);

        setVisible(true);

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StartModel model=new StartModel();
                StartForm startForm=new StartForm(null,model);
                startForm.setVisible(true);
            }
        });

        btnMyOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                UserOrdersModel model=new UserOrdersModel();
                UserOrdersForm view=new UserOrdersForm(model,username);
                UserOrdersController controller=new UserOrdersController(model,view);
            }
        });
    }

    public String getTitle(){
        return cmbBook.getSelectedItem().toString();
    }
    public String getUsername(){
        return this.lblUser.getText();
    }

    public void showError(String errorMessage){
        JOptionPane.showMessageDialog(this,errorMessage);
    }

    public void addOrderListener(ActionListener mal) {
        btnOrder.addActionListener(mal);
    }

}
