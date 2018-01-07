package com.solarwindsmsp.chess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ChessBoardTest {

	private ChessBoard testSubject;

	@Before
	public void setUp() throws Exception {
		testSubject = new ChessBoard();
	}

	@Test
	public void theTotalLengthOfTableIs8() throws Exception {
		assertEquals(8, testSubject.pieces.length);
	}

	@Test
	public void testHas_MaxBoardWidthIndex_of_7() {
		assertEquals(7, ChessBoard.MAX_BOARD_WIDTH_INDEX);
	}

	@Test
	public void testHas_MaxBoardHeightIndex_of_7() {
		assertEquals(7, ChessBoard.MAX_BOARD_HEIGHT_INDEX);
	}

	@Test
	public void testIsLegalBoardPosition_True_X_equals_0_Y_equals_0() {
		boolean isValidPosition = testSubject.IsLegalBoardPosition(0, 0);
		assertTrue(isValidPosition);
	}

	@Test
	public void testIsLegalBoardPosition_True_X_equals_5_Y_equals_5() {
		boolean isValidPosition = testSubject.IsLegalBoardPosition(5, 5);
		assertTrue(isValidPosition);
	}

	@Test
	public void testIsLegalBoardPosition_True_X_equals_7_Y_equals_7() {
		boolean isValidPosition = testSubject.IsLegalBoardPosition(7, 7);
		assertTrue(isValidPosition);
	}

	@Test
	public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_5() {
		boolean isValidPosition = testSubject.IsLegalBoardPosition(11, 5);
		assertFalse(isValidPosition);
	}

	@Test
	public void testIsLegalBoardPosition_False_X_equals_0_Y_equals_9() {
		boolean isValidPosition = testSubject.IsLegalBoardPosition(0, 9);
		assertFalse(isValidPosition);
	}

	@Test
	public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_0() {
		boolean isValidPosition = testSubject.IsLegalBoardPosition(11, 0);
		assertFalse(isValidPosition);
	}

	@Test
	public void testIsLegalBoardPosition_False_For_Negative_Y_Values() {
		boolean isValidPosition = testSubject.IsLegalBoardPosition(5, -1);
		assertFalse(isValidPosition);
	}

	@Test
	public void testAvoids_Duplicate_Positioning() {
		Pawn firstPawn = new Pawn(PieceColor.BLACK);
		Pawn secondPawn = new Pawn(PieceColor.BLACK);
		testSubject.Add(firstPawn, 6, 3);
		testSubject.Add(secondPawn, 6, 3);
		assertEquals(6, firstPawn.getXCoordinate());
		assertEquals(3, firstPawn.getYCoordinate());
		assertEquals(-1, secondPawn.getXCoordinate());
		assertEquals(-1, secondPawn.getYCoordinate());
	}

	@Test
	public void testLimits_The_Number_Of_Pawns() {
		for (int i = 0; i < 10; i++) {
			Pawn pawn = new Pawn(PieceColor.BLACK);
			int row = i / ChessBoard.MAX_BOARD_LENGTH;
			testSubject.Add(pawn, ChessBoard.MAX_BOARD_WIDTH_INDEX + row, i % ChessBoard.MAX_BOARD_LENGTH);
			if (row < 1) {
				assertEquals(ChessBoard.MAX_BOARD_WIDTH_INDEX + row, pawn.getXCoordinate());
				assertEquals(i % ChessBoard.MAX_BOARD_LENGTH, pawn.getYCoordinate());
			} else {
				assertEquals(-1, pawn.getXCoordinate());
				assertEquals(-1, pawn.getYCoordinate());
			}
		}
	}

	@Test
	public void testLimitsTheNumberOfPawnsByColor() throws Exception {
		for (int i = 0; i < 8; i++) {
			Pawn bpawn = new Pawn(PieceColor.BLACK);
			testSubject.Add(bpawn, i, 6);
			Pawn wpawn = new Pawn(PieceColor.WHITE);
			testSubject.Add(wpawn, i, 1);
			assertEquals(i, bpawn.getXCoordinate());
			assertEquals(6, bpawn.getYCoordinate());
			assertEquals(i, wpawn.getXCoordinate());
			assertEquals(1, wpawn.getYCoordinate());
		}
	}
}