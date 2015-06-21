package sabstracta;

/**
 * Represents the substraction operation in the syntax tree.
 *
 */
public class Resta extends ExpresionBinariaAritmetica {

	public Resta(Expresion _izq, Expresion _dcha) {
		super(_izq, _dcha);
	}
	/**
	 * Returns the instruction code.
	 */
	@Override
	protected String getInst() {
		return "sub";
	}

}
