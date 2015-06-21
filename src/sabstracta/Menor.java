package sabstracta;

/**
 * Represents a less than operation in the binary tree.
 *
 */
public class Menor extends ExpresionBinariaComparativa {

	public Menor(Expresion _izq, Expresion _dcha) {
		super(_izq, _dcha);
	}
	/**
	 * Returns the instruction code.
	 */
	@Override
	protected String getInst() {
		return "les";
	}
}
