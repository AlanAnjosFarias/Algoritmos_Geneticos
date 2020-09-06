package modulo_03;

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
		
		//
		for(int indexPop = 0; indexPop < populacao.populacaoTamanho(); indexPop++) {
			// obtendo pai
			Individuo pai = populacao.getFitnest(indexPop);
			
			// aplicando cruzamento individual
			if(this.cruzamentoTaxa > Math.random() && indexPop >= this.numElites) {
				// encontre a mae com modo de selecao torneio
				Individuo mae = this.selecionaPais(populacao);
				
				//criando cromossomo em branco do filho
				int cromossomoFilho[] = new int[pai.getCromossomoTamanho()];
				Arrays.fill(cromossomoFilho, -1);
				Individuo filho = new Individuo(cromossomoFilho);
				
				//Obtendo as posicoes dos subset dos cromossomos do pai
				int subset1 = (int)(Math.random() * pai.getCromossomoTamanho());
				int subset2 = (int)(Math.random() * pai.getCromossomoTamanho());
				
				// mazendo os incios e fim do cromossomo
				final int inicioSubset = Math.min(subset1, subset2);
				final int fimSubset = Math.max(subset1, subset2);
				
				//loop para addionar os subset ao torneios do pai para os filho
				for(int i = inicioSubset; i < fimSubset; i++) {
					filho.setGene(i, pai.getGene(i));
				}
				
				//loop para percorrer mae
				for(int i = 0; i < mae.getCromossomoTamanho(); i++) {
					int geneMae = i + fimSubset;
					if(geneMae >= mae.getCromossomoTamanho()) {
						geneMae -= mae.getCromossomoTamanho();
					}
					
					// se o filho ainda nao visitou a cidade
					if(filho.geneContido(mae.getGene(geneMae)) ==  false) {
						//loop para encontrar uma posicao vazia no cromossomo
						for(int ii = 0; ii < filho.getCromossomoTamanho(); ii++) {
							// se encontrar um espaco adicione a cidade
							if(filho.getGene(ii) == -1) {
								filho.setGene(ii, mae.getGene(geneMae));
								break;
							}
						}
					}
				}
				
				// adicione o filho
				novaPopulacao.setIndividuo(indexPop, filho);
			} else {
				//add individuo a populcao sem cruzamento
				novaPopulacao.setIndividuo(indexPop, pai);
			}
			
		}

		
		return novaPopulacao;
	}

	public Populacao mutacaoPopulacao(Populacao populacao) {
		// inicializa nova popuplacao
		Populacao novaPopulacao = new Populacao(this.populacaoTamanho);
		
		// percorrer individuos 
		for(int indexPop = 0; indexPop < populacao.populacaoTamanho(); indexPop++) {
			Individuo auxIndividuo = populacao.getFitnest(indexPop);
			
			// pula os elites
			if(indexPop >= this.numElites) {
				for(int indexGene = 0; indexGene < auxIndividuo.getCromossomoTamanho(); indexGene++) {
					// verifica se o gene ira sofrer mutacao
					if(this.mutacaoTaxa > Math.random()) {
						// obtem a nova posicao do gene
						int novoGenePos = (int) (Math.random() * auxIndividuo.getCromossomoTamanho());
						
						//  gene para troca
						int gene1 = auxIndividuo.getGene(novoGenePos);
						int gene2 = auxIndividuo.getGene(indexGene);
						
						// realiza troca
						auxIndividuo.setGene(indexGene, gene1);
						auxIndividuo.setGene(novoGenePos, gene2);
						
					}
				}
			}
			
			// adciona individuo a populacao
			novaPopulacao.setIndividuo(indexPop, auxIndividuo);
		}

		//retorno populacao com mutacao
		return novaPopulacao;
	}

}
