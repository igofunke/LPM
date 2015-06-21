package sabstracta;

/**
 * Represents a greater equal operation in the syntax tree.
 *
 */
public class MayorIgual extends ExpresionBinariaComparativa {

	public MayorIgual(Expresion _izq, Expresion _dcha) {
		super(_izq, _dcha);
	}
	/**
	 * Returns the instruction code.
	 */
	@Override
	protected String getInst() {
		return "geq";
	}

}
