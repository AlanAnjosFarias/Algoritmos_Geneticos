package modulo_02;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public class Maze {
	private final int maze[][];
	private int posicaoInicial[] = { -1, -1 };

	public Maze(int maze[][]) {
		this.maze = maze;
	}

	/**
	 * Obtenho a posicao inicial do labirinto
	 * 
	 * @return int[] x,y posicao inicial do labirinto
	 */
	public int[] getPosicaoIniciao() {
		// verifica se foi encontrado a posicao inicial
		if (this.posicaoInicial[0] != -1 && this.posicaoInicial[1] != -1) {
			return this.posicaoInicial;
		}

		// retornando valor padrao da posicao inicial
		int auxPosicaoInicial[] = { 0, 0 };

		// loop para linhas
		for (int linha = 0; linha < this.maze[linha].length; linha++) {
			// loop colunas
			for (int coluna = 0; coluna < this.maze[linha].length; coluna++) {
				// 2 é a representacoa para a aposicao inicial em decimal devido a conversao de
				// binario para decimal 11 -> 2
				if (this.maze[linha][coluna] == 2) {
					this.posicaoInicial = new int[] { coluna, linha };
					return new int[] { coluna, linha };
				}

			}

		}
		return auxPosicaoInicial;
	}
	
	/**
	 * Obtem o valro que represena a posicao atual do labirinto
	 * 
	 * @param x  posicao
	 *            
	 * @param y  posicao
	 *            
	 * @return int com os valores da posicao
	 */	
	public int getPosicao(int x, int y) {
		if(x < 0 || y < 0 || x >= this.maze.length || y >= this.maze[0].length ){
			return 1;			
		}
		return this.maze[y][x];
	}
	
	/**
	 * verifica se a posicao passada eh uma parede
	 * 
	 * @param x  posicao
	 *           
	 * @param y  posicao
	 *            
	 * @return boolean
	 */
	public boolean isParede(int x, int y) {
		return (this.getPosicao(x, y) == 1);
	}
	
	/**
	 * Obtem o valor maximo para o index da coluna
	 * x -> coluna
	 * y -> linha
	 * @return int Max index coluna
	 */
	public int getMaxColuna() {
		return this.maze[0].length - 1;
	}
	
	/**
	 * Obtem o valor maximo para o index da Liha
	 * x -> coluna
	 * y -> linha
	 * @return int Max index linha
	 */
	public int getMaxLinha() {
		return this.maze.length - 1;
	}
	
	/**
	 * Scores a maze route
	 * 
	 * This method inspects a route given as an array, and adds a point for each
	 * correct step made. We also have to be careful not to reward re-visiting
	 * correct paths, otherwise you could get an infinite score just by wiggling
	 * back and forth on the route.
	 * 
	 * @return int Max index
	 */
	

}
