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
 * Represents an if instruction in the syntax tree.
 *
 */
public class If extends Instruccion {

	public If(Expresion _exp, Bloque _bloque) {
		this.expresion = _exp;
		this.bloqueT = _bloque;
	}

	public If(Expresion _exp, Bloque _bloqueT, Bloque _bloqueF) {
		this.expresion = _exp;
		this.bloqueT = _bloqueT;
		this.bloqueF = _bloqueF;
		
	}
	/**
	 * Instruction components: if (expresion) [bloqueT] else [bloqueF]
	 */
	private Expresion expresion;
	private Bloque bloqueT;
	private Bloque bloqueF;
	
	/**
	 * Visits the instruction. No symbol or identifier inspection needed.
	 */
	@Override
	public void aceptaVisitante(VisitanteGramatica v) {
		expresion.aceptaVisitante(v);
		bloqueT.aceptaVisitante(v);
		if (bloqueF != null) bloqueF.aceptaVisitante(v);
	}
	/**
	 * Check that the types of its components are correct.
	 */
	@Override
	public void compruebaTipos() {
		if (!expresion.getTipo().equals(TipoBasico.BOOL))
			ErrorDispatcher.getInstance().sendErrorTipos("La expresión en un if debe ser de tipo booleano", getFila());
		
		bloqueT.compruebaTipos();
		if (bloqueF != null) bloqueF.compruebaTipos();
	}
	/**
	 * Generates the code for this instruction.
	 */
	@Override
	public void code(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		CodeWriter.Etiqueta l = writer.reservaEtiqueta();
		expresion.codeR(env, writer);
		writer.write(new InstEtiquetaSimple("fjp",l));
		bloqueT.code(env, writer);
		if (bloqueF == null) writer.marca(l);
		else {
			CodeWriter.Etiqueta m = writer.reservaEtiqueta();
			writer.write(new InstEtiquetaSimple("ujp",m));
			writer.marca(l);
			bloqueF.code(env, writer);
			writer.marca(m);
		}
		
	}
	/**
	 * Computes the necessary stack length for this instruction.
	 */
	@Override
	public int evStackLength() {
		IntWrapper acc = new IntWrapper(0), max = new IntWrapper(0);
		expresion.maxStack(acc, max);
		max.i = Math.max(max.i, bloqueT.evStackLength());
		if (bloqueF != null) max.i = Math.max(max.i, bloqueF.evStackLength());
		
		return max.i;
	}
	
}
