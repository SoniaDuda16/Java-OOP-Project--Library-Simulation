package controller;

import model.LoginModel;
import model.RegisterModel;
import view.LoginForm;
import view.RegisterForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginModel m_model;
    private LoginForm m_view;

    public LoginController(LoginModel model,LoginForm view) {
        m_model = model;
        m_view = view;
        view.addLoginListener(new LoginListener());
    }

    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String username;
            String password;
            try{
                username=m_view.getUsername();
                password=m_view.getPassword();
                m_model.loginUser(username,password);
            }
            catch(Exception exception){
                m_view.showError("Bad Input");
            }
        }
    }
}
