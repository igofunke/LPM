package sabstracta;

import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.IntWrapper;
import errores.ErrorDispatcher;
import identificadores.VisitanteGramatica;
import tipos.Clases;
import tipos.Tipo;
import tipos.TipoRegistro;

/**
 * Represents a register accessing operation in the syntax tree.
 *
 */
public class ElementoRegistro extends Var {

	public ElementoRegistro(Var _lhs, Identificador _rhs) {
		this.reg = _lhs;
		this.elem = _rhs;
	}
	/**
	 * Components of the expression: reg.elem.
	 */
	private Var reg;
	private Identificador elem;
	
	/**
	 * Visits the components of the expression. No symbol or identifier
	 * needs to be inspected.
	 */
	@Override
	public void aceptaVisitante(VisitanteGramatica v) {
		reg.aceptaVisitante(v);
	}
	
	/**
	 * Computes the type of the expression and check the type of its
	 * components throwing an exception if necessary.
	 */
	@Override
	protected Tipo calculaTipo() {
		if (reg.getTipo().clase() != Clases.Registro)
			ErrorDispatcher.getInstance().sendErrorTipos("Se esperaba un registro.", getFila());
		
		return reg.getTipo().desempaquetar(elem.getId());
	}
	/**
	 * Generates the codeL for this lefthand expression.
	 */
	@Override
	public void codeL(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		reg.codeL(env, writer);
		String idReg;
		idReg = ((TipoRegistro)reg.getTipo().simplificar()).getIdentificador();
		writer.write("inc " + env.pos(idReg + "." + elem.getId()));
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
		reg.maxStack(acc, max);
	}
}
