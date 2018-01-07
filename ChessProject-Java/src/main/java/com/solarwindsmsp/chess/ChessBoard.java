package com.solarwindsmsp.chess;

import java.util.OptionalLong;
import java.util.stream.LongStream;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 7;
    public static int MAX_BOARD_HEIGHT = 7;

    private Pawn[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];

    }

    public void Add(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        throw new UnsupportedOperationException("Need to implement ChessBoard.add()");
    }

    public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
    	OptionalLong checkXCoordinate = LongStream.range(0, MAX_BOARD_WIDTH).filter(p -> p == xCoordinate).findAny();
    	OptionalLong checkYCoordinate = LongStream.range(0, MAX_BOARD_WIDTH).filter(p -> p == yCoordinate).findAny();
    	return checkXCoordinate.isPresent() && checkYCoordinate.isPresent();
    }
}
