package sabstracta;

import identificadores.Simbolo;
import identificadores.VisitanteGramatica;

import java.util.Vector;

import tipos.Tipo;
import tipos.TipoRegistro;
import codigo.CodeWriter;
import codigo.Entorno;

/**
 * Represents a register declaration in the syntax tree.
 *
 */
public class Registro extends Elemento implements Simbolo {

	public Registro(String _iden, Vector<Definicion> _defs) {
		this.definiciones = _defs;
		this.identificador = _iden;
	}
	
	public String getIdentificador(){
		return this.identificador;
	}
	/**
	 * Components of a register declaration:
	 * reg identificador [
	 * 	definiciones
	 * ]
	 */
	private String identificador;
	private Vector<Definicion> definiciones;
	/**
	 * Generates the associated type.
	 */
	@Override
	public Tipo compilaTipo() {
		return new TipoRegistro(definiciones, this.identificador);
	}
	/**
	 * Registers itself as a symbol.
	 */
	@Override
	public void aceptaVisitante(VisitanteGramatica v) {
		v.visitaSimbolo(this);
	}
	/**
	 * Returns its internal definitions.
	 */
	@Override
	public Vector<Definicion> elementos() {
		return definiciones;
	}
	/**
	 * Processes the positions in memory of its internal definitions.
	 */
	public void code (Entorno env, CodeWriter writer){
		int t = 0;
		for (Definicion d: definiciones){
			env.asignarAbs(identificador + "." + d.getIdentificador(), t);
			t += d.compilaTipo().tam();
		}
	}
}
