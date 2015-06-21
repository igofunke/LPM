package sabstracta;

import identificadores.ExcepcionIdentificador;
import identificadores.Simbolo;
import identificadores.VisitanteGramatica;

import java.util.Vector;

import tipos.Clases;
import tipos.Tipo;
import codigo.CodeWriter;
import codigo.Entorno;
import errores.ErrorDispatcher;

/**
 * Represents a variable definition in the syntax tree.
 *
 */
public class Definicion extends Elemento implements Simbolo{

	public Definicion(String _iden, Tipo _tipo) {
		this.identificador = _iden;
		this.tipo = _tipo;
	}
	
	public String getIdentificador (){
		return this.identificador;
	}
	/**
	 * Components of the definition: tipo identificador.
	 */
	private String identificador;
	private Tipo tipo;
	/**
	 * Returns the type stated at the definition.
	 */
	@Override
	public Tipo compilaTipo() {
		return tipo;
	}
	/**
	 * Registers itself as a symbol and visits its components.
	 */
	@Override
	public void aceptaVisitante(VisitanteGramatica v) {
		v.visitaSimbolo(this);
		try {
			tipo.aceptaVisitante(v);
		} catch (ExcepcionIdentificador e) {
			ErrorDispatcher.getInstance().sendErrorIdentificacion("El tipo complejo no está declarado.", getFila());
		}
	}
	/**
	 * Returns nothing. A definition its not comprised of more definitions.
	 */
	@Override
	public Vector<Definicion> elementos() {
		return new Vector<Definicion>();
	}
	/**
	 * Allocates memory for this definition in the environment <<env>>
	 */
	public void code(Entorno env, CodeWriter writer) {
		boolean ref = false;
		if (arg && !tipo.clase().equals(Clases.Basico))
			ref = true;
		env.asignar(identificador, tipo.tam(),ref);
	}
	
	/**
	 * Sets if it is the definition of an argument of a function.
	 * @param b True if it is an argument, false otherwise.
	 */
	public void setIsArgument(boolean b){
		this.arg = b;
	}
	private boolean arg = false;
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Definicion){
			Definicion tmp = (Definicion) obj;
			if (identificador.equals(tmp.identificador) &&
					tipo.equals(tmp.tipo))
				return true;
		}
		return false;
	}
}
