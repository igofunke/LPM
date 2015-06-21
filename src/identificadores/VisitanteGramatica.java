package identificadores;

/**
 * Visitor of the syntax tree that performs an specific operation on
 * it.
 *
 */
public abstract class VisitanteGramatica {
	/**
	 * Tells the visitor that a block is being entered.
	 */
	public abstract void entraBloque ();
	/**
	 * Tells the visitor that a block is being left.
	 */
	public abstract void salBloque ();
	/**
	 * Tells the visitor that a symbol is being inspected.
	 */
	public abstract void visitaSimbolo (Simbolo e);
	/**
	 * Tells the visitor that identifier is being inspected.
	 * @param id
	 * @return
	 * @throws ExcepcionIdentificador
	 */
	public abstract Simbolo visitaIdentificador (String id) throws ExcepcionIdentificador;
}
