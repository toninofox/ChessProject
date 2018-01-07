package com.solarwindsmsp.chess;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ChessBoard {

	private static final int INVALID_COORDINATE = -1;
	public static int MAX_BOARD_WIDTH_INDEX = 7;
	public static int MAX_BOARD_HEIGHT_INDEX = 7;

	ChessPiece[][] pieces;

	public ChessBoard() {
		pieces = new ChessPiece[MAX_BOARD_WIDTH_INDEX + 1][MAX_BOARD_HEIGHT_INDEX + 1];

	}

	public void Add(ChessPiece piece, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
		if (piecesLimitNotReached(piece) && IsLegalMove(xCoordinate, yCoordinate)) {
			piece.setChessBoard(this);
			piece.setXCoordinate(xCoordinate);
			piece.setYCoordinate(yCoordinate);
			pieces[xCoordinate][yCoordinate] = piece;
		} else {
			piece.setXCoordinate(INVALID_COORDINATE);
			piece.setYCoordinate(INVALID_COORDINATE);
		}
	}

	public boolean IsLegalMove(int xCoordinate, int yCoordinate) {
		return IsLegalBoardPosition(xCoordinate, yCoordinate) && isNotAlreadyOccupied(xCoordinate, yCoordinate);
	}

	public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
		OptionalInt checkXCoordinate = IntStream.rangeClosed(0, MAX_BOARD_WIDTH_INDEX).filter(p -> p == xCoordinate).findAny();
		OptionalInt checkYCoordinate = IntStream.rangeClosed(0, MAX_BOARD_HEIGHT_INDEX).filter(p -> p == yCoordinate).findAny();
		return checkXCoordinate.isPresent() && checkYCoordinate.isPresent();
	}
	
	private boolean isNotAlreadyOccupied(int xCoordinate, int yCoordinate) {
		return !isInTheBoard(pieces[xCoordinate][yCoordinate]);
	}

	private boolean isInTheBoard(ChessPiece pawn) {
		return pawn != null;
	}

	private boolean piecesLimitNotReached(ChessPiece piece) {
		return Arrays.stream(pieces).flatMap(Stream::of).filter(whereContainsPiece()).count() <= piece.getMaxNumberOfPiecesAllowed();
	}

	
	private Predicate<? super ChessPiece> whereContainsPiece() {
		return new Predicate<ChessPiece>() {
			@Override
			public boolean test(ChessPiece t) {
				return isInTheBoard(t);
			}
		};
	}
}
