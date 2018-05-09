package com.ascacou.engine;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class PositionTest {
    private Position position;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        position = new Position(2, 4);
    }

    @Test
    public void constructor() throws Exception {
        assertEquals(2, position.x);
        assertEquals(4, position.y);

        position = new Position("C5");

        assertEquals(2, position.x);
        assertEquals(4, position.y);
    }

    @Test
    public void stringConstructorWithException() throws Exception {
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("String should respect [A-E][1-5] regex.");

        new Position("C6");
    }

    @Test
    public void integerConstructorWithException() throws Exception {
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Both x and y should be clamped between 0 and 4.");

        new Position(0, 6);
    }

    @Test
    public void toStringTest() throws Exception {
        assertEquals("C5", position.toString());
    }

    @Test
    public void equalsTest() throws Exception {
        assertEquals(Position.C5, position);
    }

}