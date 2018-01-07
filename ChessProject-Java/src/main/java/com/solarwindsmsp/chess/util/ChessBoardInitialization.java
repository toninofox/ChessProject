package com.solarwindsmsp.chess.util;

import java.text.MessageFormat;

import com.solarwindsmsp.chess.ChessBoard;
import com.solarwindsmsp.chess.PieceColor;
import com.solarwindsmsp.chess.pieces.Pawn;
import com.solarwindsmsp.chess.pieces.exception.InvalidPositionEx;

public class ChessBoardInitialization {

	private ChessBoard board;

	private ChessBoardInitialization(ChessBoard board) {
		this.board = board;
	}

	public static ChessBoardInitialization init() {
		return new ChessBoardInitialization(new ChessBoard());
	}
	
	public ChessBoardInitialization addPawnOn(PieceColor color, int x, int y) throws InvalidPositionEx {
		Pawn piece = new Pawn(color);
		board.Add(piece, x, y);
		if(!piece.isAddedOnTheBoard()) {
			throw new InvalidPositionEx(MessageFormat.format("Piece {0} cannot be added to coordinate {1}:{2}", piece,x,y));
		} else {
			return this;
		}
	}
	
	public ChessBoardMovementManager complete() {
		return new ChessBoardMovementManager(board);
	}
}
