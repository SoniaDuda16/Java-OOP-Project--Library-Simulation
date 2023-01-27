package view;
import controller.LoginController;
import controller.RegisterController;
import model.LoginModel;
import model.RegisterModel;
import model.StartModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartForm extends JDialog {
    private JPanel startPanel;
    private JButton btnLogIn;
    private JButton btnRegister;
    private JButton btnExit;
    
    
    private StartModel m_model;
    public StartForm(JFrame parent,StartModel model) {
        super(parent);
        m_model=model;
        setTitle("Start");
        setContentPane(startPanel);
        setMinimumSize(new Dimension(600,500));
        setMaximumSize(new Dimension(600,500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                RegisterModel model = new RegisterModel();
                RegisterForm view = new RegisterForm(model);
                RegisterController controller = new RegisterController(model, view);
            }
        });

        btnLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                LoginModel model=new LoginModel();
                LoginForm view=new LoginForm(model);
                LoginController controller= new LoginController(model,view);
            }
        });

        btnExit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });

    }

    public void addExitListener(ActionListener mal){
        dispose();
        btnExit.addActionListener(mal);
    }
    public static void main(String args[]){
        StartModel model=new StartModel();
        StartForm startForm=new StartForm(null,model);
    }

}
