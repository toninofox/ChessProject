package com.solarwindsmsp.chess.util;

import org.junit.Test;

import com.solarwindsmsp.chess.PieceColor;
import com.solarwindsmsp.chess.pieces.exception.InvalidPositionEx;

public class ChessBoardInitializationTest {

	@Test
	public void fluentTest() throws InvalidPositionEx {
		ChessBoardInitialization.init()
			.addPawnOn(PieceColor.BLACK, 0, 6)
			.addPawnOn(PieceColor.BLACK, 1, 6)
			.addPawnOn(PieceColor.BLACK, 2, 6)
			.addPawnOn(PieceColor.BLACK, 3, 6)
			.addPawnOn(PieceColor.BLACK, 4, 6)
			.addPawnOn(PieceColor.BLACK, 5, 6)
			.addPawnOn(PieceColor.BLACK, 6, 6)
			.addPawnOn(PieceColor.BLACK, 7, 6)
			.addPawnOn(PieceColor.WHITE, 0, 1)
			.addPawnOn(PieceColor.WHITE, 1, 1)
			.addPawnOn(PieceColor.WHITE, 2, 1)
			.addPawnOn(PieceColor.WHITE, 3, 1)
			.addPawnOn(PieceColor.WHITE, 4, 1)
			.addPawnOn(PieceColor.WHITE, 5, 1)
			.addPawnOn(PieceColor.WHITE, 6, 1)
			.addPawnOn(PieceColor.WHITE, 7, 1)
				.complete()
			.move(0, 6).to(0, 4)
			.move(6, 1).to(6, 3)
			.move(1, 6).to(1, 5)
			.move(2, 1).to(2, 2);
	}
	
	@Test(expected=InvalidPositionEx.class)
	public void testInvalidMovement() throws InvalidPositionEx {
		ChessBoardInitialization.init()
			.addPawnOn(PieceColor.BLACK, 0, 6)
				.complete()
			.move(0, 6).to(0, 3);
	}
	
	@Test(expected=InvalidPositionEx.class)
	public void captureInvalidInitialization() throws InvalidPositionEx {
		ChessBoardInitialization.init()
			.addPawnOn(PieceColor.BLACK, 0, 6)
			.addPawnOn(PieceColor.BLACK, 1, 6)
			.addPawnOn(PieceColor.BLACK, 2, 6)
			.addPawnOn(PieceColor.BLACK, 3, 6)
			.addPawnOn(PieceColor.BLACK, 4, 6)
			.addPawnOn(PieceColor.BLACK, 5, 6)
			.addPawnOn(PieceColor.BLACK, 6, 6)
			.addPawnOn(PieceColor.BLACK, 7, 6)
			.addPawnOn(PieceColor.BLACK, 7, 6);
	}

}
