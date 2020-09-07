package modulo_04;

import java.util.Arrays;


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
	
	// inicializar populacao
	public Populacao inicializarPopulacao(QuadroAula quadroAula) {
		// inicializa populacao
		Populacao populacao = new Populacao(this.populacaoTamanho, quadroAula);
		return populacao;
	}	

	//fitness
	public double calcFitness(Individuo individuo, QuadroAula quadroAula) {
		
		// criar um novo quadro de aula clonando de um existente
		QuadroAula quadroAulaClone = new QuadroAula(quadroAula);
		quadroAulaClone.criarAula(individuo);
		
		//calcular fitness
		int confrontos = quadroAulaClone.calcConfrontos();
		double fitness = 1 / ((double) (confrontos + 1));
		
		individuo.setFitness(fitness);
		
		return fitness;
		
	}

	//evolucao populacao
	public void evolucaoPopulacao(Populacao populacao, QuadroAula quadroAula) {
		double populacaoFitness = 0;
		
		//loop pelo fitness dos individuos da populacao e realizando sua soma
		//fitness
		for(Individuo individuo : populacao.getPopulacao()) {
			populacaoFitness += this.calcFitness(individuo, quadroAula);
		}
		
		populacao.setPopulacaoFitness(populacaoFitness);
	}

	
	//verificar finalizacao #1
	public boolean condicaoFinalizar(int geracaoAtual, int maxGeracao) {
		return (geracaoAtual > maxGeracao);
	}
	
	//verificar finalizacao #2
	public boolean condicaoFinalizar(Populacao populacao) {
		return populacao.getFitnest(0).getFitness() == 1.0;
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
	public Populacao mutacaoPopulacao(Populacao populacao, QuadroAula quadroAula) {
		//inicializa nova populacao
		Populacao novaPopulacao = new Populacao(this.populacaoTamanho);
		
		//percorre toda populacao pelo seu fitness
		for(int indexPopulacao = 0; indexPopulacao < populacao.populacaoTamanho(); indexPopulacao++) {
			Individuo individuo = populacao.getFitnest(indexPopulacao);
			
			//cria um individuo aleatorio para trocar os genes
			Individuo individuoAleatorio = new Individuo(quadroAula);
			
			//loop pelos genes dos individuos
			for(int indexGene = 0; indexGene < individuo.getCromossomoTamanho(); indexGene++) {
				//pula mutacao no caso do individuo ser Elite
				if(indexPopulacao > this.numElites) {
					//verifica se o gene sera mutado
					if(this.mutacaoTaxa > Math.random()) {
						//troca para o novo gene
						individuo.setGene(indexGene, individuoAleatorio.getGene(indexGene));
					}
				}
			}
			
			//add o individuo a populacao
			novaPopulacao.setIndividuo(indexPopulacao, individuo);
		}
		
		//retorna populacao com mutacao
		return novaPopulacao;
	}

}
