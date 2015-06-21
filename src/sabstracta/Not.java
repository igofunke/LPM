package sabstracta;

import errores.ErrorDispatcher;
import tipos.Tipo;
import tipos.TipoBasico;
/**
 * Represents a not operation in the syntax tree.
 *
 */
public class Not extends ExpresionUnaria {

	public Not(Expresion _exp) {
		super(_exp);
	}
	/**
	 * Computes the type of the expression and check the type of its
	 * components throwing an exception if necessary.
	 */
	@Override
	protected Tipo calculaTipo() {
		if (!getExpresion().getTipo().equals(TipoBasico.BOOL))
			ErrorDispatcher.getInstance().sendErrorTipos("Se esperaba un booleano.", getFila());
		
		return TipoBasico.BOOL;
	}
	/**
	 * Returns the instruction code.
	 */
	@Override
	protected String getInst() {
		return "not";
	}
}
