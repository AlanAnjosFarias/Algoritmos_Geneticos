package modulo_02;

public class AlgoritmoGenetico {
	private int populacaoTamanho;
	private double mutacaoTaxa;
	private double cruzamentoTaxa;
	private int numElites;

	public AlgoritmoGenetico(int populacaoTamanho, double mutacaoTaxa, double cruzamentoTaxa, int elitismo) {
		this.populacaoTamanho = populacaoTamanho;
		this.mutacaoTaxa = mutacaoTaxa;
		this.cruzamentoTaxa = cruzamentoTaxa;
		this.numElites = elitismo;
	}

	public Populacao iniciarPopulacao(int cromossomoTamanho) {
		Populacao populacao = new Populacao(this.populacaoTamanho, cromossomoTamanho);
		return populacao;
	}

	public double calcFitness(Individuo individuo) {

		
	}

	public void evolucaoPopulacao(Populacao populacao) {
		
	}

	public boolean condicaoFinalizar(Populacao populacao) {
		
	}

	public Individuo selecionaPais(Populacao populacao) {
	}

	public Populacao cruzamentoPopulacao(Populacao populacao) {
	}

	public Populacao mutacaoPopulacao(Populacao populacao) {
		// inicializa nova popuplacao
		Populacao novaPopulacao = new Populacao(this.populacaoTamanho);

		// aplicando mutacao por fitness
		for (int indexPopulacao = 0; indexPopulacao < populacao.populacaoTamanho(); indexPopulacao++) {
			Individuo individuo = populacao.getFitnest(indexPopulacao);

			// pula mutacao para os membros elite
			if (indexPopulacao >= this.numElites) {
				for (int indexGene = 0; indexGene < individuo.getCromossomoTamanho(); indexGene++) {
					if (this.mutacaoTaxa > Math.random()) {
						// obtem novo gene
						int novoGene = 1;
						if (individuo.getGene(indexGene) == 1) {
							novoGene = 0;
						}
						// realiza mutacao
						individuo.setGene(indexGene, novoGene);
					}
				}
			}
			// adiciona individuo a populacao
			novaPopulacao.setIndividuo(indexPopulacao, individuo);
		}

		// retorno nova populacao com mutacao
		return novaPopulacao;
	}

}
