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

    // This method may be replaced and should be used carefully (since it alters too much board state)
    void removePawn() {
        this.empty = true;
        this.pawn = null;
    }

    @Override
    public String toString() {
        return empty ? "." : pawn.toString();
    }
}
