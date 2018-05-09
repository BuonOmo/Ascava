package com.ascacou.engine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CaseTest {
    private Case aCase;

    @Before
    public void setUp() throws Exception {
        aCase = new Case();
    }

    @Test
    public void add() throws Exception {
        aCase.add(Pawn.WHITE);
        assertEquals("non-emptiness", false, aCase.isEmpty());
        assertEquals("pawn presence", Pawn.WHITE, aCase.pawn);
    }

}