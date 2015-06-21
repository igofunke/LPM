package sabstracta;
/**
 * Represents a less equal expression in the binary tree.
 *
 */
public class MenorIgual extends ExpresionBinariaComparativa {

	public MenorIgual(Expresion _izq, Expresion _dcha) {
		super(_izq, _dcha);
	}
	/**
	 * Returns the instruction code.
	 */
	@Override
	protected String getInst() {
		return "leq";
	}

}
