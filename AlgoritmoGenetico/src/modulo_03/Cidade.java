package modulo_03;

public class Cidade {
	private int x;
	private int y;
	
	public Cidade(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public double distanciaDe(Cidade cidade) {
		//referencia das posicoes das cidades
		double deltaX = Math.pow((cidade.getX() - this.getX()), 2);
		double deltaY = Math.pow((cidade.getY() - this.getY()), 2);
		
		//calculando menor caminho
		double distancia = Math.sqrt(Math.abs(deltaX + deltaY));
		return distancia;		
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

}
