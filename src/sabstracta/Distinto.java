package sabstracta;

/**
 * Represents the operation 'not equal' in the syntax tree.
 * @author Ignacio
 *
 */
public class Distinto extends ExpresionBinariaComparativa {

	public Distinto(Expresion _izq, Expresion _dcha) {
		super(_izq, _dcha);
	}
	/**
	 * Returns the instruction code.
	 */
	@Override
	protected String getInst() {
		return "neq";
	}

}
