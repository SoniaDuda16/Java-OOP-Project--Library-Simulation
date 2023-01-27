package controller;

import model.*;
import view.RegisterBookForm;
import view.UserHome;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserHomeController {
    private UserHomeModel m_model;
    private UserHome m_view;
    public String username;

    public UserHomeController(UserHomeModel model,UserHome view) {
        m_model = model;
        m_view = view;
        this.username=view.getUsername();
        view.addOrderListener(new OrderListener());
    }

    class OrderListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String username;
            String title;
            try{
                title=m_view.getTitle();
                username=m_view.getUsername();
                m_model.registerOrder(title,username);
            }
            catch(Exception exception){
                m_view.showError("Bad Input");
            }
        }
    }
}
