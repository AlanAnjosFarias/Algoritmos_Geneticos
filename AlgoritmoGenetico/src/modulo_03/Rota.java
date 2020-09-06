package modulo_03;


public class Rota {
	private Cidade rota[];
	private double distancia = 0;
	
	public Rota(Individuo individuo, Cidade cidades[]) {
		// Obtendo cromossomo dos individuos
		int cromossomo[] =  individuo.getCromossomo();
		
		// criando rota
		this.rota = new Cidade[cidades.length];
		for(int indexGene = 0; indexGene < cromossomo.length; indexGene++) {
			this.rota[indexGene] = cidades[cromossomo[indexGene]];
		}	
		
	}
	
	public double getDistancia() {
		if(this.distancia > 0) {
			return this.distancia;
		}
		
		// calcular distancia
		double distanciaTotal = 0;
		for(int indexCidade = 0; indexCidade + 1 < this.rota.length; indexCidade++ ) {
			distanciaTotal += this.rota[indexCidade].distanciaDe(this.rota[indexCidade + 1]);			
		}
		
		distanciaTotal += this.rota[this.rota.length -1].distanciaDe(this.rota[0]);
		this.distancia = distanciaTotal;
		
		return distanciaTotal;
	}
}
