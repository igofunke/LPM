package sabstracta;

import errores.ErrorDispatcher;
import tipos.Tipo;
import tipos.TipoBasico;
/**
 * Represents the 'negative number'(-) operation in the syntax tree.
 *
 */
public class OpMenos extends ExpresionUnaria {

	public OpMenos(Expresion _exp) {
		super(_exp);
	}
	/**
	 * Computes the type of the expression and check the type of its
	 * components throwing an exception if necessary.
	 */
	@Override
	protected Tipo calculaTipo() {
		if (!getExpresion().getTipo().equals(TipoBasico.INT))
			ErrorDispatcher.getInstance().sendErrorTipos("Se esperaba un entero.", getFila());
		
		return TipoBasico.INT;
	}
	/**
	 * Returns the instruction code.
	 */
	@Override
	protected String getInst() {
		return "neg";
	}

}
