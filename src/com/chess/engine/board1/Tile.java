package com.chess.engine.board1;

import java.util.HashMap;
import java.util.Map;
import javax.management.ImmutableDescriptor;
import com.chess.engine.piece.Piece;
import com.google.common.collect.ImmutableMap;

/**
 * Essa classe cuida das casas do tabuleiro de xadrez
 * podendo estar vazia ou não
 * @author ari
 *
 */

public abstract class Tile {
	/**
	 * Tile é uma classe abstrata e final onde suas constantes estão protegidas e não podem ser alteradas
	 * 
	 */
	protected final int tileCoordinate;	
	private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();	
	/**
	 * A Função cria todas as possibilidades de casas no tabuleiro e armazena em um Map
	 * onde cada casa vai do numero 0-63
	 * @return uma casa do tabuleiro
	 */
	private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles(){
		
		final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
		
		for(int i = 0; i < BoardUtils.NUM_TILES; i++){
			emptyTileMap.put(i, new EmptyTile(i));
		}
		return ImmutableMap.copyOf(emptyTileMap);
		//fim do método createAllPossibleEmptyTiles
		//ImmutableMap vem de uma biblioteca externa com.google.common.collect.ImmutableMap
	}
	/**
	 * 
	 * @param tileCoordinate local da casa
	 * @param piece peça que está a ocupando
	 * @return verifica se a casa está ou não ocupada
	 */
	public static Tile createTile(final int tileCoordinate, final Piece piece){
		return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
	}
		/**
		 * seta o local da casa
		 * @param tileCoordinate local da casa
		 */
	private Tile(final int tileCoordinate){
		this.tileCoordinate = tileCoordinate;
	}
	
	public abstract boolean isTileOccupied();	
	public abstract Piece getPiece();
	/**
	 * Classe extendida de Tile que retorna que a casa está vazia
	 *
	 */
	public static final class EmptyTile extends Tile{
		private EmptyTile(final int coordinate){
			super(coordinate);
		}
		
		@Override
		public boolean isTileOccupied(){
			return false;
		}
		
		@Override
		public Piece getPiece(){
			return null;
		}
	}
	/**
	 * 
	 *Classe extendida de Tile que diz que a classe está ocupada
	 *
	 */
	public static final class OccupiedTile extends Tile{
		
		private final Piece pieceOnTile;
		/**
		 * 
		 * @param tileCoordinate
		 * @param pieceOnTile
		 */
		private OccupiedTile(int tileCoordinate,final Piece pieceOnTile) {
			super(tileCoordinate);
			this.pieceOnTile = pieceOnTile;
		}
		@Override
		public boolean isTileOccupied(){
			return true;
		}
		@Override
		public Piece getPiece(){
			return this.pieceOnTile;
		}
		
		}
	
	
}
