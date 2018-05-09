package com.ascacou.engine;

public final class Position {
    public final int x, y;

    public final static Position A1 = new Position(0, 0),
                                 B1 = new Position(1, 0),
                                 C1 = new Position(2, 0),
                                 D1 = new Position(3, 0),
                                 E1 = new Position(4, 0),

                                 A2 = new Position(0, 1),
                                 B2 = new Position(1, 1),
                                 C2 = new Position(2, 1),
                                 D2 = new Position(3, 1),
                                 E2 = new Position(4, 1),

                                 A3 = new Position(0, 2),
                                 B3 = new Position(1, 2),
                                 C3 = new Position(2, 2),
                                 D3 = new Position(3, 2),
                                 E3 = new Position(4, 2),

                                 A4 = new Position(0, 3),
                                 B4 = new Position(1, 3),
                                 C4 = new Position(2, 3),
                                 D4 = new Position(3, 3),
                                 E4 = new Position(4, 3),

                                 A5 = new Position(0, 4),
                                 B5 = new Position(1, 4),
                                 C5 = new Position(2, 4),
                                 D5 = new Position(3, 4),
                                 E5 = new Position(4, 4);

    Position(int x, int y) {
        if (x < 0 || x > 4 || y < 0 || y > 4)
            throw new IndexOutOfBoundsException("Both x and y should be clamped between 0 and 4.");
        this.x = x;
        this.y = y;
    }

    Position(String str) {
        if (!str.matches("[A-E][1-5]"))
            throw new IndexOutOfBoundsException("String should respect [A-E][1-5] regex.");
        this.x = str.charAt(0) - 65;
        this.y = str.charAt(1) - 49;
    }

    @Override
    public String toString() {
        return "" + ((char) (x + 65)) + (y + 1);
    }
}

// TODO: test this and replace enum by class in board