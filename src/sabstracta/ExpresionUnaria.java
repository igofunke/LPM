package sabstracta;

import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.IntWrapper;
import identificadores.VisitanteGramatica;

/**
 * Represents an unary expression in the syntax tree.
 *
 */
public abstract class ExpresionUnaria extends Expresion {
	public ExpresionUnaria(Expresion _exp){
		this.exp = _exp;
	}
	
	public Expresion getExpresion(){
		return this.exp;
	}
	/**
	 * Components of the expression: op exp.
	 */
	private Expresion exp;
	/**
	 * Visits the components of the expression. No symbol or identifier
	 * needs to be inspected.
	 */
	@Override
	public void aceptaVisitante(VisitanteGramatica v) {
		exp.aceptaVisitante(v);
	}
	/**
	 * Generates the codeR for the expression.
	 * @param env Current environment of identifiers.
	 * @param writer Codewriter used to write the code.
	 * @throws ExcepcionWriter If there is a writing error.
	 */
	@Override
	public void codeR(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		exp.codeR(env, writer);
		writer.write(getInst());
	}
	/**
	 * Returns the instruction code for the operation it represents.
	 */
	protected abstract String getInst();
	/**
	 * Computes the max. stack length needed for the computation of the expression. <<max>> will
	 * contain that amount.
	 * @param acc When the function returns <<acc>> will have been increased by the amount of
	 * stack positions it needs.
	 * @param max When the function returns, <<max>> will contain the maximum value of <<acc>>.
	 */
	@Override
	protected void maxStack(IntWrapper acc, IntWrapper max) {
		exp.maxStack(acc, max);
	}
}
