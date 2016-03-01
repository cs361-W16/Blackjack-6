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
    public Game(){
        player.holder.add(new ArrayList<Card>());
        dealer.holder.add(new ArrayList<Card>());
    }

    public void buildDeck() {
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

    public void deal() {
        player.holder.get(0).add(deck.get(deck.size()-1));
        deck.remove(deck.size()-1);
    }


    public void customDeal (int c1){
        player.holder.get(0).add(deck.get(c1));
        deck.remove(c1);
    }
}
