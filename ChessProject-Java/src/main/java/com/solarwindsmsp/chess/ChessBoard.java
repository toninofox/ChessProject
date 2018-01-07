package com.solarwindsmsp.chess;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.solarwindsmsp.chess.pieces.ChessPiece;

public class ChessBoard {

	
	public static int MAX_BOARD_LENGTH = 8;
	public static int MAX_BOARD_HEIGHT_INDEX = MAX_BOARD_LENGTH-1;
	public static int MAX_BOARD_WIDTH_INDEX = MAX_BOARD_LENGTH-1;

	ChessPiece[][] pieces;

	public ChessBoard() {
		pieces = new ChessPiece[MAX_BOARD_LENGTH][MAX_BOARD_LENGTH];

	}

	public void Add(ChessPiece piece, int xCoordinate, int yCoordinate) {
		if (piecesLimitNotReached(piece) && IsLegalMove(xCoordinate, yCoordinate)) {
			piece.setChessBoard(this);
			piece.setXCoordinate(xCoordinate);
			piece.setYCoordinate(yCoordinate);
			pieces[xCoordinate][yCoordinate] = piece;
			System.out.println("Added Piece "+piece);
		} else {
			System.err.println("Invalid Coordinate "+xCoordinate+":"+yCoordinate+" for "+piece);
			piece.setXCoordinate(ChessPiece.INVALID_COORDINATE);
			piece.setYCoordinate(ChessPiece.INVALID_COORDINATE);
		}
	}

	public boolean IsLegalMove(int xCoordinate, int yCoordinate) {
		return IsLegalBoardPosition(xCoordinate, yCoordinate) && isNotAlreadyOccupied(xCoordinate, yCoordinate);
	}

	public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
		OptionalInt checkXCoordinate = IntStream.rangeClosed(0, MAX_BOARD_WIDTH_INDEX).filter(p -> p == xCoordinate).findAny();
		OptionalInt checkYCoordinate = IntStream.rangeClosed(0, MAX_BOARD_HEIGHT_INDEX).filter(p -> p == yCoordinate).findAny();
		boolean insideTheBoard = checkXCoordinate.isPresent() && checkYCoordinate.isPresent();
		if(!insideTheBoard) {
			System.err.println(xCoordinate+":"+yCoordinate+" are not valid coordinates");
		}
		return insideTheBoard;
	}
	
	private boolean isNotAlreadyOccupied(int xCoordinate, int yCoordinate) {
		boolean isAlreadyInTheBoard = isInTheBoard(getPieceOn(xCoordinate, yCoordinate));
		if(isAlreadyInTheBoard) {
			System.err.println("Position already occupied by "+getPieceOn(xCoordinate, yCoordinate));
		}
		return !isAlreadyInTheBoard;
	}

	public ChessPiece getPieceOn(int xCoordinate, int yCoordinate) {
		return pieces[xCoordinate][yCoordinate];
	}

	private boolean isInTheBoard(ChessPiece pawn) {
		return pawn != null;
	}

	private boolean piecesLimitNotReached(ChessPiece piece) {
		boolean limitNotReached = Arrays.stream(pieces).flatMap(Stream::of).filter(whereContainsPieceOfColor(piece.getPieceColor())).count() <= piece.getMaxNumberOfPiecesAllowed();
		if(!limitNotReached) {
			System.err.println("Max Pieces reached for "+piece);
		}
		return limitNotReached;
	}

	
	private Predicate<? super ChessPiece> whereContainsPieceOfColor(PieceColor pieceColor) {
		return new Predicate<ChessPiece>() {
			@Override
			public boolean test(ChessPiece t) {
				return isInTheBoard(t) && t.getPieceColor().equals(pieceColor);
			}
		};
	}
}
