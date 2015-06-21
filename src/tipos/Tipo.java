package tipos;

import identificadores.ExcepcionIdentificador;
import identificadores.VisitanteGramatica;

/**
 * General class for types.
 *
 */
public abstract class Tipo {
	/**
	 * The size of the type.
	 */
	public abstract int tam();
	/**
	 * The class of the type.
	 */
	public abstract Clases clase();
	/**
	 * Returns the subtype of the type. Used when applying type rules.
	 * @param context Only if it is a register, context specifies which
	 * internal definition type the function must return.
	 */
	public abstract Tipo desempaquetar(String context);
	/**
	 * Used only to return the actual type class the class TipoComplejo actually
	 * represents.
	 */
	public Tipo simplificar(){return this;}
	/**
	 * Visits the subtypes to resolve identifier dependencies.
	 */
	public void aceptaVisitante (VisitanteGramatica v) throws ExcepcionIdentificador {}
}
