package com.solarwindsmsp.chess.pieces;

import com.solarwindsmsp.chess.PieceColor;

public class Pawn extends ChessPiece{

	private static final int BLACK_START_ROW = 6;
	private static final int WHITE_START_ROW = 1;

	public Pawn(PieceColor pieceColor) {
		super(pieceColor);
	}

	public boolean IsLegalMove(int newX, int newY) {
		if(getPieceColor().equals(PieceColor.BLACK)) {
			if(getYCoordinate() == BLACK_START_ROW) {
				return newX == getXCoordinate() && (newY  == getYCoordinate() - 1 || newY  == getYCoordinate() - 2);
			} else {
				return newX == getXCoordinate() && newY  == getYCoordinate() - 1;
			}
		} else{
			if(getYCoordinate() == WHITE_START_ROW) {
				return newX == getXCoordinate() && (newY  == getYCoordinate() + 1 || newY  == getYCoordinate() + 2);
			} else {
				return newX == getXCoordinate() && newY  == getYCoordinate() + 1;
			}
		} 
	}

	@Override
	public int getMaxNumberOfPiecesAllowed() {
		return 8;
	}

	
}
