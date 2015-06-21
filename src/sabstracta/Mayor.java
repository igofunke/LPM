package sabstracta;

/**
 * Represents a greater than operation in the syntax tree.
 *
 */
public class Mayor extends ExpresionBinariaComparativa {

	public Mayor(Expresion _izq, Expresion _dcha) {
		super(_izq, _dcha);
	}
	/**
	 * Returns the instruction code.
	 */
	@Override
	protected String getInst() {
		return "grt";
	}

}
