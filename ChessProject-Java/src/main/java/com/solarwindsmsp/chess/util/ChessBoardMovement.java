package com.solarwindsmsp.chess.util;

import com.solarwindsmsp.chess.MovementType;
import com.solarwindsmsp.chess.pieces.ChessPiece;
import com.solarwindsmsp.chess.pieces.exception.InvalidPositionEx;

public class ChessBoardMovement {

	private ChessPiece piece;
	private ChessBoardMovementManager chessBoardMovementManager;

	ChessBoardMovement(ChessBoardMovementManager chessBoardMovementManager, ChessPiece piece) {
		this.chessBoardMovementManager = chessBoardMovementManager;
		this.piece = piece;
	}
	
	public ChessBoardMovementManager to(int x, int y) throws InvalidPositionEx {
		if(piece.IsLegalMove(x, y)) {
			piece.Move(MovementType.MOVE, x, y);
		} else {
			throw new InvalidPositionEx("Piece "+piece+" Cannot be moved to "+x+":"+y);
		}
		return chessBoardMovementManager;
	}
	
	public ChessBoardMovementManager capturing(int x, int y) throws InvalidPositionEx {
		if(piece.IsLegalMove(x, y)) {
			piece.Move(MovementType.CAPTURE, x, y);
		} else {
			throw new InvalidPositionEx("Piece "+piece+" Cannot capture Piece on "+x+":"+y);
		}
		return chessBoardMovementManager;
	}

}
