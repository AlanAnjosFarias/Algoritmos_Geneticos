package modulo_04;

public class Aula {
	private final int aulaID;
	private final int grupoID;
	private final int moduloID;
	private int professorID;
	private int horaUtilID;
	private int salaID;
	
	public Aula(int aulaID, int grupoID, int moduloID) {
		this.aulaID = aulaID;
		this.grupoID = grupoID;
		this.moduloID = moduloID;
	}
	
	public void addProfessor(int professorId) {
		this.professorID = professorId;
	}
	
	public void addHoraUtil(int horaUtilID) {
		this.horaUtilID = horaUtilID;
	}
	
	public void setSalaID(int salaID) {
		this.salaID = salaID;
	}
	
	public int getAulaID() {
		return this.aulaID;
	}
	
	public int getGrupoID() {
		return this.grupoID;
	}
	
	public int getModuloID() {
		return this.moduloID;
	}
	
	public int getProfessorID() {
		return this.professorID;
	}
	
	public int getHoraUtilID() {
		return this.horaUtilID;
	}
	
	public int getSalaID(){
		return this.salaID;
	}
	

}
