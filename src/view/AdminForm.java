package view;

import controller.AdminController;
import controller.OrdersController;
import controller.RegisterBookController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminForm extends JDialog{
    private JPanel adminPanel;
    private JButton btnBack;
    private JButton btnAddBook;
    private JButton btnOrders;
    public AdminModel m_model;

    public AdminForm(AdminModel model){
        m_model=model;
        JPanel content=new JPanel();
        content=adminPanel;

        this.setContentPane(content);
        this.pack();

        this.setTitle("Admin Home");

        setMinimumSize(new Dimension(600,500));

        setLocationRelativeTo(btnOrders);

        this.setDefaultCloseOperation(HIDE_ON_CLOSE);

        setVisible(true);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StartModel model=new StartModel();
                StartForm startForm=new StartForm(null,model);
                startForm.setVisible(true);
            }
        });

        btnAddBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                RegisterBookModel model=new RegisterBookModel();
                RegisterBookForm view=new RegisterBookForm(model);
                RegisterBookController controller= new RegisterBookController(model,view);
            }
        });

        btnOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                OrdersModel model=new OrdersModel();
                OrdersForm view=new OrdersForm(model);
                OrdersController controller= new OrdersController(model,view);
            }
        });
    }
}
