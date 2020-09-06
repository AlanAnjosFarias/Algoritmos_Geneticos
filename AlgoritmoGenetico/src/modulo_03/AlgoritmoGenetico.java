package modulo_03;

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

	public double calcFitness(Individuo individuo, Cidade cidades[]) {
		// obtendo fitness
		Rota rota = new Rota(individuo, cidades);
		double fitness = 1 / rota.getDistancia();
		
		//armazenar valor de fitness
		individuo.setFitness(fitness);
		
		return fitness;
	}

	public void evolucaoPopulacao(Populacao populacao, Cidade cidades[]) {
		double populacaoFitness = 0;
		
		// evoluir populacao calculando fitness
		for(Individuo auxIndividuo : populacao.getPopulacao()) {
			populacaoFitness += this.calcFitness(auxIndividuo, cidades);
		}
		
		double mediaFit = populacaoFitness / populacao.populacaoTamanho();
		populacao.setPopulacaoFitness(mediaFit);
	}

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
		// cria nova populacao gerada apos cruzamento
		Populacao novaPopulacao = new Populacao(populacao.populacaoTamanho());

		
		return novaPopulacao;
	}

	public Populacao mutacaoPopulacao(Populacao populacao) {
		// inicializa nova popuplacao
		Populacao novaPopulacao = new Populacao(this.populacaoTamanho);

		
		return novaPopulacao;
	}

}
