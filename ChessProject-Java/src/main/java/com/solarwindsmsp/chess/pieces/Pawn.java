package com.solarwindsmsp.chess.pieces;

import com.solarwindsmsp.chess.PieceColor;

public class Pawn extends ChessPiece{

	public Pawn(PieceColor pieceColor) {
		super(pieceColor);
	}

	public boolean IsLegalMove(int newX, int newY) {
		if(getPieceColor().equals(PieceColor.BLACK)) {
			return newX == getXCoordinate() && newY  == getYCoordinate() - 1;
		} else{
			return newX == getXCoordinate() && newY  == getYCoordinate() + 1;
		} 
	}

	@Override
	public int getMaxNumberOfPiecesAllowed() {
		return 8;
	}

}
