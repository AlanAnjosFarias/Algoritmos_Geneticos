package modulo_04;

import java.util.HashMap;

public class QuadroAula {
	private final HashMap<Integer, Sala> salas;
	private final HashMap<Integer, Professor> professores;
	private final HashMap<Integer, ModuloCurso> modulosCurso;
	private final HashMap<Integer, Grupo> grupos;
	private final HashMap<Integer, HoraUtil> horasUtil;
	private Aula aulas[];
	
	private int numAulas = 0;
	
	/* 
	 * 
	 * Inicializando um novo quadro de aulas
	 * 
	 */
	
	public QuadroAula() {
		this.salas = new HashMap<Integer, Sala>();
		this.professores = new HashMap<Integer, Professor>();
		this.modulosCurso = new HashMap<Integer, ModuloCurso>();
		this.grupos = new HashMap<Integer, Grupo>();
		this.horasUtil = new HashMap<Integer, HoraUtil>();
	}
	
	public QuadroAula(QuadroAula clonavel) {
		this.salas = clonavel.getSalas();
		this.professores = clonavel.getProfessores();
		this.modulosCurso = clonavel.getModulosCurso();
		this.grupos = clonavel.getGrupos();
		this.horasUtil = clonavel.getHorasUtil();
	}
	
	public HashMap<Integer, Sala> getSalas(){
		return this.salas;
	}
	
	//OK
	public HashMap<Integer, Professor> getProfessores(){
		return this.professores;
	}
	
	//OK
	public HashMap<Integer, ModuloCurso> getModulosCurso(){
		return this.modulosCurso;
	}
	
	//OK
	public HashMap<Integer, Grupo> getGrupos(){
		return this.grupos;
	}
	
	//OK
	public HashMap<Integer, HoraUtil> getHorasUtil(){
		return this.horasUtil;
	}
	
	
	/**
	 * Add nova sala
	 *
	 * @param salaID
	 * @param salaName
	 * @param capacidade
	 * 
	 */
	//OK
	public void addSala(int salaID, String salaNome, int capacidade) {
		this.salas.put(salaID, new Sala(salaID, salaNome, capacidade));
	}
	
	
	/**
	 * Add novo professor
	 *
	 * @param professorID
	 * @param professorName
	 * 
	 * 
	 */	
	//OK
	public void addProfessor(int professorID, String professorNome) {
		this.professores.put(professorID, new Professor(professorID, professorNome));
	}
	
	
	/**
	 * Add novo ModuloCurso
	 *
	 * @param moduloID
	 * @param moduloCodigo
	 * @param modulo
	 * @param professoresID
	 * 
	 */
	//OK
	public void addModulo(int moduloID, String moduloCodigo, String modulo, int professoresID[]) {
		this.modulosCurso.put(moduloID, new ModuloCurso(moduloID, moduloCodigo, modulo, professoresID));
	}
	
	
	/**
	 * Add novo grupo
	 *
	 * @param grupoID
	 * @param grupoTamanho
	 * @param modulosID
	 *
	 * 
	 */
	//OK
	public void addGrupo(int grupoID, int grupoTamanho, int modulosID[]) {
		this.grupos.put(grupoID, new Grupo(grupoID, grupoTamanho, modulosID));
		this.numAulas = 0;
	}
	
	
	/**
	 * Add nova HoraUtil
	 *
	 * @param horaUtilID
	 * @param horaUtil
	 *
	 * 
	 */
	//OK
	public void addHoraUtil(int horaUtilID, String horaUtil) {
		this.horasUtil.put(horaUtilID, new HoraUtil(horaUtilID, horaUtil));
	}
	
	
	/**
	 * criando Aula usando cromossomo individuo
	 *
	 * @param individuo
	 *  
	 */
	//OK
	public void criarAula(Individuo individuo) {
		//inicia aula
		Aula aulas[] = new Aula[this.getNumAulas()];
		
		//obtem cromossomo individuo
		int cromossomo[] = individuo.getCromossomo();
		int cromossomoPos = 0;
		int indexAula = 0;
		
		for(Grupo grupo : this.getGruposComoArray() ) {
			int auxModulosID[] = grupo.getModulosID();
			for(int auxModuloID : auxModulosID) {
				aulas[indexAula] = new Aula(indexAula, grupo.getGrupoID(), auxModuloID);
				
				//add HoraUtil
				aulas[indexAula].addHoraUtil(cromossomo[cromossomoPos]);
				cromossomoPos++;
				
				//add sala
				aulas[indexAula].setSalaID(cromossomo[cromossomoPos]);
				cromossomoPos++;
				
				//add Professor
				aulas[indexAula].addProfessor(cromossomo[cromossomoPos]);
				cromossomoPos++;
				
				indexAula++;
			}
		}
		
		this.aulas = aulas;
	}
	
