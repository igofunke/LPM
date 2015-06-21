package sabstracta;

import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.IntWrapper;
import identificadores.VisitanteGramatica;

/**
 * Represents a binary expression in the syntax tree.
 *
 */
public abstract class ExpresionBinaria extends Expresion {
	public ExpresionBinaria(Expresion _izq, Expresion _dch) {
		this._izq = _izq;
		this._dcha = _dch;
	}
	
	public Expresion get_izq() {
		return _izq;
	}

	public Expresion get_dcha() {
		return _dcha;
	}
	/**
	 * Components of the expression: _izq op _dcha.
	 */
	private Expresion _izq,_dcha;
	
	/**
	 * Visits the components of the expression. No symbol or identifier
	 * needs to be inspected.
	 */
	@Override
	public void aceptaVisitante(VisitanteGramatica v) {
		_izq.aceptaVisitante(v);
		_dcha.aceptaVisitante(v);
	}
	/**
	 * Generates the codeR for the expression.
	 * @param env Current environment of identifiers.
	 * @param writer Codewriter used to write the code.
	 * @throws ExcepcionWriter If there is a writting error.
	 */
	@Override
	public void codeR(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		get_izq().codeR(env, writer);
		get_dcha().codeR(env, writer);
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
		get_izq().maxStack(acc, max);
		get_dcha().maxStack(acc, max);
		acc.i--;
	}
}
