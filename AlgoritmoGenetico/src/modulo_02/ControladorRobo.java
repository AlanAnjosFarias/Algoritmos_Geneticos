package modulo_02;

public class ControladorRobo {

	public static int maxGeracao = 1000;
	
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
		
		//criar algoritmo genetico
		AlgoritmoGenetico ag = new AlgoritmoGenetico(200, 0.05, 0.9, 2, 10);
		
		//criar populacao
		Populacao populacao = ag.iniciarPopulacao(128);
		
		// calcula score da populacao
		ag.evolucaoPopulacao(populacao, maze);
		
		//inicia geracao
		int geracao = 1;
		
		//inicia loop genetico
		while(ag.condicaoFinalizar(geracao, maxGeracao) == false) {
			// print melhor individuo da populacao
			Individuo melhorIndividuo = populacao.getFitnest(0);
			System.out.println("Geração: " + geracao + " Melhor solucao (" + melhorIndividuo.getFitness() + "): " + melhorIndividuo.ToString() );
			
			// realizar cruzamento
			
			// realizar mutacao
			
			// calcular score da populacao/individuo
			ag.evolucaoPopulacao(populacao, maze);
			
			// incremento o aumento da geracao
			geracao++;
		}
		
		// imprimir resultado alcancado
		

	}

}
