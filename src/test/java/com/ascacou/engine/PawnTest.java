package com.ascacou.engine;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PawnTest {
    @Test
    public void toStringTest() throws Exception {
        assertEquals("B", Pawn.BLACK.toString());
        assertEquals("W", Pawn.WHITE.toString());
    }
}
