package com.ascacou.engine;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Board {
    // A group of four cases is represented this way:
    //
    //        A B
    //
    //       +-+-+
    //     1 |A|B|
    //       +-+-+
    //     2 |C|D|
    //       +-+-+
    //
    // So if A has (x, y) coordinates, B has (x+1, y).
    private static final int STACK_ENCODE = 0b11110000;
    private static final int CARD_ENCODE = 0b00001111;

//    private static final int A_FULL = 0b00010000;
//    private static final int B_FULL = 0b00100000;
//    private static final int C_FULL = 0b01000000;
//    private static final int D_FULL = 0b10000000;
//
//    private static final int A_BLACK = 0b0001;
//    private static final int B_BLACK = 0b0010;
//    private static final int C_BLACK = 0b0100;
//    private static final int D_BLACK = 0b1000;

    // positions are [Y][X] - [A-E][1-5]
    private Case[][] board;

    private Set<Integer> completedCards;

    Board() {
        board = new Case[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = new Case();
            }
        }

        completedCards = new HashSet<>();
    }

    boolean move(Position position, Pawn pawn) {
        if (verify(position, pawn)) {
            board[position.y][position.x].add(pawn);
            updateCompletedCards(position);
            return true;
        }
        return false;
    }

    void addCompletedCard(int card) {
        completedCards.add(card & CARD_ENCODE);
    }

    Set<Integer> getCompletedCards() {
        return completedCards;
    }

    private void updateCompletedCards(Position position) {
        getAdjacentFullSquares(position).forEach(p -> addCompletedCard(getSquare(p)));
    }

    int getSquare(Position position) {
        int x = position.x, y = position.y;
        int buffer = 0;
        if (x > 3 || y > 3) {
            throw new IndexOutOfBoundsException(
                    "Square positions are clamped between 0 and 3 (since they have a 2x2 size)."
            );
        }

        for (int i = 0; i < 4; i++) {
            Case current = board[y + (i/2)][x + i%2];
            if (current.isEmpty()) continue;
            int full = 1 << i + 4;
            int black = 1 << i;
            buffer |= full;
            if (current.pawn.color == Color.BLACK) buffer |= black;
        }

        return buffer;
    }

    List<Position> getAdjacentFullSquares(Position position) {
        LinkedList<Position> adjacentFullSquares = new LinkedList<>();
        if (boardAt(position).isEmpty()) return adjacentFullSquares;

        int x = position.x, y = position.y;
        int left = x-1, right = x+1, down = y-1, up = y+1;
        boolean exploreDown = y > 0 && !board[down][x].isEmpty();
        boolean exploreUp = y < 4 && !board[up][x].isEmpty();

        if (x > 0 && !board[y][left].isEmpty()) { // explore left
            if (exploreDown && !board[down][left].isEmpty()) {
                adjacentFullSquares.add(new Position(left, down));
            }
            if (exploreUp && !board[up][left].isEmpty()) {
                adjacentFullSquares.add(new Position(left, y));
            }
        }
        if (x < 4 && !board[y][right].isEmpty()) { // explore right
            if (exploreDown && !board[down][right].isEmpty()) {
                adjacentFullSquares.add(new Position(x, down));
            }
            if (exploreUp && !board[up][right].isEmpty()) {
                adjacentFullSquares.add(new Position(x, y));
            }
        }
        return adjacentFullSquares;
    }

    // true if move is authorized
    boolean verify(Position position, Pawn pawn) {
        if (!boardAt(position).isEmpty()) return false;
        int x = position.x, y = position.y;

        // Quick add pawn without using method move, just for square verification
        board[y][x].add(pawn);

        boolean anyCardAlreadyCompleted = getAdjacentFullSquares(position).stream()
                .mapToInt(this::getSquare)
                .anyMatch(card -> completedCards.contains(card & CARD_ENCODE));

        board[y][x].removePawn();
        return !anyCardAlreadyCompleted;
    }

    // Utility functions
    private Case boardAt(Position position) {
        return board[position.y][position.x];
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
