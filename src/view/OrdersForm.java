package view;

import controller.AdminController;
import controller.RegisterBookController;
import model.AdminModel;
import model.OrdersModel;
import model.RegisterBookModel;
import model.StartModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class OrdersForm extends JDialog {
    private JPanel ordersPanel;
    private JButton btnBack;
    private JTextArea txtOrder;
    private OrdersModel m_model;

    public OrdersForm(OrdersModel model){
        m_model=model;
        JPanel content=new JPanel();
        content=ordersPanel;

        final String DB_URL="jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt=conn.createStatement();
            String sql="SELECT username,title FROM orders";
            PreparedStatement preparedStatement=conn.prepareStatement(sql);

            int orderCounter=1;
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                String counter=Integer.toString(orderCounter);
                txtOrder.append(counter);
                txtOrder.append(". Username:");
                txtOrder.append(resultSet.getString("username"));
                txtOrder.append("   Title:");
                txtOrder.append(resultSet.getString("title"));
                txtOrder.append("\n");
                orderCounter++;
            }
            stmt.close();
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        this.setContentPane(content);
        this.pack();

        this.setTitle("Orders");

        setMinimumSize(new Dimension(600,500));
        setLocationRelativeTo(btnBack);

        this.setDefaultCloseOperation(HIDE_ON_CLOSE);

        setVisible(true);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminModel model=new AdminModel();
                AdminForm view=new AdminForm(model);
                AdminController controller= new AdminController(model,view);
            }
        });
    }

}
