package com.ascacou.engine;


public class Board {


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
