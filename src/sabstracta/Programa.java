package sabstracta;

import identificadores.VisitanteIdentificadores;

import java.io.Writer;
import java.util.Vector;

import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.InstEtiquetaSimple;

/**
 * Class the represents the code itself. It is the root of the syntax tree.
 *
 */
public class Programa {

	public Programa(Vector<Import> _simp, Vector<Registro> _sreg, Vector<Funcion> _sfun,
			Vector<Definicion> _sdef, Vector<Instruccion> _sinst) {
		this.imports = _simp;
		this.registros = _sreg;
		this.funciones = _sfun;
		this.definiciones = _sdef;
		this.instrucciones = _sinst;
	}
	/**
	 * Components of a program:
	 * imports
	 * registros
	 * [definiciones]
	 * funciones
	 * instrucciones
	 */
	private Vector<Import> imports;
	private Vector<Registro> registros;
	private Vector<Funcion> funciones;
	private Vector<Definicion> definiciones;
	private Vector<Instruccion> instrucciones;
	
	/**
	 * Preload the imports to avoid loading them in the middle
	 * of another phase.
	 */
	public void loadImports(){
		for (Import i: imports) i.load();
	}
	
	/**
	 * Starts the identifiers stage.
	 */
	public void calculaSimbolos(){
		VisitanteIdentificadores v = new VisitanteIdentificadores();
		for (Import i: imports) i.aceptaVisitante(v);
		for (Registro r: registros) r.aceptaVisitante(v);
		for (Definicion d: definiciones) d.aceptaVisitante(v);
		for (Funcion f: funciones) f.aceptaVisitante(v);
		for (Instruccion i: instrucciones) i.aceptaVisitante(v);
	}
	/**
	 * Starts the type checking stage.
	 */
	public void compruebaTipos(){
		for (Import i: imports) i.compruebaTipos();
		for (Funcion f: funciones) f.compruebaTipos();
		for (Instruccion i: instrucciones) i.compruebaTipos();
	}
	/**
	 * Computes the static zone length.
	 */
	private int staticZoneLength(){
		int l = 5; //Parte organizativa
		for (Definicion d: definiciones) l += d.compilaTipo().tam();
		
		return l;
	}
	/**
	 * Computes the maximum stack length needed.
	 */
	private int evStackLength (){
		int l = 0;
		for (Instruccion i: instrucciones) l = Math.max(l, i.evStackLength());
		
		return l;
	}
	/**
	 * Starts writing the code.
	 * @param w The writer in which the code will be output.
	 * @throws ExcepcionWriter If a code-writing error occurs. 
	 */
	public void compila (Writer w) throws ExcepcionWriter{
		Entorno env = new Entorno();
		CodeWriter writer = new CodeWriter(w);
		
		//[[Secuencia de entrada]]
		writer.write("ssp " + staticZoneLength());
		writer.write("sep " + evStackLength());
		CodeWriter.Etiqueta dp = writer.reservaEtiqueta();
		writer.write(new InstEtiquetaSimple("ujp",dp));
		//[[Declaraciones anidadas]]
		for (Import i: imports) i.code(env,writer);
		for (Registro r: registros) r.code(env, writer);
		for (Definicion d: definiciones) d.code(env, writer);
		for (Funcion f: funciones) f.code(env, writer);
		//[[Código del cuerpo]]
		writer.marca(dp);
		for (Instruccion i: instrucciones) i.code(env, writer);
		writer.write("stp");
	}
	
}
