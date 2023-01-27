package view;

import controller.AdminController;
import controller.RegisterBookController;
import model.AdminModel;
import model.RegisterBookModel;
import model.RegisterModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterBookForm extends JDialog {
    private JPanel registerBookPanel;
    private JButton btnCancel;
    private JTextField tfTitle;
    private JTextField tfAuthor;
    public JComboBox cmbGenre;
    private JButton btnRegisterBook;
    private JTextField tfQuantity;
    private RegisterBookModel m_model;


    public RegisterBookForm(RegisterBookModel model){
        m_model=model;
        String drama=new String("Drama");
        cmbGenre.addItem(drama);
        String fiction=new String("Fiction");
        cmbGenre.addItem(fiction);
        String history=new String("History");
        cmbGenre.addItem(history);
        String scientific=new String("Scientific");
        cmbGenre.addItem(scientific);

        JPanel content=new JPanel();
        content=registerBookPanel;
        this.setContentPane(content);
        this.pack();

        this.setTitle("Register a new book");

        setMinimumSize(new Dimension(600,500));
        setLocationRelativeTo(btnCancel);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);

        setVisible(true);

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminModel model=new AdminModel();
                AdminForm view=new AdminForm(model);
                AdminController controller= new AdminController(model,view);
            }
        });
    }
    public String getTitle(){
        return tfTitle.getText();
    }

    public String getAuthor(){
        return tfAuthor.getText();
    }

    public String getGenre(){
        return cmbGenre.getSelectedItem().toString();
    }
     public String getQuantity(){
        return tfQuantity.getText();
     }

    public void showError(String errorMessage){
        JOptionPane.showMessageDialog(this,errorMessage);
    }

    public void addRegisterBookListener(ActionListener mal){
        btnRegisterBook.addActionListener(mal);
    }
}
