package models;


import java.util.ArrayList;

/**
 * Created by Wenbo on 2/29/2016.
 */
public class Player extends Gamer {
    public int bet;
    public boolean Ace;
    public Player (){
        bet =100;
        card_count=0;
        Ace=false;
    }
    public int calculate(){
        int total_value;
        total_value=0;
        for (int i =0;i<card_count;i++) {
            if (holder.get(0).get(i).getValue() > 10) {
                total_value += 10;
            } else if (holder.get(0).get(i).getValue() == 1){
                total_value += 11;
                Ace=true;
            }
            else {
                total_value += holder.get(0).get(i).getValue();
            }
            if (Ace && total_value>21){
                total_value-=10;
                Ace=false;
            }
        }
        return total_value;
    }
}
