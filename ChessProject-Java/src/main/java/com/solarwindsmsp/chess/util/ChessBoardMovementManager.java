package com.solarwindsmsp.chess.util;

import com.solarwindsmsp.chess.ChessBoard;

public class ChessBoardMovementManager {

	 private ChessBoard board;

	ChessBoardMovementManager(ChessBoard board) {
		this.board = board;
	}
	
	public ChessBoardMovement move(int x, int y) {
		return new ChessBoardMovement(this, board.getPieceOn(x, y));
	}

}
