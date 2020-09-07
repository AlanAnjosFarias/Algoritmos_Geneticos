package modulo_04;

public class QuadroAulasGenetico {

	public static void main(String[] args) {
		// cria quadro de aula com todos os parametros
		QuadroAula quadroAula = inicializarQuadroAulas();
		
		//inicializa algoritmo genetico
		AlgoritmoGenetico ag = new AlgoritmoGenetico(100, 0.01, 0.9, 2, 5);
		
		//inicializa populacao
		Populacao populacao = ag.inicializarPopulacao(quadroAula);
		
		
		//evol populacao
		ag.evolucaoPopulacao(populacao, quadroAula);
		
		//inicia geracao
		int geracaoAtual = 1;
		
		//inicia loop de evolucao
		while(ag.condicaoFinalizar(geracaoAtual, 1000) == false && ag.condicaoFinalizar(populacao) == false) {
			//print fitness
			System.out.println("G" + geracaoAtual + " Melhor fitness: " + populacao.getFitnest(0).getFitness()); 
			
			
			//aplica cruzamento
			populacao = ag.cruzamentoPopulacao(populacao);
			
			//aplica mutacao
			populacao = ag.mutacaoPopulacao(populacao, quadroAula);
			
			
			// aplica evol
			ag.evolucaoPopulacao(populacao, quadroAula);
			
			//incrementa geracao
			geracaoAtual++;
			
		}
		
		// Imprimi fitness final
		quadroAula.criarAulas(populacao.getFitnest(0));
		System.out.println();
		System.out.println("Solucao Encontrada após " + geracaoAtual + " gerações");
		System.out.println("Fitness final da solucao: " + populacao.getFitnest(0).getFitness());
		System.out.println("Confrontos: " + quadroAula.calcConfrontos());
		
		// Imprimi quadro de aulas final
		System.out.println();
		Aula aulas[] = quadroAula.getAulas();
		int indexAula = 1;
		for(Aula melhorAula : aulas) {
			System.out.println("Aula " + indexAula + ":");
			System.out.println("Modulo: " + quadroAula.getModuloCurso(melhorAula.getModuloID()).getModuloNome());
			System.out.println("Grupo: " + quadroAula.getGrupo(melhorAula.getGrupoID()).getGrupoID());
			System.out.println("Sala: " + quadroAula.getSala(melhorAula.getSalaID()).getSalaNumero());
			System.out.println("Professor: " + quadroAula.getProfessor(melhorAula.getProfessorID()).getProfessorNome());
			System.out.println("Horario: " + quadroAula.getHoraUtil(melhorAula.getHoraUtilID()).getHoraUtil());
			System.out.println("-----");			
			indexAula++;
		}
	}
	
	private static QuadroAula inicializarQuadroAulas() {
		// criar quadro de aula
		QuadroAula quadroAula = new QuadroAula();
		
		// inserir-configurar salas
		quadroAula.addSala(1, "A1", 15);
		quadroAula.addSala(2, "B1", 30);
		quadroAula.addSala(4, "D1", 20);
		quadroAula.addSala(5, "F1", 25);
		
		// inserir-configurar horarios
		quadroAula.addHoraUtil(1, "Seg 9:00 - 11:00");
		quadroAula.addHoraUtil(2, "Seg 11:00 - 13:00");
		quadroAula.addHoraUtil(3, "Seg 13:00 - 15:00");
		quadroAula.addHoraUtil(4, "Ter 9:00 - 11:00");
		quadroAula.addHoraUtil(5, "Ter 11:00 - 13:00");
		quadroAula.addHoraUtil(6, "Ter 13:00 - 15:00");
		quadroAula.addHoraUtil(7, "Qua 9:00 - 11:00");
		quadroAula.addHoraUtil(8, "Qua 11:00 - 13:00");
		quadroAula.addHoraUtil(9, "Qua 13:00 - 15:00");
		quadroAula.addHoraUtil(10, "Qui 9:00 - 11:00");
		quadroAula.addHoraUtil(11, "Qui 11:00 - 13:00");
		quadroAula.addHoraUtil(12, "Qui 13:00 - 15:00");
		quadroAula.addHoraUtil(13, "Sex 9:00 - 11:00");
		quadroAula.addHoraUtil(14, "Sex 11:00 - 13:00");
		quadroAula.addHoraUtil(15, "Sex 13:00 - 15:00");
		
		// inserir-configurar professores
		quadroAula.addProfessor(1, "Dr. P Smith");
		quadroAula.addProfessor(2, "Mrs E Mitchell");
		quadroAula.addProfessor(3, "Dr. R Williams");
		quadroAula.addProfessor(4, "Mr. A Thompson");
		
		// configurar modulo e os professores que podem ensinar
		quadroAula.addModulo(1, "CS1", "Ciencia da Computacao", new int[] {1,2});
		quadroAula.addModulo(2, "EN1", "Ingles", new int[] {1,3});
		quadroAula.addModulo(3, "MA1", "Matematica", new int[] {1,2});
		quadroAula.addModulo(4, "FI1", "Fisica", new int[] {3,4});
		quadroAula.addModulo(5, "HI1", "Historia", new int[] {4});
		quadroAula.addModulo(6, "TE1", "Teatro", new int[] {1,4});
		
		// configurar os grupos de estudantes e suas disciplinas
		quadroAula.addGrupo(1, 10, new int[] {1, 3, 4});
		quadroAula.addGrupo(2, 30, new int[] {2, 3, 5, 6});
		quadroAula.addGrupo(3, 18, new int[] {3, 4, 5});
		quadroAula.addGrupo(4, 25, new int[] {1, 4});
		quadroAula.addGrupo(5, 20, new int[] {2, 3, 5});
		quadroAula.addGrupo(6, 22, new int[] {1, 4, 5});
		quadroAula.addGrupo(7, 16, new int[] {1, 3});
		quadroAula.addGrupo(8, 18, new int[] {2, 6});
		quadroAula.addGrupo(9, 24, new int[] {1, 6});
		quadroAula.addGrupo(10, 25, new int[] {3, 4});
		
		return quadroAula;
	}

}
