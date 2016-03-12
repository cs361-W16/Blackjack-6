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
        g.deal_player();
        g.deal_dealer();
        assertEquals("13Diamonds",g.dealer.holder.get(0).get(0).toString());
        assertEquals("13Spades",g.player.holder.get(0).get(0).toString());
    }
    @Test
    public void testCustomDeal (){
        Game g = new Game ();
        g.buildDeck();
        g.customDeal_p(0);
        assertEquals("1Clubs",g.player.holder.get(0).get(0).toString());
    }
    @Test
    public void test_get_value (){
        Game g = new Game ();
        g.buildDeck();
        g.deal_player();
        g.deal_player();
        assertEquals("13Diamonds",g.player.holder.get(0).get(1).toString());
        assertEquals("13Spades",g.player.holder.get(0).get(0).toString());
        assertEquals(20,g.player.calculate());
        Game g_1=new Game ();
        g_1.buildDeck();
        g_1.customDeal_p(0);
        assertEquals(1,g_1.player.holder.get(0).get(0).getValue());
        g_1.customDeal_p(29);
        assertEquals(8,g_1.player.holder.get(0).get(1).getValue());
        g_1.customDeal_p(33);
        assertEquals(9,g_1.player.holder.get(0).get(2).getValue());
        assertEquals(18,g_1.player.calculate());
    }
}