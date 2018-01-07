package com.solarwindsmsp.chess.util;

import com.solarwindsmsp.chess.MovementType;
import com.solarwindsmsp.chess.pieces.ChessPiece;

public class ChessBoardCapture {

	private ChessPiece pieces;
	private ChessBoardMovementManager chessBoardMovementManager;

	ChessBoardCapture(ChessBoardMovementManager chessBoardMovementManager, ChessPiece pieces) {
		this.chessBoardMovementManager = chessBoardMovementManager;
		this.pieces = pieces;
	}
	
	public ChessBoardMovementManager to(int x, int y) {
		pieces.Move(MovementType.CAPTURE, x, y);
		return chessBoardMovementManager;
	}

}
