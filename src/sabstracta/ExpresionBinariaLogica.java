package sabstracta;

import errores.ErrorDispatcher;
import tipos.Tipo;
import tipos.TipoBasico;

/**
 * Represents a logical binary expression in the syntax tree.
 *
 */
public abstract class ExpresionBinariaLogica extends ExpresionBinaria {

	public ExpresionBinariaLogica(Expresion _izq, Expresion _dch) {
		super(_izq, _dch);
	}

	/**
	 * Computes the type of the expression and check the type of its
	 * components throwing an exception if necessary.
	 */
	@Override
	protected Tipo calculaTipo() {
		if(!(get_izq().getTipo().equals(TipoBasico.BOOL) &&
				get_dcha().getTipo().equals(TipoBasico.BOOL)))
			ErrorDispatcher.getInstance().sendErrorTipos("Los operandos de las expresiones lógicas deben ser booleanos.", getFila());
		
		return TipoBasico.BOOL;
	}

}
