package modulo_03;

public class TSM {
	public static int maxGeracoes = 3000;
	
	public static void main(String[] args) {
		int numCidades = 100;
		Cidade cidades[] = new Cidade[numCidades];
		
		//criando cidades
		for(int indexCid = 0; indexCid < numCidades; indexCid++) {
			int xPos = (int)(100*Math.random());
			int yPos = (int)(100*Math.random());
			cidades[indexCid] = new Cidade(xPos, yPos);
		}
		
		//inicianlizano algoritmo genetico
		AlgoritmoGenetico ag = new AlgoritmoGenetico(100, 0.001, 0.9, 2, 5);
		
		//inicializar populacao
		Populacao populacao = ag.iniciarPopulacao(cidades.length);
		
		//Evoluir populacao
		ag.evolucaoPopulacao(populacao, cidades);
		
		
		//iniciar primeira geracao
		int geracaoAtual = 1;
		
		// iniciar loop de evolucao
		while(ag.condicaoFinalizar(geracaoAtual, maxGeracoes) == false) {
			// imprimir o melhor individuo da populacao
			Rota rota = new Rota(populacao.getFitnest(0), cidades);
			System.out.println("G" + geracaoAtual + " melhor distancia: " + rota.getDistancia());
			// Aplicar cruzamento
			
			// Aplicar mutacao
			
			// Calcular Evolucao da populacao
			ag.evolucaoPopulacao(populacao, cidades);
			
			// Incrementar geracao
			geracaoAtual++;
		}
		
		// Apresentar resultados
		System.out.println("Parado após " + maxGeracoes + " geracoes.");
		Rota rota = new Rota(populacao.getFitnest(0), cidades);
		System.out.println("Melhor distancia: " + rota.getDistancia());

	}

}
