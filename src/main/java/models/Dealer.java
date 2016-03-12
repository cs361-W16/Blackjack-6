package models;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Wenbo on 2/29/2016.
 */
public class Dealer extends  Gamer{
    public Dealer (){
        card_count=0;
    }
    public int calculate_d(){
        int total_value;
        total_value=0;
        for (int i =0;i<card_count;i++) {
            if (holder.get(0).get(i).getValue() > 10) {
                total_value += 10;
            } else if (holder.get(0).get(i).getValue() == 1){
                total_value += 11;
            }
            else {
                total_value += holder.get(0).get(i).getValue();
            }
        }
        return total_value;
    }
}
