package sabstracta;

import identificadores.ExcepcionIdentificador;
import identificadores.Simbolo;
import identificadores.VisitanteGramatica;

import java.util.Vector;

import tipos.Clases;
import tipos.TipoBasico;
import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.IntWrapper;
import errores.ErrorDispatcher;

/**
 * Represents the call instruction in the syntax tree.
 *
 */
public class Call extends Instruccion {

	public Call(String _iden, Vector<Expresion> _sec) {
		this.argumentos = _sec;
		this.identificador = _iden;
	}
	public Call(String _iden, Vector<Expresion> _sec, Var _var) {
		this.argumentos = _sec;
		this.identificador = _iden;
		this.var = _var;
	}
	/**
	 * Instruction components: call identificador (argumentos) -> var
	 */
	private String identificador;
	private Vector<Expresion> argumentos;
	private Var var;
	
	/**
	 * Visits the instruction. Inspects the identifier <<identificador>>.
	 */
	@Override
	public void aceptaVisitante(VisitanteGramatica v) {
		try {
			ref = v.visitaIdentificador(identificador);
		} catch (ExcepcionIdentificador e1) {
			ErrorDispatcher.getInstance().sendErrorIdentificacion(e1.getMessage(), getFila());
		}
		for (Expresion e:argumentos)
			e.aceptaVisitante(v);
		if (var != null) var.aceptaVisitante(v);
	}
	private Simbolo ref;
	
	/**
	 * Check that the types of its components are correct.
	 */
	@Override
	public void compruebaTipos() {
		if (ref.compilaTipo().clase() != Clases.Funcion)
			ErrorDispatcher.getInstance().sendErrorTipos("Se esperaba una función.", getFila());
		
		Vector<Definicion> parametros = ref.elementos();
		if (parametros.size() != argumentos.size())
			ErrorDispatcher.getInstance().sendErrorTipos("Número de parámetros incorrecto.", getFila());
		
		for (int i=0; i<parametros.size(); i++)
			if (!(parametros.get(i).compilaTipo().equals(argumentos.get(i).getTipo())))
				ErrorDispatcher.getInstance().sendErrorTipos("Los tipos de los argumentos de la función no coinciden.", getFila());
		
		if (var != null && !var.getTipo().equals(((Funcion)ref).getTipoDevuelto()))
			ErrorDispatcher.getInstance().sendErrorTipos("La asignación en el call tiene que ser del mismo tipo que el que devuelve la función", getFila());
	}
	/**
	 * Generates the code for this instruction.
	 */
	@Override
	public void code(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		if (var != null) var.codeL(env, writer);
		//[[Secuencia de llamada]]
		writer.write("mst " + (env.n() - env.def(identificador)));
		for (Expresion e: argumentos)
			e.codeA(env, writer);
		writer.write("cup " + argumentos.size() + " " + env.pos(identificador));
		if (var != null) writer.write("sto");
		else if (!((Funcion)ref).getTipoDevuelto().equals(TipoBasico.VOID))
			writer.write("sli");
	}
	/**
	 * Computes the necessary stack length for this instruction.
	 */
	@Override
	public int evStackLength() {
		IntWrapper acc = new IntWrapper(0),max = new IntWrapper(0);
		if (var != null) var.maxStack(acc, max);
		acc.i += 5;
		max.i = Math.max(acc.i, max.i);
		for (Expresion e: argumentos)
			e.maxStack(acc, max);
		
		return max.i;
	}
}
