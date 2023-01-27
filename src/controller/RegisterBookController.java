package controller;

import model.*;
import view.RegisterBookForm;
import view.RegisterForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterBookController {
    private RegisterBookModel m_model;
    private RegisterBookForm m_view;

    public RegisterBookController(RegisterBookModel model,RegisterBookForm view) {
        m_model = model;
        m_view = view;
        view.addRegisterBookListener(new RegisterBookListener());
    }

    class RegisterBookListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String title;
            String author;
            String genre;
            int price=0;
            int quantity;
            try{
                title=m_view.getTitle();
                author=m_view.getAuthor();
                quantity= Integer.parseInt(m_view.getQuantity());
                genre=m_view.cmbGenre.getSelectedItem().toString();;
                if(genre.equals("Drama")){
                    DramaBook dramaBook=new DramaBook();
                    price=dramaBook.findPrice(quantity);
                }
                if(genre.equals("Fiction")){
                    FictionBook fictionBook=new FictionBook();
                    price=fictionBook.findPrice(quantity,author);
                }
                if(genre.equals("Scientific")){
                    ScientificBook scientificBook=new ScientificBook();
                    price=scientificBook.findPrice(quantity,author);
                }
                if(genre.equals("History")){
                    HistoryBook historyBook=new HistoryBook();
                    price=historyBook.findPrice(quantity);
                }
                m_model.registerBook(title,author,genre,price,quantity);
            }
            catch(Exception exception){
                m_view.showError("Bad Input");
            }
        }
    }
}
