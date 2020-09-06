package modulo_04;

public class Grupo {
	private final int grupoID;
	private final int grupoTamanho;
	private final int modulosID[];
	
	public Grupo(int grupoID, int grupoTamanho, int modulosID[]) {
		this.grupoID = grupoID;
		this.grupoTamanho = grupoTamanho;
		this.modulosID = modulosID;
	}
	
	public int getGrupoID() {
		return this.grupoID;
	}
	
	public int getGrupoTamanho() {
		return this.grupoTamanho;
	}
	
	public int[] getModulosID() {
		return this.modulosID;
	}

}
