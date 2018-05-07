package com.ascacou.engine;

public class Case {
    private Pawn pawn;
    private boolean hasPawn;

    Case() {
        pawn = null;
        hasPawn = false;
    }

    boolean isEmpty() {
        return !hasPawn;
    }

    void add(Pawn pawn) {
        this.hasPawn = true;
        this.pawn = pawn;
    }

    @Override
    public String toString() {
        return isEmpty() ? "." : pawn.toString();
    }
}
