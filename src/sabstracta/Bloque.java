package sabstracta;

import identificadores.VisitanteGramatica;

import java.util.Iterator;
import java.util.Vector;

import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;

/**
 * Represents a set of instructions in the syntax tree.
 *
 */
public class Bloque extends Elemento implements Iterable<Instruccion>{

	public Bloque(Vector<Instruccion> _seq) {
		this.instrucciones = _seq;
	}
	/**
	 * The set of instructions.
	 */
	private Vector<Instruccion> instrucciones;
	
	/**
	 * Visits all the instructions.
	 */
	@Override
	public void aceptaVisitante(VisitanteGramatica v) {
		for (Instruccion i: instrucciones)
			i.aceptaVisitante(v);
	}
	/**
	 * Checks the types of its components.
	 */
	public void compruebaTipos() {
		for (Instruccion i: instrucciones)
			i.compruebaTipos();
	}
	/**
	 * Generates code for its components.
	 */
	public void code(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		for (Instruccion i: instrucciones)
			i.code(env, writer);
	}
	/**
	 * Computes the maximum stack length needed for the execution
	 * of all its instructions.
	 */
	public int evStackLength() {
		int m = 0;
		for (Instruccion i: instrucciones)
			m = Math.max(m, i.evStackLength());
		return m;
	}
	/**
	 * Iterates through the instructions.
	 */
	@Override
	public Iterator<Instruccion> iterator() {
		return instrucciones.iterator();
	}
}
