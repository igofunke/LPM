package sabstracta;

import identificadores.Simbolo;
import identificadores.VisitanteGramatica;

import java.util.Vector;

import tipos.Tipo;
import tipos.TipoBasico;
import tipos.TipoFuncion;
import codigo.CodeWriter;
import codigo.ContextType;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.InstEtiquetaSimple;

/**
 * Represents a function in the syntax tree.
 *
 */
public class Funcion extends Elemento implements Simbolo{

	public Funcion(String _iden, Vector<Definicion> _sarg, Tipo _ret,
			Vector<Funcion> _sfun, Vector<Definicion> _sdef, Bloque _bloque) {
		this.bloque = _bloque;
		this.argumentos = _sarg;
		this.definiciones = _sdef;
		this.funciones = _sfun;
		this.devuelto = _ret;
		this.identificador = _iden;
		
		for (Definicion d: argumentos) d.setIsArgument(true);
	}

	public String getIdentificador() {
		return this.identificador;
	}
	
	public Tipo getTipoDevuelto(){
		return devuelto;
	}
	/**
	 * Components of a function: 
	 * func identificador (argumentos) : devuelto
	 * 	definiciones
	 * 	funciones
	 * [
	 * 	bloque
	 * ]
	 */
	private String identificador;
	private Vector<Definicion> argumentos;
	private Vector<Funcion> funciones;
	private Vector<Definicion> definiciones;
	private Tipo devuelto;
	private Bloque bloque;
	
	/**
	 * Generates the associated type.
	 */
	@Override
	public Tipo compilaTipo() {
		Vector<Tipo> tipos = new Vector<>();
		for (Definicion d:argumentos)tipos.add(d.compilaTipo());
		return new TipoFuncion(tipos, devuelto);
	}
	/**
	 * Registers itself as a symbol and visits its components.
	 */
	@Override
	public void aceptaVisitante(VisitanteGramatica v) {
		v.visitaSimbolo(this);
		v.entraBloque();
		for (Definicion d: argumentos) d.aceptaVisitante(v);
		for (Definicion d:definiciones)d.aceptaVisitante(v);
		for (Funcion f: funciones) f.aceptaVisitante(v);
		bloque.aceptaVisitante(v);
		v.salBloque();
	}
	/**
	 * Checks the types of its components.
	 */
	public void compruebaTipos() {
		bloque.compruebaTipos();
		for (Funcion f: funciones) f.compruebaTipos();
	}
	/**
	 * Returns the definitions that are part of the function regarding
	 * the computation of its type.
	 * In this case the arguments of the function.
	 */
	@Override
	public Vector<Definicion> elementos() {
		return argumentos;
	}
	/**
	 * Computes the static zone length.
	 */
	private int staticZoneLength(){
		int l = 5; //Parte organizativa
		l += argumentos.size(); //Tipos primitivos o refs
		for (Definicion d: definiciones) l += d.compilaTipo().tam();
		
		return l;
	}
	/**
	 * Computes the maximum stack length needed.
	 */
	private int evStackLength (){
		return bloque.evStackLength();
	}
	/**
	 * Generates the code of the function.
	 */
	public void code (Entorno env, CodeWriter writer) throws ExcepcionWriter{
		env.asignarFuncion(identificador, writer.pos());
		env = env.newEnv();
		//[[Identificación de la función]]
		if (devuelto.equals(TipoBasico.VOID)) env.contextType(ContextType.Procedure);
		else env.contextType(ContextType.Function);
		//[[Secuencia de entrada]]
		writer.write("ssp " + staticZoneLength());
		writer.write("sep " + evStackLength());
		CodeWriter.Etiqueta dp = writer.reservaEtiqueta();
		writer.write(new InstEtiquetaSimple("ujp",dp));
		//[[Declaraciones anidadas]]
		for (Definicion d: argumentos) d.code(env, writer);
		for (Definicion d: definiciones) d.code(env, writer);
		for (Funcion f: funciones) f.code(env, writer);
		//[[Código del cuerpo]]
		writer.marca(dp);
		bloque.code(env, writer);
		writer.write("retp");
	}
}
