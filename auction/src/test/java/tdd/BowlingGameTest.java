package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BowlingGameTest {
    private Game game;

    @BeforeEach
    void setUp() throws Exception{
        game = new Game();
    }

    void rollMany(int rollCount, int pins){
        for(int i=0;i<rollCount;i++){
            game.roll(pins);
        }
    }


    @Test
    void testGutterGame(){
        rollMany(20, 0);

        assertEquals(0, game.score());
    }

    @Test
    void allOne(){
        rollMany(20, 1);

        assertEquals(20, game.score());
    }

    @Test
    void testOneSpare(){
        game.roll(5);
        game.roll(5);
        game.roll(3);
        rollMany(17,0);

        assertEquals(16, game.score());
    }

    @Test
    void testOneStrike(){
        game.roll(10);
        game.roll(3);
        game.roll(4);
        rollMany(16,0);
        assertEquals(24, game.score());
    }
}
