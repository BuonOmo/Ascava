package com.ascacou.engine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest {
    Board board;
    Pawn black, white;

    @Before
    public void setUp() throws Exception {
        board = new Board();
        black = new Pawn(Color.BLACK);
        white = new Pawn(Color.WHITE);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void move() throws Exception {
    }

    @Test
    public void toStringTest() throws Exception {
        board.move(Board.Position.A1, black);
        board.move(Board.Position.B2, white);
        board.move(Board.Position.C2, black);
        System.out.println(board);
        assertEquals(
                " +-+-+-+-+-+\n" +
                "5|.|.|.|.|.|\n" +
                " +-+-+-+-+-+\n" +
                "4|.|.|.|.|.|\n" +
                " +-+-+-+-+-+\n" +
                "3|.|.|.|.|.|\n" +
                " +-+-+-+-+-+\n" +
                "2|.|W|B|.|.|\n" +
                " +-+-+-+-+-+\n" +
                "1|B|.|.|.|.|\n" +
                " +-+-+-+-+-+\n" +
                "  A B C D E\n",
                board.toString());
    }
}
