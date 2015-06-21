package sabstracta;

import identificadores.VisitanteGramatica;

import java.util.Stack;
import java.util.Vector;

import tipos.TipoBasico;
import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.InstEtiquetaSimple;
import codigo.IntWrapper;
import errores.ErrorDispatcher;

/**
 * Represents a switch instruction in the syntax tree.
 *
 */
public class Switch extends Instruccion {

	public Switch(Var _var, Vector<CaseC> _casos, Default _def) {
		this.variable = _var;
		this.casos = _casos;
		this.def = _def;
	}
	/**
	 * Instruction components: switch (variable) [casos def]
	 */
	private Var variable;
	private Vector<CaseC> casos;
	private Default def;
	
	/**
	 * Visits the instruction. No symbol or identifier inspection needed.
	 */
	@Override
	public void aceptaVisitante(VisitanteGramatica v) {
		variable.aceptaVisitante(v);
		for (Case c: casos)
			c.aceptaVisitante(v);
	}
	/**
	 * Check that the types of its components are correct.
	 */
	@Override
	public void compruebaTipos() {
		if (!variable.getTipo().equals(TipoBasico.INT))
			ErrorDispatcher.getInstance().sendErrorTipos("La variable en el switch debe ser de tipo entero.", getFila());
		for (Case c: casos)
			c.compruebaTipos();
	}
	/**
	 * Generates the code for this instruction.
	 */
	@Override
	public void code(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		CodeWriter.Etiqueta l,s = writer.reservaEtiqueta();
		CodeWriter.Etiqueta d = writer.reservaEtiqueta();
		CodeWriter.Etiqueta q = writer.reservaEtiqueta();
		CodeWriter.Etiqueta qq = writer.reservaEtiqueta();
		variable.codeR(env, writer);
		//[[Distincion de casos]]
		for (int i=0;i<casos.size();i++){
			l = writer.reservaEtiqueta();
			writer.write("dpl");
			writer.write("ldc " + casos.get(i).getValor());
			writer.write("equ");
			writer.write(new InstEtiquetaSimple("fjp", l));
			writer.write("sli");
			writer.write("ldc " + (i+1));
			writer.write(new InstEtiquetaSimple("ujp", s));
			writer.marca(l);
		}
		//[[Default]]
		writer.write("sli");
		writer.write("ldc 0");
		//[[Switching]]
		writer.marca(s);
		writer.write("neg");
		writer.write(new InstEtiquetaSimple("ixj", q));
		
		//[[Switching-Default]]
		writer.marca(d);
		if (def != null) def.code(env, writer);
		writer.write(new InstEtiquetaSimple("ujp", qq));
		
		Stack<CodeWriter.Etiqueta> etiquetas = new Stack<>();
		for (Case c : casos) {
			etiquetas.push(writer.reservaEtiqueta());
			writer.marca(etiquetas.peek());
			c.code(env,writer);
			writer.write(new InstEtiquetaSimple("ujp", qq));
		}
		//[[Backjumps]]
		while (!etiquetas.empty())
			writer.write(new InstEtiquetaSimple("ujp",etiquetas.pop()));
		//[[Backjump default]]
		writer.marca(q);
		writer.write(new InstEtiquetaSimple("ujp",d));
		writer.marca(qq);
	}
	/**
	 * Computes the necessary stack length for this instruction.
	 */
	@Override
	public int evStackLength() {
		IntWrapper acc = new IntWrapper(0), max = new IntWrapper(0);
		variable.maxStack(acc, max);
		for (Case c: casos)
			max.i = Math.max(max.i, c.evStackLength());
		
		return max.i;
	}
}
