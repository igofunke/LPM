package sabstracta;

/**
 * Represents a division in the syntax tree.
 *
 */
public class Division extends ExpresionBinariaAritmetica {

	public Division(Expresion _izq, Expresion _dcha) {
		super(_izq, _dcha);
	}
	/**
	 * Returns the instruction code.
	 */
	@Override
	protected String getInst() {
		return "div";
	}

}
