package modulo_02;

import java.util.ArrayList;

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
		for (int linha = 0; linha < this.maze.length; linha++) {
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
	 * @param x posicao
	 * 
	 * @param y posicao
	 * 
	 * @return int com os valores da posicao
	 */
	public int getPosicao(int x, int y) {
		if (x < 0 || y < 0 || x >= this.maze.length || y >= this.maze[0].length) {
			return 1;
		}
		return this.maze[y][x];
	}

	/**
	 * verifica se a posicao passada eh uma parede
	 * 
	 * @param x posicao
	 * 
	 * @param y posicao
	 * 
	 * @return boolean
	 */
	public boolean isParede(int x, int y) {
		return (this.getPosicao(x, y) == 1);
	}

	/**
	 * Obtem o valor maximo para o index da coluna x -> coluna y -> linha
	 * 
	 * @return int Max index coluna
	 */
	public int getMaxColuna() {
		return this.maze[0].length - 1;
	}

	/**
	 * Obtem o valor maximo para o index da Liha x -> coluna y -> linha
	 * 
	 * @return int Max index linha
	 */
	public int getMaxLinha() {
		return this.maze.length - 1;
	}

	/**
	 * crie rank/score para a rota do labirinto
	 * 
	 * Metodo para inspecionar uma rota e pontua cada etapa realizada corretamente
	 * Tomar cuidado para potuar novamente lugares ja visitados
	 * 
	 * @return int Max index
	 */
	public int scoreRota(ArrayList<int[]> rota) {
		int score = 0;
		boolean visitado[][] = new boolean[this.getMaxLinha() + 1][this.getMaxColuna() + 1];

		// loop para percorrer a rota e pontuar cada movimento
		for (Object passoRota : rota) {
			int passo[] = (int[]) passoRota;
			if ((this.maze[passo[1]][passo[0]] == 3 || this.maze[passo[1]][passo[0]] == 4) && visitado[passo[1]][passo[0]] == false) {
				// incremente um score para o movimentro
				score++;
				// marque o caminho como visitado para nao atribuir novamente o score
				visitado[passo[1]][passo[0]] = true;
			}
		}
		
		return score;
	}

}
