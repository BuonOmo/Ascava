package com.ascacou.engine;

public class Board {
    enum Position {
        A1(0, 0), B1(1, 0), C1(2, 0), D1(3, 0), E1(4, 0),
        A2(0, 1), B2(1, 1), C2(2, 1), D2(3, 1), E2(4, 1),
        A3(0, 2), B3(1, 2), C3(2, 2), D3(3, 2), E3(4, 2),
        A4(0, 3), B4(1, 3), C4(2, 3), D4(3, 3), E4(4, 3),
        A5(0, 4), B5(1, 4), C5(2, 4), D5(3, 4), E5(4, 4);

        private int x, y;

        int x() {
            return x;
        }

        int y() {
            return y;
        }

        private Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // positions are [Y][X] - [A-E][1-5]
    private Case[][] board;

    Board() {
        board = new Case[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = new Case();
            }
        }
    }

    boolean move(Position position, Pawn pawn) {
        if (board[position.y()][position.x()].isEmpty()) {
            board[position.y()][position.x()].add(pawn);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        String line = " +-+-+-+-+-+\n";
        boardString.append(line);
        for (int i = 4; i >= 0; i--) {
            boardString.append(i + 1);
            for (int j = 0; j < 5; j++) {
                boardString.append('|');
                boardString.append(board[i][j]);
            }
            boardString.append("|\n");
            boardString.append(line);
        }
        boardString.append("  A B C D E\n");
        return boardString.toString();
    }
}
