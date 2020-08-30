package modulo_02;

import java.util.ArrayList;

public class Robo {
	private enum Direcao {NORTE, LESTE, SUL, OESTE};
	
	private int xPosicao;
	private int yPosicao;
	private Direcao heading;
	int maxMovimento;
	int movimento;
	private int valorSensor;
	private final int acaoSensor[];
	private Maze maze;
	private ArrayList<int[]> rota;
	
	
	/**
     * Inicializando o robo com controlador
     * 
     * 
     * @param acaoSensor		responsavel para mapear os valores dos sensores em acoes
     * @param maze 				labirinto usado pelo robo
     * @param maxMoves 			numero maximo de moviemnto que o robo deve fazer
     */
	public Robo(int[] acaoSensor, Maze maze, int maxMovimento) {
		this.acaoSensor = this.calcAcaoSensor(acaoSensor);
		this.maze = maze;
		int posicaoInicial[] = this.maze.getPosicaoIniciao();
		this.xPosicao = posicaoInicial[0];
		this.yPosicao = posicaoInicial[1];
		this.valorSensor = -1;
		this.heading = Direcao.LESTE;
		this.maxMovimento = maxMovimento;
		this.movimento = 0;
		this.rota = new ArrayList<int[]>();
		this.rota.add(posicaoInicial);
	}
	
	 /**
     * 
     * Executa as acoes do robo baseado nas entradas dos sensores
     * 
     */
	public void run() {
		while(true) {
			this.movimento++;
			
			// interrompe loop caso o robo pare de se movimentar
			if(this.getProximaAcao() == 0) {
				return;
			}
			
			// interrompe loop caso o robo encontro o objetivo
			if(this.maze.getPosicao(this.xPosicao, this.yPosicao) == 4) {
				return;
			}
			
			// interrompe loop caso o robo realize o numero maximo de movimentos
			if(this.movimento > this.maxMovimento) {
				return;
			}
			
			// Executa acao
			this.facaProximaAcao();
		}
	}
	
	/**
	 * 
	 * Mapeia os dados dos sensores do robo para uma palavra binaria 
     * 
     * @param palavraAcaoSensor 	cromossomo binario das acoes
     * @return int[] 				Um array pra mapear os valores dos sensor para traduzir em acoes
     */
	private int[] calcAcaoSensor(int[] palavraAcaoSensor) {
		// Quantas acoes existem?
		int numAcao = (int) palavraAcaoSensor.length / 2;
		int acaoSensor[] =  new int[numAcao];
		
		//loop para percorrer as acoes
		for(int valorSensor = 0; valorSensor < numAcao; valorSensor++) {
			//obtem a acao do sensor
			int auxAcaoSensor = 0;
			if(palavraAcaoSensor[valorSensor*2] == 1) {
				auxAcaoSensor += 2;
			}
			if(palavraAcaoSensor[(valorSensor*2)+1] == 1) {
				auxAcaoSensor += 1;
			}
			
			// adiciona as acoes no mapeamento
			acaoSensor[valorSensor] = auxAcaoSensor;
		}
		return acaoSensor;
	}
	
	/**
	 * 
     * Execute a proxima acao
     * 
     */
	public void facaProximaAcao() {
		// Se o movimento for para frente
		if(this.getProximaAcao() == 1) {
			int atualX = this.xPosicao;
			int atualY = this.yPosicao;
			
			// Se move dependendo da direcao atual
			if(Direcao.NORTE == this.heading) {
				this.yPosicao += 1;
				if(this.yPosicao < 0 ) {
					this.yPosicao = 0;
				}
			}
			else if(Direcao.LESTE == this.heading) {
				this.xPosicao += 1;
				if(this.xPosicao > this.maze.getMaxColuna()) {
					this.xPosicao = this.maze.getMaxColuna();
				}
			}
			else if(Direcao.SUL == this.heading) {
				this.yPosicao += 1;
				if(this.yPosicao > this.maze.getMaxLinha()) {
					this.yPosicao = this.maze.getMaxLinha();
				}
			}
			else if(Direcao.OESTE == this.heading) {
				this.xPosicao += -1;
				if(this.xPosicao < 0) {
					this.xPosicao = 0;
				}
			}

			// Posicao que noa eh possivel fazer moviemnto
			if(this.maze.isParede(this.xPosicao,this.yPosicao) == true) {
				this.xPosicao = atualX;
				this.yPosicao = atualY;
			}
			else {
				if(atualX != this.xPosicao || atualY != this.yPosicao) {
					this.rota
				}
				
			}
		}
	}

}
