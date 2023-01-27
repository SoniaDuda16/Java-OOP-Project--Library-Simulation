package controller;
import model.RegisterModel;
import view.RegisterForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterController {
    private RegisterModel m_model;
    private RegisterForm m_view;

    public RegisterController(RegisterModel model,RegisterForm view) {
        m_model = model;
        m_view = view;
        view.addRegisterListener(new RegisterListener());
    }

    class RegisterListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String name;
            String username;
            String phone;
            String password;
            String confirmPassword;
            try{
                name=m_view.getName();
                username=m_view.getUsername();
                phone=m_view.getPhone();
                password=m_view.getPassword();
                confirmPassword=m_view.getConfirmPassword();
                m_model.registerUser(name,username, phone,password,confirmPassword);
            }
            catch(Exception exception){
                m_view.showError("Bad Input");
            }
        }
    }
}
