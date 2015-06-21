package sabstracta;

/**
 * Represents an addition operation in the syntax tree.
 *
 */
public class Suma extends ExpresionBinariaAritmetica {

	public Suma(Expresion _izq, Expresion _dcha) {
		super(_izq, _dcha);
	}
	/**
	 * Returns the instruction code.
	 */
	@Override
	protected String getInst() {
		return "add";
	}
}
