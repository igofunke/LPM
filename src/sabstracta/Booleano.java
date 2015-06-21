package sabstracta;

import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.IntWrapper;
import identificadores.VisitanteGramatica;
import tipos.Tipo;
import tipos.TipoBasico;
/**
 * Represents a boolean in a expresion of the syntax tree.
 *
 */
public class Booleano extends Expresion {

	public Booleano(boolean b) {
		this.booleano = b;
	}
	/**
	 * Components of the expression: TT|FF.
	 */
	private boolean booleano;
	
	@Override
	public void aceptaVisitante(VisitanteGramatica v) {}
	/**
	 * Computes the type of the expression.
	 */
	@Override
	protected Tipo calculaTipo() {
		return TipoBasico.BOOL;
	}
	
	/**
	 * Generates the codeR for the expression.
	 * @param env Current environment of identifiers.
	 * @param writer Codewriter used to write the code.
	 * @throws ExcepcionWriter If there is a writting error.
	 */
	@Override
	public void codeR(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		String val = (booleano) ? "true" : "false";
		writer.write("ldc " + val);
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
