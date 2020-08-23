package modulo_01;

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

		double genesCorretos = 0;

		for (int indexGene = 0; indexGene < individuo.getCromossomoTamanho(); indexGene++) {
			if (individuo.getGene(indexGene) == 1) {
				genesCorretos += 1;
			}
		}

		double fitness = genesCorretos / individuo.getCromossomoTamanho();

		individuo.setFitness(fitness);

		return fitness;
	}

	public void evolucaoPopulacao(Populacao populacao) {
		double populacaoFitness = 0;

		for (Individuo individuo : populacao.getPopulacao()) {
			populacaoFitness += calcFitness(individuo);
		}

		populacao.setPopulacaoFitness(populacaoFitness);
	}

	public boolean condicaoFinalizar(Populacao populacao) {
		for (Individuo individuo : populacao.getPopulacao()) {
			if (individuo.getFitness() == 1) {
				return true;
			}
		}
		return false;
	}

	public Individuo selecionaPais(Populacao populacao) {
		// obtendo individuos
		Individuo individuos[] = populacao.getPopulacao();

		// rodando roleta para selecionar
		double populacaoFitness = populacao.getPopulacaoFitness();
		double roletaPosicao = Math.random() * populacaoFitness;

		// encontrado pais
		double roleta = 0;
		for (Individuo individuo : individuos) {
			roleta += individuo.getFitness();
			if (roleta >= roletaPosicao) {
				return individuo;
			}
		}
		return individuos[populacao.populacaoTamanho() - 1];
	}

	public Populacao cruzamentoPopulacao(Populacao populacao) {
		// criar nova populacao
		Populacao novaPopulacao = new Populacao(populacao.populacaoTamanho());

		for (int indexPopulacao = 0; indexPopulacao < populacao.populacaoTamanho(); indexPopulacao++) {

			Individuo pai = populacao.getFitnest(indexPopulacao);

			// aplicando cruzamento
			if (this.cruzamentoTaxa > Math.random() && indexPopulacao >= this.numElites) {

				// inicializa ancestral
				Individuo ancestral = new Individuo(pai.getCromossomoTamanho());

				// encontra o individuo mae
				Individuo mae = selecionaPais(populacao);

				// loop para cruzamento de genoma
				for (int indexGene = 0; indexGene < pai.getCromossomoTamanho(); indexGene++) {
					// usa metade do cromossomo do pai e metada da mae
					if (0.5 > Math.random()) {
						ancestral.setGene(indexGene, pai.getGene(indexGene));
					} else {
						ancestral.setGene(indexGene, mae.getGene(indexGene));
					}
				}
				novaPopulacao.setIndividuo(indexPopulacao, ancestral);

			} else {
				novaPopulacao.setIndividuo(indexPopulacao, pai);
			}
		}

		return novaPopulacao;
	}

	public Populacao mutacaoPopulacao(Populacao populacao) {
		// inicializa nova popuplacao
		Populacao novaPopulacao = new Populacao(this.populacaoTamanho);

		// aplicando mutacao por fitness
		for (int indexPopulacao = 0; indexPopulacao < populacao.populacaoTamanho(); indexPopulacao++) {
			Individuo individuo = populacao.getFitnest(indexPopulacao);

			for (int indexGene = 0; indexGene < individuo.getCromossomoTamanho(); indexGene++) {
				// pula mutacao para os membros elite
				if (indexPopulacao >= this.numElites) {
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
