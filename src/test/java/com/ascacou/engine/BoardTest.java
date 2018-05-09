package com.ascacou.engine;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.*;


public class BoardTest {
    private Board board;


    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        board = new Board();
    }

    @Test
    public void getSquare() throws Exception {
        board.move(Position.A1, Pawn.BLACK);
        board.move(Position.A2, Pawn.WHITE);
        board.move(Position.B1, Pawn.BLACK);
        board.move(Position.B2, Pawn.WHITE);

        assertEquals(0b11110011, board.getSquare(Position.A1));
        assertEquals(0b01010001, board.getSquare(Position.B1));

        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Square positions are clamped between 0 and 3 (since they have a 2x2 size).");
        board.getSquare(Position.E2);
    }

    @Test
    public void getAdjacentFullSquares() throws Exception {
        assertEquals("An empty Case has no adjacent full squares",
                     0,
                     board.getAdjacentFullSquares(Position.C2).size());

        board.move(Position.C2, Pawn.BLACK);
        assertEquals(0, board.getAdjacentFullSquares(Position.C2).size());

        board.move(Position.C3, Pawn.BLACK);
        board.move(Position.B2, Pawn.BLACK);
        board.move(Position.B3, Pawn.WHITE);
        assertEquals(
                Collections.singletonList(Position.B2),
                board.getAdjacentFullSquares(Position.C2)
        );


        board.move(Position.D2, Pawn.WHITE);
        board.move(Position.D3, Pawn.BLACK);
        assertEquals(
                Arrays.asList(Position.B2, Position.C2),
                board.getAdjacentFullSquares(Position.C2)
        );

        board.move(Position.C1, Pawn.WHITE);
        board.move(Position.D1, Pawn.WHITE);
        assertEquals(
                Arrays.asList(Position.B2, Position.C1, Position.C2),
                board.getAdjacentFullSquares(Position.C2)
        );

        board.move(Position.B1, Pawn.WHITE);
        assertEquals(
                Arrays.asList(Position.B1, Position.B2, Position.C1, Position.C2),
                board.getAdjacentFullSquares(Position.C2)
        );

        assertEquals(
                Collections.singletonList(Position.B1),
                board.getAdjacentFullSquares(Position.B1)
        );

    }

    @Test
    public void verify() throws Exception {
        board.addCompletedCard(0b1111);
        board.move(Position.B1, Pawn.BLACK);
        board.move(Position.B2, Pawn.BLACK);
        board.move(Position.C1, Pawn.BLACK);
        assertFalse(board.verify(Position.C2, Pawn.BLACK));
        assertTrue(board.verify(Position.C2, Pawn.WHITE));

    }

    @Test
    public void move() throws Exception {
        assertTrue(board.move(Position.A1, Pawn.BLACK));
        board.move(Position.A2, Pawn.BLACK);
        board.move(Position.B1, Pawn.BLACK);
        board.move(Position.B2, Pawn.BLACK);
        assertEquals(new HashSet<>(Collections.singletonList(0b1111)), board.getCompletedCards());
        board.move(Position.C1, Pawn.BLACK);
        assertFalse(board.move(Position.C2, Pawn.BLACK));
        assertTrue(board.move(Position.C2, Pawn.WHITE));
        assertEquals(new HashSet<>(Arrays.asList(0b1111, 0b0111)), board.getCompletedCards());

    }

    @Test
    public void toStringTest() throws Exception {
        board.move(Position.A1, Pawn.BLACK);
        board.move(Position.B2, Pawn.WHITE);
        board.move(Position.C2, Pawn.BLACK);
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
