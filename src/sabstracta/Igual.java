package sabstracta;

/**
 * Represents an equal operation in the syntax tree.
 *
 */
public class Igual extends ExpresionBinariaComparativa {

	public Igual(Expresion _izq, Expresion _dcha) {
		super(_izq, _dcha);
	}
	/**
	 * Returns the instruction code.
	 */
	@Override
	protected String getInst() {
		return "equ";
	}
}
