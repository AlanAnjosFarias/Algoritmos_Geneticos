package modulo_04;

public class Sala {
	private final int salaID;
	private final String salaNumero;
	private final int capacidade;
	
	public Sala(int salaID, String salaNumero, int capacidade) {
		this.salaID = salaID;
		this.salaNumero = salaNumero;
		this.capacidade = capacidade;
	}
	
	public int getSalaID() {
		return this.salaID;
	}
	
	public String getSalaNumero(){
		return this.salaNumero;
	}
	
	public int getCapacidade() {
		return this.capacidade;
	}
	

}
