package view;

import controller.RegisterController;
import model.LoginModel;
import model.RegisterModel;
import model.StartModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JDialog{
    private JPanel loginPanel;
    private JButton btnBack;
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JButton btnLogin;
    private JButton btnRegister;
    LoginModel m_model;

    public LoginForm(LoginModel model){
        m_model=model;

        JPanel content=new JPanel();
        content=loginPanel;
        this.setContentPane(content);
        this.pack();

        this.setTitle("Log In");

        setMinimumSize(new Dimension(600,500));
        setLocationRelativeTo(btnRegister);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                StartModel model=new StartModel();
                StartForm startForm=new StartForm(null,model);
                startForm.setVisible(true);
            }
        });

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                RegisterModel model=new RegisterModel();
                RegisterForm view=new RegisterForm(model);
                RegisterController controller=new RegisterController(model,view);
            }
        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        //setVisible(true);

    }
    public String getUsername(){
        return tfUsername.getText();
    }

    public String getPassword(){
        String password=String.valueOf(pfPassword.getPassword());
        return password;
    }


    public void showError(String errorMessage){
        JOptionPane.showMessageDialog(this,errorMessage);
    }

    public void addLoginListener(ActionListener mal){
        btnLogin.addActionListener(mal);
    }
}
