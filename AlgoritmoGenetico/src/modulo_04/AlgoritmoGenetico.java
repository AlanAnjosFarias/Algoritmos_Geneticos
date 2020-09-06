package modulo_04;

import java.util.Arrays;

import modulo_02.Individuo;
import modulo_02.Populacao;

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

	// inicar populacao
	//public Populacao iniciarPopulacao(int cromossomoTamanho) 
	

	//fitness
	//public double calcFitness(Individuo individuo, Cidade cidades[]) 

	//evolucao populacao
	//public void evolucaoPopulacao(Populacao populacao, Cidade cidades[])

	public boolean condicaoFinalizar(int geracaoAtual, int maxGeracao) {
		return (geracaoAtual > maxGeracao);
	}

	public Individuo selecionaPais(Populacao populacao) {
		// Criar torneio
		Populacao torneio = new Populacao(this.torneioTamanho);

		// adicionar individuos aleatorios ao torneio
		populacao.embaralharPopulacao();
		for (int i = 0; i < this.torneioTamanho; i++) {
			Individuo indTorneio = populacao.getIndividuo(i);
			torneio.setIndividuo(i, indTorneio);
		}

		// retorna o melhor
		return torneio.getFitnest(0);
	}

	public Populacao cruzamentoPopulacao(Populacao populacao) {
		// criar nova populacao
		Populacao novaPopulacao = new Populacao(populacao.populacaoTamanho());

		for (int indexPopulacao = 0; indexPopulacao < populacao.populacaoTamanho(); indexPopulacao++) {

			Individuo pai = populacao.getFitnest(indexPopulacao);

			// aplicando cruzamento
			if (this.cruzamentoTaxa > Math.random() && indexPopulacao >= this.numElites) {

				// inicializa filho
				Individuo filho = new Individuo(pai.getCromossomoTamanho());

				// encontra o individuo mae
				Individuo mae = selecionaPais(populacao);

				// loop para cruzamento de genoma
				for (int indexGene = 0; indexGene < pai.getCromossomoTamanho(); indexGene++) {
					// usa metade do cromossomo do pai e metada da mae
					if (0.5 > Math.random()) {
						filho.setGene(indexGene, pai.getGene(indexGene));
					} else {
						filho.setGene(indexGene, mae.getGene(indexGene));
					}
				}
				novaPopulacao.setIndividuo(indexPopulacao, filho);

			} else {
				novaPopulacao.setIndividuo(indexPopulacao, pai);
			}
		}

		return novaPopulacao;
	}

	// mutacao
	//public Populacao mutacaoPopulacao(Populacao populacao) 

}
