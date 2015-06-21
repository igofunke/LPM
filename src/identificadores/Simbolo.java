package identificadores;

import java.util.Vector;

import sabstracta.Definicion;
import tipos.Tipo;
/**
 * General type for symbols (currently definitions, registers
 * and functions) in the syntax tree.
 *
 */
public interface Simbolo{
	/**
	 * Returns the identifier for the symbol.
	 */
	public String getIdentificador();
	/**
	 * Gets the auxiliary definitions that comprise the symbol.
	 */
	public Vector<Definicion> elementos();
	/**
	 * Creates a type based on the form of the symbol.
	 */
	public Tipo compilaTipo ();
}
