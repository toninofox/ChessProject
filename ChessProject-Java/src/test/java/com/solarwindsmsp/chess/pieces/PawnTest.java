package com.solarwindsmsp.chess.pieces;

import org.junit.Before;
import org.junit.Test;

import com.solarwindsmsp.chess.ChessBoard;
import com.solarwindsmsp.chess.MovementType;
import com.solarwindsmsp.chess.PieceColor;
import com.solarwindsmsp.chess.pieces.Pawn;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

public class PawnTest {

	private ChessBoard chessBoard;
	private Pawn blackTestSubject;
	private Pawn whiteTestSubject;

	@Before
	public void setUp() {
		this.chessBoard = new ChessBoard();
		this.blackTestSubject = new Pawn(PieceColor.BLACK);
		this.whiteTestSubject = new Pawn(PieceColor.WHITE);
	}

	@Test
	public void testChessBoard_Add_Sets_XCoordinate() {
		this.chessBoard.Add(blackTestSubject, 6, 3);
		assertEquals(6, blackTestSubject.getXCoordinate());
	}

	@Test
	public void testChessBoard_Add_Sets_YCoordinate() {
		this.chessBoard.Add(blackTestSubject, 6, 3);
		assertEquals(3, blackTestSubject.getYCoordinate());
	}

	@Test
	public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
		chessBoard.Add(blackTestSubject, 6, 3);
		blackTestSubject.Move(MovementType.MOVE, 7, 3);
		assertEquals(6, blackTestSubject.getXCoordinate());
		assertEquals(3, blackTestSubject.getYCoordinate());
	}

	@Test
	public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
		chessBoard.Add(blackTestSubject, 6, 3);
		blackTestSubject.Move(MovementType.MOVE, 4, 3);
		assertEquals(6, blackTestSubject.getXCoordinate());
		assertEquals(3, blackTestSubject.getYCoordinate());
	}

	@Test
	public void testPawn_Move_IllegalCoordinates_ObliqueLeft_DoesNotMove() {
		chessBoard.Add(blackTestSubject, 6, 3);
		blackTestSubject.Move(MovementType.MOVE, 5, 2);
		assertEquals(6, blackTestSubject.getXCoordinate());
		assertEquals(3, blackTestSubject.getYCoordinate());
	}

	@Test
	public void testPawn_Move_IllegalCoordinates_ObliqueRight_DoesNotMove() {
		chessBoard.Add(blackTestSubject, 6, 3);
		blackTestSubject.Move(MovementType.MOVE, 5, 3);
		assertEquals(6, blackTestSubject.getXCoordinate());
		assertEquals(3, blackTestSubject.getYCoordinate());
	}

	@Test
	public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
		chessBoard.Add(blackTestSubject, 6, 3);
		blackTestSubject.Move(MovementType.MOVE, 6, 2);
		assertEquals(6, blackTestSubject.getXCoordinate());
		assertEquals(2, blackTestSubject.getYCoordinate());
	}

	@Test
	public void theMaxAllowedPawnsAre8() throws Exception {
		assertThat(blackTestSubject.getMaxNumberOfPiecesAllowed(), is(8));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void movementCaptureIsNotAllowed() throws Exception {
		blackTestSubject.Move(MovementType.CAPTURE, 6, 2);

	}

	@Test
	public void InvalidCoordinateShouldBeConsideredWithInvalidCoordinate() throws Exception {
		chessBoard.Add(blackTestSubject, -1, -1);
		assertThat(blackTestSubject.isAddedOnTheBoard(), is(false));
	}

	@Test
	public void blackPawnFromInitialPositionCanBemovedOfTwo() throws Exception {
		chessBoard.Add(blackTestSubject, 6, 6);
		blackTestSubject.Move(MovementType.MOVE, 6, 4);
		assertEquals(6, blackTestSubject.getXCoordinate());
		assertEquals(4, blackTestSubject.getYCoordinate());
	}
	
	@Test
	public void blackPawnFromInitialPositionCanBemovedOfOne() throws Exception {
		chessBoard.Add(blackTestSubject, 6, 6);
		blackTestSubject.Move(MovementType.MOVE, 6, 5);
		assertEquals(6, blackTestSubject.getXCoordinate());
		assertEquals(5, blackTestSubject.getYCoordinate());
	}

	@Test
	public void blackPawnNotFromInitialPositionCannotBemovedOfTwo() throws Exception {
		chessBoard.Add(blackTestSubject, 6, 3);
		blackTestSubject.Move(MovementType.MOVE, 6, 1);
		assertEquals(6, blackTestSubject.getXCoordinate());
		assertEquals(3, blackTestSubject.getYCoordinate());
	}

	@Test
	public void whitePawnFromInitialPositionCanBemovedOfTwo() throws Exception {
		chessBoard.Add(whiteTestSubject, 4, 1);
		whiteTestSubject.Move(MovementType.MOVE, 4, 3);
		assertEquals(4, whiteTestSubject.getXCoordinate());
		assertEquals(3, whiteTestSubject.getYCoordinate());
	}
	@Test
	public void whitePawnFromInitialPositionCanBemovedOfOne() throws Exception {
		chessBoard.Add(whiteTestSubject, 4, 1);
		whiteTestSubject.Move(MovementType.MOVE, 4, 2);
		assertEquals(4, whiteTestSubject.getXCoordinate());
		assertEquals(2, whiteTestSubject.getYCoordinate());
	}

	@Test
	public void whitePawnNotFromInitialPositionCannotBemovedOfTwo() throws Exception {
		chessBoard.Add(whiteTestSubject, 1, 3);
		whiteTestSubject.Move(MovementType.MOVE, 1, 5);
		assertEquals(1, whiteTestSubject.getXCoordinate());
		assertEquals(3, whiteTestSubject.getYCoordinate());
	}

}