package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Wenbo on 2/29/2016.
 */
public class Game {
    public java.util.List<Card> deck = new ArrayList<>();
    //public java.util.List<java.util.List<Card>> holder = new ArrayList<>();

    public Player player = new Player ();
    public Dealer dealer = new Dealer ();
    public Player player_s = new Player();
    public int bet_amount;
    public int user_win;
    public int user_win_s;
    public boolean split;
    public int P_value;
    public int P_value_s;
    public int D_value;
    public Game(){
        player.holder.add(new ArrayList<Card>());
        dealer.holder.add(new ArrayList<Card>());
        player_s.holder.add(new ArrayList<Card>());
        bet_amount=0;
        user_win=0;
        user_win_s=0;
        P_value=0;
        P_value_s=0;
        D_value=0;
        split=false;
    }

    public void buildDeck() {
        deck.clear();
        for(int i = 1; i < 14; i++){
            deck.add(new Card(i,Suit.Clubs));
            deck.add(new Card(i,Suit.Hearts));
            deck.add(new Card(i,Suit.Diamonds));
            deck.add(new Card(i,Suit.Spades));
        }
    }
    public void shuffle() {
        long seed = System.nanoTime();
        Collections.shuffle(deck, new Random(seed));
    }
    public void deal_player() {
        player.holder.get(0).add(deck.get(deck.size()-1));
        deck.remove(deck.size()-1);
        player.card_count+=1;
    }
    public void deal_player_s() {
        player_s.holder.get(0).add(deck.get(deck.size()-1));
        deck.remove(deck.size()-1);
        player_s.card_count+=1;
    }
    public void deal_dealer() {
        dealer.holder.get(0).add(deck.get(deck.size()-1));
        deck.remove(deck.size()-1);
        dealer.card_count+=1;
    }
    public void place_bet (int bet){
        player.bet-=bet;
        bet_amount+=bet;
    }
    public void assign_bet (){
        player.bet+=bet_amount*2;
    }
    public boolean check_brust (int value){
        if (value>21){
            return true;
        }
        else {
            return false;
        }

    }
    public void customDeal_p (int c1){
        player.holder.get(0).add(deck.get(c1));
        deck.remove(c1);
        player.card_count+=1;
    }
    public void customDeal_d (int c1){
        dealer.holder.get(0).add(deck.get(c1));
        deck.remove(c1);
        dealer.card_count+=1;
    }
}
