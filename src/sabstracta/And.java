package sabstracta;

/**
 * Represents an And operation in the syntax tree.
 *
 */
public class And extends ExpresionBinariaLogica {

	public And(Expresion _izq, Expresion _dch) {
		super(_izq, _dch);
	}
	/**
	 * Returns the instruction code.
	 */
	@Override
	protected String getInst() {
		return "and";
	}
}
