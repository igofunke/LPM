package sabstracta;

/**
 * Represents a multiplication operation in the syntax tree.
 *
 */
public class Multiplicacion extends ExpresionBinariaAritmetica {

	public Multiplicacion(Expresion _izq, Expresion _dcha) {
		super(_izq, _dcha);
	}
	/**
	 * Returns the instruction code.
	 */
	@Override
	protected String getInst() {
		return "mul";
	}

}
