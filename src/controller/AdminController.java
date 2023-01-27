package controller;

import model.AdminModel;
import model.StartModel;
import view.AdminForm;
import view.StartForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminController {
    private AdminModel m_model;
    private AdminForm m_view;
    public AdminController(AdminModel model, AdminForm view){
        m_model=model;
        m_view=view;
    }

}
