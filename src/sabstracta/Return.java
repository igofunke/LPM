package sabstracta;

import codigo.CodeWriter;
import codigo.ContextType;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.IntWrapper;
import identificadores.VisitanteGramatica;

/**
 * Represents a return instruction in the syntax tree.
 *
 */
public class Return extends Instruccion {

	public Return(Expresion _exp) {
		this.expresion = _exp;
	}
	/**
	 * Instruction components: return expresion
	 */
	private Expresion expresion;
	
	/**
	 * Visits the instruction. No symbol or identifier inspection needed.
	 */
	@Override
	public void aceptaVisitante(VisitanteGramatica v) {
		expresion.aceptaVisitante(v);
	}
	/**
	 * Check that the types of its components are correct.
	 */
	@Override
	public void compruebaTipos() {
		expresion.getTipo();
	}
	/**
	 * Generates the code for this instruction.
	 */
	@Override
	public void code(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		expresion.codeR(env, writer);
		writer.write("str 0 0");
		if (env.contextType().equals(ContextType.Procedure))
			writer.write("retp");
		else if (env.contextType().equals(ContextType.Function))
			writer.write("retf");
	}
	/**
	 * Computes the necessary stack length for this instruction.
	 */
	@Override
	public int evStackLength() {
		IntWrapper acc = new IntWrapper(0), max = new IntWrapper(0);
		expresion.maxStack(acc, max);
		
		return max.i;
	}
}
