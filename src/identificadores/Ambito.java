package identificadores;
/**
 * Stores info about symbols to be stored in the symbol table.
 *
 */
public class Ambito {
	public Ambito(){
		puntero = null;
		simbolo = null;
	}

	public Simbolo getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(Simbolo simbolo) {
		this.simbolo = simbolo;
	}

	public String getPuntero() {
		return puntero;
	}
	public void setPuntero(String puntero) {
		this.puntero = puntero;
	}

	private String puntero;
	private Simbolo simbolo;
}
