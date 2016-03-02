package models;

/**
 * Created by Wenbo on 2/29/2016.
 */
import org.junit.Test;

import static org.junit.Assert.*;

public class testCard {
    @Test
    public void testGetSuit(){
        Card c = new Card(5,Suit.Clubs);
        assertEquals(Suit.Clubs,c.getSuit());
    }
    @Test
    public void testGetValue(){
        Card c = new Card(5,Suit.Clubs);
        assertEquals(5,c.getValue());
    }
    @Test
    public void testToString(){
        Card c = new Card(5,Suit.Clubs);
        assertEquals("5Clubs",c.toString());
    }
}
