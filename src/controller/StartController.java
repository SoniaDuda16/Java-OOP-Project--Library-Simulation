package controller;
import view.StartForm;
import model.StartModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartController {
    private StartModel m_model;
    private StartForm m_view;
    public StartController(StartModel model, StartForm view){
        m_model=model;
        m_view=view;
        view.addExitListener(new ExitListener());
    }

    class ExitListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
        }
    }
}
