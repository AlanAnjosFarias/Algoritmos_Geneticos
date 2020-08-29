package modulo_01;

public class TodosUm {

	public static void main(String[] args) {
		// Criar objeto do algoritmo genetico
		AlgoritmoGenetico ag = new AlgoritmoGenetico(100, 0.001, 0.95, 3);

		// criar a populacao do algoritomo
		Populacao populacao = ag.iniciarPopulacao(180);

		// evolucao da populacao
		ag.evolucaoPopulacao(populacao);

		// iniciando com a primeira geracao
		int geracao = 1;

		while (ag.condicaoFinalizar(populacao) == false) {
			// imiprimi o melhor individuo da populacao atual
			System.out.println("Melhor Genoma: " + populacao.getFitnest(0).ToString());

			// cruzamento
			populacao = ag.cruzamentoPopulacao(populacao);

			// mutacao
			populacao = ag.mutacaoPopulacao(populacao);

			// evolucao
			ag.evolucaoPopulacao(populacao);

			// geracao
			geracao++;
		}

		System.out.println("Genoma alcançado após: " + geracao + " gerações");
		System.out.println("Genoma: " + populacao.getFitnest(0).ToString());

	}
}
