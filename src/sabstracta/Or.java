package sabstracta;

/**
 * Represents an or operation in the syntax tree.
 *
 */
public class Or extends ExpresionBinariaLogica {

	public Or(Expresion _izq, Expresion _dch) {
		super(_izq, _dch);
	}
	/**
	 * Returns the instruction code.
	 */
	@Override
	protected String getInst() {
		return "or";
	}
}
