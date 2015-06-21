package sabstracta;

import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.InstEtiquetaSimple;
import codigo.IntWrapper;
import errores.ErrorDispatcher;
import tipos.TipoBasico;
import identificadores.VisitanteGramatica;
/**
 * Represents a while instruction in the syntax tree.
 *
 */
public class While extends Instruccion {

	public While(Expresion _exp, Bloque _bloque) {
		this.expresion = _exp;
		this.bloque = _bloque;
	}
	/**
	 * Instruction components: while (expresion) [bloque]
	 */
	private Expresion expresion;
	private Bloque bloque;
	
	/**
	 * Visits the instruction. No symbol or identifier inspection needed.
	 */
	@Override
	public void aceptaVisitante(VisitanteGramatica v) {
		expresion.aceptaVisitante(v);
		bloque.aceptaVisitante(v);
	}
	
	/**
	 * Check that the types of its components are correct.
	 */
	@Override
	public void compruebaTipos() {
		if (!expresion.getTipo().equals(TipoBasico.BOOL))
			ErrorDispatcher.getInstance().sendErrorTipos("La condición de un while debe ser de tipo booleano.", getFila());
		
		bloque.compruebaTipos();
	}
	/**
	 * Generates the code for this instruction.
	 */
	@Override
	public void code(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		CodeWriter.Etiqueta l1 = writer.reservaEtiqueta();
		CodeWriter.Etiqueta l2 = writer.reservaEtiqueta();
		writer.marca(l1);
		expresion.codeR(env, writer);
		writer.write(new InstEtiquetaSimple("fjp",l2));
		bloque.code(env, writer);
		writer.write(new InstEtiquetaSimple("ujp",l1));
		writer.marca(l2);
	}
	/**
	 * Computes the necessary stack length for this instruction.
	 */
	@Override
	public int evStackLength() {
		IntWrapper acc = new IntWrapper(0), max = new IntWrapper(0);
		expresion.maxStack(acc, max);
		max.i = Math.max(max.i, bloque.evStackLength());
		
		return max.i;
	}
}
