package modulo_03;

public class Individuo {
	private int[] cromossomo;
	private double fitness = -1;

	public Individuo(int[] cromossomo) {
		this.cromossomo = cromossomo;
	}

	public Individuo(int cromossomoTamanho) {
		//criando individuo
		int[] individuo;
		individuo = new int[cromossomoTamanho];
		
		for(int gene = 0; gene < cromossomoTamanho; gene++) {
			individuo[gene] = gene;
		}
		
		this.cromossomo = individuo;			
	}

	public int[] getCromossomo() {
		return this.cromossomo;
	}

	public int getCromossomoTamanho() {
		return this.cromossomo.length;
	}

	public void setGene(int offset, int gene) {
		this.cromossomo[offset] = gene;
	}

	public int getGene(int offset) {
		return this.cromossomo[offset];
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public double getFitness() {
		return this.fitness;
	}

	/**
	 * Apresenta o cromossomo como uma String.
	 * 
	 * @return string representation of the chromosome
	 */

	public String ToString() {
		String saida = "";
		for (int gene = 0; gene < this.cromossomo.length; gene++) {
			saida += this.cromossomo[gene];
		}
		return saida;
	}

}