	/**
	 * obtendo sala de salaID
	 *
	 * @param salaID
	 * @return room
	 *  
	 */
	//OK
	public Sala getSala(int salaID) {
		if(!this.salas.containsKey(salaID)) {
			System.out.println("Salas não contem a chave " + salaID );
		}
		return (Sala) this.salas.get(salaID);
	}
	
	
	/**
	 * obtendo sala aleatoria
	 *
	 * @return room
	 *  
	 */
	public Sala getSalaAleatoria(){
		Object[] salasArray = this.salas.values().toArray();
		Sala sala = (Sala) salasArray[(int) (salasArray.length * Math.random())];
		
		return sala;
	}
	
	
	/**
	 * obtendo professor de professorID
	 *
	 * @param professorID
	 * @return professor
	 *  
	 */
	public Professor getProfessor(int professorID) {
		return (Professor) this.professores.get(professorID);
	}
	
	
	/**
	 * obtendo modulo de moduloID 
	 *
	 * @param moduloID 
	 * @return modulo
	 *  
	 */
	public ModuloCurso getModuloCurso(int moduloID) {
		return (ModuloCurso) this.modulosCurso.get(moduloID);
	}
	
	
	/**
	 * obtendo moduloID de grupo de estudante 
	 *
	 * @param grupoID 
	 * @return moduloID array
	 *  
	 */
	public int[] getGrupoModulos(int grupoID) {
		Grupo grupo = (Grupo) this.grupos.get(grupoID);
		return grupo.getModulosID();
	}
	
	
	/**
	 * obtendo grupo de grupoID 
	 *
	 * @param grupoID 
	 * @return grupo
	 *  
	 */
	public Grupo getGrupo(int grupoID) {
		return (Grupo) this.grupos.get(grupoID);
	}
	
	
	/**
	 * obtendo todos os estudadnte do grupo 
	 *
	 * 
	 * @return array de grupos
	 *  
	 */
	public  Grupo[] getGruposComoArray() {
		return (Grupo[]) this.grupos.values().toArray(new Grupo[this.grupos.size()]);
	}
	
	/**
	 * obtendo HoraUtil de HoraUtilID 
	 *
	 * @param HoraUtilID 
	 * @return HoraUtil
	 *  
	 */
	public HoraUtil getHoraUtil(int horaUtilID) {
		return (HoraUtil) this.horasUtil.get(horaUtilID);
	}
	
	
	/**
	 * obtendo HoraUtilID aleatoria 
	 * 
	 * @return HoraUtil
	 *  
	 */
	public HoraUtil getHoraUtilAleatoria() {
		Object[] horaUtilArray = this.horasUtil.values().toArray();
		HoraUtil horaUtil = (HoraUtil) horaUtilArray[(int) (horaUtilArray.length*Math.random())];
		return horaUtil;
	}
	
	
	/**
	 * obtendo aulas
	 * 
	 * @return aulas
	 *  
	 */
	public Aula[] getAulas() {
		return this.aulas;
	}
	
	
	/**
	 * obtendo numero de aulas que precisa agendar
	 * 
	 * @return numAulas
	 *  
	 */
	public int getNumAulas() {
		if(this.numAulas > 0) {
			return this.numAulas;
		}
		
		int numAulas = 0;
		Grupo grupos[] = (Grupo[]) this.grupos.values().toArray(new Grupo[this.grupos.size()]);
		for(Grupo grupo : grupos) {
			numAulas += grupo.getModulosID().length;
		}
		this.numAulas = numAulas;
		
		return this.numAulas;
	}
	
	/**
	 * calcula numero de confrontos
	 * 
	 * @return confrontos
	 *  
	 */
	public int calcConfrontos() {
		int confrontos = 0;
		for(Aula aulaA : this.aulas) {
			//verifica capacidade
			int salaCapacidade = this.getSala(aulaA.getSalaID()).getCapacidade();
			int grupoTamanho = this.getGrupo(aulaA.getGrupoID()).getGrupoTamanho();
			if(salaCapacidade < grupoTamanho) {
				confrontos++;
			}
			
			//verifica se a sala ja esta usada
			for(Aula aulaB : this.aulas) {
				if(aulaA.getSalaID() == aulaB.getSalaID() && aulaA.getHoraUtilID() == aulaB.getHoraUtilID() && aulaA.getAulaID() == aulaB.getAulaID()) {
					confrontos++;
					break;					
				}
			}
			
			//verificar se o professor esta disponivel
			for(Aula aulaB : this.aulas) {
				if(aulaA.getProfessorID() == aulaB.getProfessorID() && aulaA.getHoraUtilID() == aulaB.getHoraUtilID() && aulaA.getAulaID() == aulaB.getAulaID()) {
					confrontos++;
					break;
				}
			}
		}
		
		return confrontos;
		
	}
}
