package modulo_02;

public class AlgoritmoGenetico {
	private int populacaoTamanho;
	private double mutacaoTaxa;
	private double cruzamentoTaxa;
	private int numElites;

	protected int torneioTamanho;

	public AlgoritmoGenetico(int populacaoTamanho, double mutacaoTaxa, double cruzamentoTaxa, int elitismo,
			int torneioTamanho) {
		this.populacaoTamanho = populacaoTamanho;
		this.mutacaoTaxa = mutacaoTaxa;
		this.cruzamentoTaxa = cruzamentoTaxa;
		this.numElites = elitismo;
		this.torneioTamanho = torneioTamanho;
	}

	public Populacao iniciarPopulacao(int cromossomoTamanho) {
		Populacao populacao = new Populacao(this.populacaoTamanho, cromossomoTamanho);
		return populacao;
	}

	public double calcFitness(Individuo individuo, Maze maze) {
		int[] cromossomo = individuo.getCromossomo();
		Robo robo = new Robo(cromossomo, maze, 100);
		robo.run();
		int fitness = maze.scoreRota(robo.getRota());
		individuo.setFitness(fitness);
		return fitness;

	}

	public void evolucaoPopulacao(Populacao populacao, Maze maze) {
		double populacaoFitness = 0;
		
		for(Individuo individuo : populacao.getPopulacao()) {
			populacaoFitness += this.calcFitness(individuo, maze);
		}
		
		populacao.setPopulacaoFitness(populacaoFitness);
	}

	public boolean condicaoFinalizar(int geracaoAtual, int maxGeracao) {
		return (geracaoAtual > maxGeracao);
	}

	
	public Individuo selecionaPais(Populacao populacao) {
		// Criar torneio
		Populacao torneio = new Populacao(this.torneioTamanho);
		
		//adicionar individuos aleatorios ao torneio
		populacao.embaralharPopulacao();
		for(int i = 0; i < this.torneioTamanho; i++) {
			Individuo indTorneio = populacao.getIndividuo(i);
			torneio.setIndividuo(i,indTorneio);
		}
		
		//retorna o melhor
		return torneio.getFitnest(0);
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
