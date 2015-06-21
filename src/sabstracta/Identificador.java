package sabstracta;

import identificadores.ExcepcionIdentificador;
import identificadores.Simbolo;
import identificadores.VisitanteGramatica;
import tipos.Tipo;
import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.IntWrapper;
import errores.ErrorDispatcher;

/**
 * Represents an identifier in the syntax tree.
 *
 */
public class Identificador extends Var{

	public Identificador(String _iden) {
		this.iden = _iden;
	}
	
	public String getId(){
		return iden;
	}
	/**
	 * Components of the expression: iden.
	 */
	private String iden;
	/**
	 * Visits the identifier, getting the associated symbol.
	 */
	@Override
	public void aceptaVisitante(VisitanteGramatica v){
		try {
			ref = v.visitaIdentificador(iden);
		} catch (ExcepcionIdentificador e) {
			ErrorDispatcher.getInstance().sendErrorIdentificacion(e.getMessage(), getFila());
		}
	}
	private Simbolo ref;
	/**
	 * Computes the type of the expression.
	 */
	@Override
	protected Tipo calculaTipo() {
		return ref.compilaTipo();
	}
	/**
	 * Generates the codeL for this lefthand expression.
	 */
	public void codeL (Entorno env, CodeWriter writer) throws ExcepcionWriter{
		if (!env.ref(iden))
			writer.write(("lda " + (env.n() - env.def(iden))) + " " + env.pos(iden));
		else
			writer.write(("lod " + (env.n() - env.def(iden))) + " " + env.pos(iden));
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
