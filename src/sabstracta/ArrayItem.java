package sabstracta;

import identificadores.VisitanteGramatica;
import tipos.Clases;
import tipos.Tipo;
import tipos.TipoBasico;
import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.IntWrapper;
import errores.ErrorDispatcher;

/**
 * Represents an array access operation in the syntax tree.
 *
 */
public class ArrayItem extends Var {

	public ArrayItem(Var _var, Expresion _exp) {
		this.var=_var;
		this.exp=_exp;
	}
	/**
	 * Components of the expression: var(exp).
	 */
	private Var var;
	private Expresion exp;
	
	/**
	 * Visits the components of the expression. No symbol or identifier
	 * needs to be inspected.
	 */
	@Override
	public void aceptaVisitante(VisitanteGramatica v) {
		var.aceptaVisitante(v);
		exp.aceptaVisitante(v);
	}
	/**
	 * Computes the type of the expression and check the type of its
	 * components throwing an exception if necessary.
	 */
	@Override
	protected Tipo calculaTipo() {
		if (var.getTipo().clase() != Clases.Array)
			ErrorDispatcher.getInstance().sendErrorTipos("Se esperaba un array.", getFila());
		if (!exp.getTipo().equals(TipoBasico.INT))
			ErrorDispatcher.getInstance().sendErrorTipos("Se esperaba una expresion al acceder al array.", getFila());
		
		return var.getTipo().desempaquetar(null);
	}
	/**
	 * Generates the codeL for this lefthand expression.
	 */
	@Override
	public void codeL(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		var.codeL(env, writer);
		//--- codeI [0..exp] g env
		exp.codeR(env, writer);
		writer.write("ixa " + var.getTipo().desempaquetar(null).tam());
		writer.write("dec 0");
	}
	/**
	 * Computes the max. stack length needed for the computation of the expression. <<max>> will
	 * contain that amount.
	 * @param acc When the function returns <<acc>> will have been increased by the amount of
	 * stack positions it needs.
	 * @param max When the function returns, <<max>> will contain the maximum value of <<acc>>.
	 */
	@Override
	protected void maxStack(IntWrapper acc, IntWrapper max) {
		var.maxStack(acc, max);
		exp.maxStack(acc, max);
		acc.i--;
	}
}
