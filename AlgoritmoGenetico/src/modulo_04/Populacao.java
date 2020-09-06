package modulo_04;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Populacao {
	private Individuo populacao[];
	private double populacaoFitness = -1;

	public Populacao(int populacaoTamanho) {
		this.populacao = new Individuo[populacaoTamanho];
	}

	public Populacao(int populacaoTamanho, int cromossomoTamanho) {
		this.populacao = new Individuo[populacaoTamanho];

		for (int individuoNum = 0; individuoNum < populacaoTamanho; individuoNum++) {
			Individuo candidato = new Individuo(cromossomoTamanho);
			this.populacao[individuoNum] = candidato;
		}
	}

	public Individuo[] getPopulacao() {
		return this.populacao;
	}

	public Individuo getFitnest(int offset) {
		Arrays.sort(this.populacao, new Comparator<Individuo>() {
			@Override
			public int compare(Individuo o1, Individuo o2) {
				if (o1.getFitness() > o2.getFitness()) {
					return -1;
				} else if (o1.getFitness() < o2.getFitness()) {
					return 1;
				}
				return 0;
			}
		});

		return this.populacao[offset];
	}

	public void setPopulacaoFitness(double fitness) {
		this.populacaoFitness = fitness;
	}

	public double getPopulacaoFitness() {
		return this.populacaoFitness;
	}

	public int populacaoTamanho() {
		return this.populacao.length;
	}

	public Individuo setIndividuo(int offset, Individuo candidato) {
		return populacao[offset] = candidato;
	}

	public Individuo getIndividuo(int offset) {
		return populacao[offset];
	}

	public void embaralharPopulacao() {
		Random rdn = new Random();
		for (int i = populacao.length - 1; i > 0; i--) {
			int index = rdn.nextInt(i + 1);
			Individuo auxCandidato = populacao[index];
			populacao[index] = populacao[i];
			populacao[i] = auxCandidato;
		}

	}

}
