//This project contains a simulation of a virtual library.
//The users of this app can create a new account using their name, username, phone and a password and see all the titles in the
//library and order books one by one. Also, they can see all their orders.
//The account of the admin (username: admin;password: admin) permits the admin to register a new book in the library using a title, an author an available genre and the
//quantity for that collection of books. Admin can also see all orders made by costumers.
//Books are organized after genres which are extensions of the abstract class Book, representing inheritance. All of these extensions
// of particular types of books contain different methods for computing the price of one book depending on quantity (and author name)
// which represents overloading or overriding of the principal method implemented in the Book class.
//Interfaces are represented by the command buttons (register, register book) which implement Action Listeners in controller package.
//The project contains 7 GUIs done with Swing UI Designer provided by IntelliJ, with buttons, combo-boxes, text fields, password fields,
//text fields, labels and photos.
//The database is done using phpMyAdmin.

import controller.RegisterBookController;
import controller.RegisterController;
import controller.StartController;
import model.FictionBook;
import model.RegisterBookModel;
import model.RegisterModel;
import model.StartModel;
import view.RegisterBookForm;
import view.RegisterForm;
import view.StartForm;

public class Main {
    public static void main(String[] args) {

        StartModel model = new StartModel();
        StartForm view = new StartForm(null,model);
        StartController controller = new StartController(model, view);

        view.setVisible(true);
    }
}
