package com.ascacou.engine;

public class Case {
    Pawn pawn;
    private boolean empty;

    Case() {
        pawn = null;
        empty = true;
    }

    boolean isEmpty() {
        return empty;
    }

    void add(Pawn pawn) {
        this.empty = false;
        this.pawn = pawn;
    }

    @Override
    public String toString() {
        return empty ? "." : pawn.toString();
    }
}
