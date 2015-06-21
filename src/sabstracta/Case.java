package sabstracta;

import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import identificadores.VisitanteGramatica;

/**
 * Represents a case block (case itself or default).
 *
 */
public abstract class Case extends Elemento{
	public Case(Bloque _bloque){
		this.bloque = _bloque;
	}
	/**
	 * The set of instructions of the case block.
	 */
	private Bloque bloque;
	/**
	 * Visits all the instructions.
	 */
	@Override
	public void aceptaVisitante(VisitanteGramatica v) {
		bloque.aceptaVisitante(v);
	}
	/**
	 * Checks the types of all the instructions.
	 */
	public void compruebaTipos (){
		bloque.compruebaTipos();
	}
	/**
	 * Generates code for all its instructions.
	 */
	public void code (Entorno env, CodeWriter writer) throws ExcepcionWriter{
		bloque.code(env, writer);
	}
	/**
	 * Computes the maximum stack length needed for executing all the
	 * instructions.
	 */
	public int evStackLength(){
		return bloque.evStackLength();
	}
}
