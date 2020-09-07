package modulo_04;

public class ModuloCurso {
	private final int moduloID;
	private final String moduloCodigo;
	private final String modulo;
	private final int professoresID[];
	
	public ModuloCurso(int moduloID, String moduloCodigo, String modulo, int professoresID[]) {
		this.moduloID = moduloID;
		this.moduloCodigo = moduloCodigo;
		this.modulo = modulo;
		this.professoresID = professoresID;
	}
	
	public int getModuloID() {
		return moduloID;
	}
	
	public String getModuloCodigo() {
		return moduloCodigo;
	}
	
	public String getModuloNome() {
		return modulo;
	}
	
	public int getAleatorioProfessorID() {
		int auxProfessorID = professoresID[(int) (professoresID.length * Math.random())];
		
		return auxProfessorID;
	}

}
