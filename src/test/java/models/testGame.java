package models;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by Wenbo on 2/29/2016.
 */
public class testGame {
    @Test
    public void testGameCreation(){
        Game g = new Game();
        assertNotNull(g);
    }

    @Test
    public void testGameBuildDeck(){
        Game g = new Game();
        g.buildDeck();
        assertEquals(52,g.deck.size());
    }
    @Test
    public void testGameInit(){
        Game g = new Game();
        g.buildDeck();
        g.shuffle();
        assertNotEquals(1,g.deck.get(0).getValue());
    }
    @Test
    public void Deal (){
        Game g = new Game ();
        g.buildDeck();
        g.deal();
        assertEquals("13Spades",g.player.holder.get(0).get(0).toString());
    }
    @Test
    public void testCustomDeal (){
        Game g = new Game ();
        g.buildDeck();
        g.customDeal(0);
        assertEquals("1Clubs",g.player.holder.get(0).get(0).toString());
    }
}
