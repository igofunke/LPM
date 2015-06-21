package sabstracta;

import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;

/**
 * Represents an instruction in the syntax tree.
 *
 */
public abstract class Instruccion extends Elemento{
	/**
	 * Checks the types of its components.
	 */
	public abstract void compruebaTipos();
	/**
	 * Generates code for this instruction.
	 */
	public abstract void code (Entorno env, CodeWriter writer) throws ExcepcionWriter;
	/**
	 * Returns the maximum stack length needed for the execution of
	 * this instruction. 
	 */
	public abstract int evStackLength ();
}
