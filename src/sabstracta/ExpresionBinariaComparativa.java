package sabstracta;

import errores.ErrorDispatcher;
import tipos.Tipo;
import tipos.TipoBasico;

/**
 * Represents a comparative binary expression in the syntax tree.
 *
 */
public abstract class ExpresionBinariaComparativa extends ExpresionBinaria {

	public ExpresionBinariaComparativa(Expresion _izq, Expresion _dch) {
		super(_izq, _dch);
	}
	/**
	 * Computes the type of the expression and check the type of its
	 * components throwing an exception if necessary.
	 */
	@Override
	protected Tipo calculaTipo() {
		if(!(get_izq().getTipo().equals(TipoBasico.INT) &&
				get_dcha().getTipo().equals(TipoBasico.INT)))
			ErrorDispatcher.getInstance().sendErrorTipos("Los operandos de las expresiones comparativas deben ser enteros.", getFila());
		
		return TipoBasico.BOOL;
	}

}
