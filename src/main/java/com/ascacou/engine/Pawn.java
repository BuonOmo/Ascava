package com.ascacou.engine;

public enum Pawn {
    BLACK(Color.BLACK), WHITE(Color.WHITE);

    Color color;

    Pawn(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color == Color.BLACK ? "B" : "W";
    }
}
