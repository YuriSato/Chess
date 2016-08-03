package com.chess.engine.piece;


import com.chess.engine.board1.Board;
import com.chess.engine.board1.Move;

import java.util.Collection;

import com.chess.engine.Alliance;

public abstract class Piece{	

	protected final int piecePosition;
	protected final Alliance pieceAlliance;

	Piece(final int piecePosition, final Alliance pieceAlliance){
	this.pieceAlliance = pieceAlliance;
	this.piecePosition = piecePosition;
	}
	public Alliance getPieceAlliance(){
		return this.pieceAlliance;
	}
	
	public abstract Collection<Move> calculateLegalMoves(final Board board);

}
