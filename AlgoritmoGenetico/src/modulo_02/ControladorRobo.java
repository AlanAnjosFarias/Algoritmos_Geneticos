package modulo_02;

public class ControladorRobo {

	public static int maxGeracoes = 1000;
	
	public static void main(String[] args) {
		 
		/**
		 * Iniciazano o labirinto manualmente
		 * 
		 * Legenda 
		 * 0 = Vazio 
		 * 1 = Parede
		 * 2 = Posicao Incial
		 * 3 = melhor Rota
		 * 4 = Posicao de chegada
		 */
		
		Maze maze = new Maze(new int[][] {
			{ 0, 0, 0, 0, 1, 0, 1, 3, 2 }, 
			{ 1, 0, 1, 1, 1, 0, 1, 3, 1 },
			{ 1, 0, 0, 1, 3, 3, 3, 3, 1 }, 
			{ 3, 3, 3, 1, 3, 1, 1, 0, 1 }, 
			{ 3, 1, 3, 3, 3, 1, 1, 0, 0 },
			{ 3, 3, 1, 1, 1, 1, 0, 1, 1 }, 
			{ 1, 3, 0, 1, 3, 3, 3, 3, 3 }, 
			{ 0, 3, 1, 1, 3, 1, 0, 1, 3 },
			{ 1, 3, 3, 3, 3, 1, 1, 1, 4 } 
		});
		

	}

}
