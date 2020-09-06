package modulo_04;

public class Professor {
	private final int professorID;
	private final String professorNome;
	
	public Professor(int professorID, String professorNome) {
		this.professorID = professorID;
		this.professorNome = professorNome;
	}
	
	public int getProfessorID() {
		return this.professorID;
	}
	
	public String getProfessorNome() {
		return this.professorNome;
	}
}
