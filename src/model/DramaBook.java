package model;

public class DramaBook extends Book{

    @Override
    public int findPrice(int quantity){
        int price;
        if(quantity>10) {
            price = (int) (Math.random() * (30 - 15)) + 15;
        }
        else{
            price = (int) (Math.random() * (45 - 25)) + 25;
        }
        return price;
    }

}
