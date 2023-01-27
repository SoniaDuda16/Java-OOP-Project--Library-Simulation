package view;

import controller.RegisterController;
import model.RegisterModel;
import model.StartModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm extends JDialog {
    private JPanel registerPanel;
    private JButton btnBack;
    private JTextField tfName;
    private JTextField tfUsername;
    private JTextField tfPhone;
    private JPasswordField pfPassword;
    private JPasswordField pfConfirmPassword;
    private RegisterModel m_model;
    private JButton btnRegister;

    public RegisterForm(RegisterModel model){
        m_model=model;

        JPanel content=new JPanel();
        content=registerPanel;
        this.setContentPane(content);
        this.pack();

        this.setTitle("Create a new account");

        setMinimumSize(new Dimension(600,500));
        setLocationRelativeTo(btnRegister);
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
    }

    public String getName(){
        return tfName.getText();
    }

    public String getUsername(){
        return tfUsername.getText();
    }

    public String getPhone(){
        return tfPhone.getText();
    }

    public String getPassword(){
        String password=String.valueOf(pfPassword.getPassword());
        return password;
    }

    public String getConfirmPassword(){
        String confirmPassword=String.valueOf(pfConfirmPassword.getPassword());
        return confirmPassword;
    }

    public void showError(String errorMessage){
        JOptionPane.showMessageDialog(this,errorMessage);
    }

    public void addRegisterListener(ActionListener mal){
        btnRegister.addActionListener(mal);
    }
}
