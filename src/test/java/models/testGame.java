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
    @Test
    public void test_bet_op (){
        Game g = new Game ();
        g.buildDeck();
        g.place_bet(20);
        assertEquals(80,g.player.bet);
        assertEquals(20,g.bet_amount);
        g.assign_bet();
        assertEquals(120,g.player.bet);
        g.bet_amount=0;
        assertEquals(0,g.bet_amount);
    }
    @Test
    public void test_value_dealer(){
        Game g = new Game ();
        g.buildDeck();
        g.deal_dealer();
        assertEquals(10,g.dealer.calculate_d());
        Game g_1=new Game ();
        g_1.buildDeck();
        g_1.customDeal_d(0);
        assertEquals(11,g_1.dealer.calculate_d());
        assertEquals(false,g_1.check_brust(g_1.dealer.calculate_d()));
    }
    @Test
    public void test_compare (){
        Game g = new Game ();
        g.buildDeck();
        g.deal_dealer();
        g.deal_player();
        assertEquals(0,g.compare());
        g.customDeal_p(0);
        g.customDeal_d(5);
        assertEquals(1,g.compare());
        Game g_1 = new Game();
        g_1.buildDeck();
        g_1.deal_player();
        g_1.deal_player();
        g_1.deal_player();
        g_1.deal_dealer();
        assertEquals(2,g_1.compare());
    }
    @Test
    public void test_split_card_back (){
        Game g = new Game ();
        g.buildDeck();
        g.deal_player();
        g.deal_player();
        g.split();
        assertEquals(true,g.split);
        assertEquals(2,g.player.card_count);
        assertEquals(2,g.player_s.card_count);
        assertEquals(13,g.player_s.holder.get(0).get(0).getValue());
        assertEquals(1,g.compare_s());
        g.player.holder.get(0).clear();
        g.card_back();
        assertEquals(2,g.player.card_count);
        assertEquals(2,g.player.holder.get(0).size());
        assertEquals(0,g.player_s.holder.get(0).size());
        assertEquals(0,g.player_s.card_count);

    }
}