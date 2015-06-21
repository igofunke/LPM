package sabstracta;

import errores.ErrorDispatcher;
import tipos.TipoBasico;
/**
 * Represents a case block in the syntax tree.
 *
 */
public class CaseC extends Case {

	public CaseC(Entero _exp, Bloque _bloque) {
		super(_bloque);
		this.expresion = _exp;
	}
	/**
	 * Components: case expresion : [...]
	 */
	private Entero expresion;
	/**
	 * Returns the case value.
	 * @return
	 */
	public int getValor (){
		return expresion.getEntero();
	}
	/**
	 * Check the type of exp.
	 */
	@Override
	public void compruebaTipos() {
		super.compruebaTipos();
		if (!expresion.getTipo().equals(TipoBasico.INT))
			ErrorDispatcher.getInstance().sendErrorTipos("La expresión de un case debe ser de tipo entero.", getFila());
	}
}
