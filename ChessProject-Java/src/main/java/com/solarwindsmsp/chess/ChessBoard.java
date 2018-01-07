package com.solarwindsmsp.chess;

import java.util.Arrays;
import java.util.OptionalLong;
import java.util.function.Predicate;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ChessBoard {

	private static final long MAX_N_OF_PAWNS = 8;
	private static final int INVALID_COORDINATE = -1;
	public static int MAX_BOARD_WIDTH = 7;
	public static int MAX_BOARD_HEIGHT = 7;

	private Pawn[][] pieces;

	public ChessBoard() {
		pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];

	}

	public void Add(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
		if (piecesLimitNotReached() && IsLegalBoardPosition(xCoordinate, yCoordinate)) {
			pawn.setXCoordinate(xCoordinate);
			pawn.setYCoordinate(yCoordinate);
			pieces[xCoordinate][yCoordinate] = pawn;
		} else {
			pawn.setXCoordinate(INVALID_COORDINATE);
			pawn.setYCoordinate(INVALID_COORDINATE);
		}
	}

	private boolean piecesLimitNotReached() {
		return Arrays.stream(pieces).flatMap(Stream::of).filter(whereContainsPiece()).count() <= MAX_N_OF_PAWNS;
	}

	private Predicate<? super Pawn> whereContainsPiece() {
		return new Predicate<Pawn>() {
			@Override
			public boolean test(Pawn t) {
				return t != null;
			}
		};
	}

	public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
		OptionalLong checkXCoordinate = LongStream.range(0, MAX_BOARD_WIDTH).filter(p -> p == xCoordinate).findAny();
		OptionalLong checkYCoordinate = LongStream.range(0, MAX_BOARD_HEIGHT).filter(p -> p == yCoordinate).findAny();
		return checkXCoordinate.isPresent() && checkYCoordinate.isPresent();
	}
}
