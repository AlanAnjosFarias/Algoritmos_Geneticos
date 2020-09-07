package modulo_04;

public class Individuo {
	private int[] cromossomo;
	private double fitness = -1;

	//construtor #3
	public Individuo(QuadroAula quadroAula) {
		int numAulas = quadroAula.getNumAulas();
		
		// 1 gene para sala, 1 gene para horario, 1 gene para professor
		int cromossomoTamanho = numAulas * 3;
		
		//criar individuos aleatorios
		int novoCromossomo[] = new int[cromossomoTamanho];
		int indexCromossomo = 0;
		
		//loop  pelos grupos
		for(Grupo grupo : quadroAula.getGruposComoArray()) {
			// loop pelos modulos
			for(int moduloID : grupo.getModulosID()) {
				//add horario aleatorio
				int horaUtilID = quadroAula.getHoraUtilAleatoria().getHoraUtilID();
				novoCromossomo[indexCromossomo] = horaUtilID;
				indexCromossomo++;
				
				//add sala aleatoria
				int salaID = quadroAula.getSalaAleatoria().getSalaID();
				novoCromossomo[indexCromossomo] = salaID;
				indexCromossomo++;
				
				//add professor aleatorio
				ModuloCurso modulo = quadroAula.getModuloCurso(moduloID);
				novoCromossomo[indexCromossomo] = modulo.getAleatorioProfessorID();
				indexCromossomo++;
			}
		}
		
		this.cromossomo = novoCromossomo;
	}	
	
	//construtor #2
	public Individuo(int cromossomoTamanho) {
		//criando individuo
		int[] individuo;
		individuo = new int[cromossomoTamanho];
		
		for(int gene = 0; gene < cromossomoTamanho; gene++) {
			individuo[gene] = gene;
		}
		
		this.cromossomo = individuo;			
	}
	
	//construtor #1
	public Individuo(int[] cromossomo) {
		this.cromossomo = cromossomo;
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
			saida += this.cromossomo[gene] + ",";
		}
		return saida;
	}
	
	public boolean geneContido(int gene) {
		for(int i = 0; i < this.getCromossomoTamanho(); i++) {
			if(this.cromossomo[i] == gene) {
				return true;
			}
		}
		return false;
	}

}
