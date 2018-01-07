package com.solarwindsmsp.chess;

public abstract class ChessPiece {

	
	private ChessBoard chessBoard;
	private int xCoordinate;
	private int yCoordinate;
	private PieceColor pieceColor;

	public ChessPiece(PieceColor pieceColor) {
		setPieceColor(pieceColor);
	}

	public ChessBoard getChesssBoard() {
		return chessBoard;
	}
	

	public void setChessBoard(ChessBoard chessBoard) {
		this.chessBoard = chessBoard;
	}

	public int getXCoordinate() {
		return xCoordinate;
	}

	public void setXCoordinate(int value) {
		this.xCoordinate = value;
	}

	public int getYCoordinate() {
		return yCoordinate;
	}

	public void setYCoordinate(int value) {
		this.yCoordinate = value;
	}

	public PieceColor getPieceColor() {
		return this.pieceColor;
	}

	protected void setPieceColor(PieceColor value) {
		pieceColor = value;
	}

	public void Move(MovementType movementType, int newX, int newY) {
		if (movementType == MovementType.CAPTURE) {
			throw new UnsupportedOperationException("MovementType.CAPTURE is not implemented yet");
		} else {
			if (chessBoard.IsLegalMove(newX, newY) && this.IsLegalMove(newX, newY)) {
				this.setXCoordinate(newX);
				this.setYCoordinate(newY);
			}
		}
	}

	public abstract boolean IsLegalMove(int newX, int newY);

	public abstract int getMaxNumberOfPiecesAllowed();

	@Override
	public String toString() {
		return CurrentPositionAsString();
	}

	protected String CurrentPositionAsString() {
		String eol = System.lineSeparator();
		return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, xCoordinate, yCoordinate,
				pieceColor);
	}


}
