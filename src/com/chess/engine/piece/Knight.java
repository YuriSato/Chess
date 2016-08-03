package com.chess.engine.piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board1.Board;
import com.chess.engine.board1.BoardUtils;
import com.chess.engine.board1.Move;
import com.chess.engine.board1.Tile;
import com.google.common.collect.ImmutableList;

import static com.chess.engine.board1.Move.*;

public class Knight extends Piece {
	
	private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};

	Knight(int piecePosition, Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
		// TODO Auto-generated constructor stub
	}

	public Collection<Move> calculateLegalMoves(final Board board) {

		final List<Move> legalMoves = new ArrayList<>();
		
		for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES){
			
			final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
			
			if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
				
				if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) || 
						isSecondColumnExclusion(this.piecePosition, currentCandidateOffset) || 
							isSeventhColumnExclusion(this.piecePosition, currentCandidateOffset) ||
								isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)){
					continue;
				}
				
				final Tile candidateDestinationTile = 
						board.getTile(candidateDestinationCoordinate);
				
				if(!candidateDestinationTile.isTileOccupied()){
					legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
				}else{
					final Piece pieceAtDestination = candidateDestinationTile.getPiece();
					final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
					
					if(this.pieceAlliance != pieceAlliance){
						legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
					}
				}	
			}	
		}		
		return ImmutableList.copyOf(legalMoves);
	}	
	
	private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
		return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -17 || candidateOffset == -10 
				|| candidateOffset == 6 || candidateOffset == 15);
	}
	
	private static boolean isSecondColumnExclusion(final int currentPostion, final int candidateOffset){
		return BoardUtils.SECOND_COLUMN[currentPostion] && (candidateOffset == -10 || candidateOffset == 6);
	}
	
	private static boolean isSeventhColumnExclusion(final int currentPostion, final int candidateOffset){
		return BoardUtils.SEVENTH_COLUMN[currentPostion] && (candidateOffset == -6 || candidateOffset == 10);
	}
	private static boolean isEighthColumnExclusion(final int currentPostion, final int candidateOffset){
		return BoardUtils.EIGHTH_COLUMN[currentPostion] && (candidateOffset == -15 || candidateOffset == -6
				|| candidateOffset == 10 || candidateOffset == 17);
	}
}
