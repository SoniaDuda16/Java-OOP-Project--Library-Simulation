package view;

import controller.AdminController;
import controller.UserHomeController;
import model.AdminModel;
import model.UserHomeModel;
import model.UserOrdersModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UserOrdersForm extends JDialog{
    private JPanel userOrdersPanel;
    private JTextArea txtOrders;
    private JButton btnBack;
    public UserOrdersModel m_model;
    public String username;


    public UserOrdersForm(UserOrdersModel model, String username) {
        m_model = model;
        this.username = username;
        JPanel content = new JPanel();
        content = userOrdersPanel;

        final String DB_URL = "jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "SELECT title FROM orders WHERE username=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, this.username);

            int orderCounter = 1;
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String counter = Integer.toString(orderCounter);
                txtOrders.append(counter);
                txtOrders.append(".   Title:");
                txtOrders.append(resultSet.getString("title"));
                txtOrders.append("\n");
                orderCounter++;
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setContentPane(content);
        this.pack();

        this.setTitle("My Orders");

        setMinimumSize(new Dimension(600,500));
        setLocationRelativeTo(btnBack);

        this.setDefaultCloseOperation(HIDE_ON_CLOSE);

        setVisible(true);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                UserHomeModel model=new UserHomeModel();
                UserHome view=new UserHome(model,username);
                UserHomeController controller= new UserHomeController(model,view);
            }
        });
    }
}
