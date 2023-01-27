package model;

public class ScientificBook extends Book {

    public int findPrice(int quantity,String author){
        int price;
        if(quantity>=10 && author.equals("Sigmund Freud")){
            price = (int) (Math.random() * (30 - 25)) + 15;
        }
        else if(quantity<10 && author.equals("Sigmund Freud")){
            price = (int) (Math.random() * (50 - 30)) + 30;
        }
        else{
            price = (int) (Math.random() * (25 - 20)) + 20;
        }
        return price;
    }
}
