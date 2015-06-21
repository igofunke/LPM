package sabstracta;

import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.IntWrapper;
import errores.ErrorDispatcher;
import identificadores.VisitanteGramatica;

/**
 * Represents an assignment instruction in the syntax tree.
 *
 */
public class Asignacion extends Instruccion {
	public Asignacion (Var _lhs, Expresion _rhs){
		this.expresion = _rhs;
		this.variable = _lhs;
	}
	/**
	 * Instruction components
	 */
	private Var variable;
	private Expresion expresion;
	
	/**
	 * Visits the instruction. No symbol or identifier inspection needed.
	 */
	@Override
	public void aceptaVisitante(VisitanteGramatica v) {
		variable.aceptaVisitante(v);
		expresion.aceptaVisitante(v);
	}
	/**
	 * Check that the types of its components are correct.
	 */
	@Override
	public void compruebaTipos() {
		if (!variable.getTipo().equals(expresion.getTipo()))
			ErrorDispatcher.getInstance().sendErrorTipos("Tipos de variable asignada y expresion no coinciden.", getFila());
	}
	/**
	 * Generates the code for this instruction.
	 */
	@Override
	public void code(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		variable.codeL(env, writer);
		expresion.codeR(env, writer);
		writer.write("sto");
	}
	/**
	 * Computes the necessary stack length for this instruction.
	 */
	@Override
	public int evStackLength() {
		IntWrapper acc=new IntWrapper(0), max=new IntWrapper(0);
		variable.maxStack(acc, max);
		expresion.maxStack(acc, max);
		
		return max.i;
	}
}
