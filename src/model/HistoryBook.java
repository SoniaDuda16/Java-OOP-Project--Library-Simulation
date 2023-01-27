package model;

public class HistoryBook extends Book{

    @Override
    public int findPrice(int quantity){
        int price;
        if(quantity>7) {
            price = (int) (Math.random() * (35 - 17)) + 17;
        }
        else{
            price = (int) (Math.random() * (40 - 25)) + 25;
        }
        return price;
    }
}
