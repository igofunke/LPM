package sabstracta;

import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.IntWrapper;
import tipos.Tipo;
import tipos.TipoBasico;
import identificadores.VisitanteGramatica;

/**
 * Represents an integer in the syntax tree.
 *
 */
public class Entero extends Expresion {

	public Entero(int _ent) {
		this.ent = _ent;
	}
	/**
	 * Components of the expression: ...|-2|-1|0|1|2|....
	 */
	private int ent;
	
	public int getEntero(){
		return ent;
	}
	
	@Override
	public void aceptaVisitante(VisitanteGramatica v) {}
	/**
	 * Computes the type of the expression.
	 */
	@Override
	protected Tipo calculaTipo() {
		return TipoBasico.INT;
	}
	/**
	 * Generates the codeR for the expression.
	 * @param env Current environment of identifiers.
	 * @param writer Codewriter used to write the code.
	 * @throws ExcepcionWriter If there is a writting error.
	 */
	@Override
	public void codeR(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		writer.write("ldc " + ent);
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
		acc.i++;
		max.i = Math.max(acc.i, max.i);
	}
}
