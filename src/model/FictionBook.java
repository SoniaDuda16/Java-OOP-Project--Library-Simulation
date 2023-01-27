package model;

public class FictionBook extends Book{

    public int findPrice(int quantity,String author){
        int price=0;
        if(quantity>10 && author.equals("J.K.Rowling")){
            price = (int) (Math.random() * (36 - 18)) + 18;
        }
        else{
            price = (int) (Math.random() * (30 - 20)) + 20;
        }
        return price;
    }
}
