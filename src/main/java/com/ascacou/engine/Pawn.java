package com.ascacou.engine;

public class Pawn {
    Color color;

    Pawn(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color == Color.BLACK ? "B" : "W";
    }
}
