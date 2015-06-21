package sabstracta;

import tipos.Tipo;
import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.IntWrapper;

/**
 * Represents an expression in the syntax tree.
 *
 */
public abstract class Expresion extends Elemento {
	/**
	 * Returns the type of the expression.
	 */
	public Tipo getTipo (){
		if (tipo == null)
			tipo = calculaTipo();
		
		return tipo;
	}
	/**
	 * Computes the type of the expression.
	 */
	protected abstract Tipo calculaTipo();
	
	/**
	 * Generates the codeR for the expression.
	 * @param env Current environment of identifiers.
	 * @param writer Codewriter used to write the code.
	 * @throws ExcepcionWriter If there is a writting error.
	 */
	public abstract void codeR(Entorno env, CodeWriter writer) throws ExcepcionWriter;
	/**
	 * Generates the codeA for the expression.
	 * @param env Current environment of identifiers.
	 * @param writer Codewriter used to write the code.
	 * @throws ExcepcionWriter If there is a writting error.
	 */
	public void codeA (Entorno env, CodeWriter writer) throws ExcepcionWriter{
		//Para los tipos básicos, por comodidad
		//Reescribir para los no básicos
		codeR(env, writer);
	}
	/**
	 * Computes the max. stack length needed for the computation of the expression. <<max>> will
	 * contain that amount.
	 * @param acc When the function returns <<acc>> will have been increased by the amount of
	 * stack positions it needs.
	 * @param max When the function returns, <<max>> will contain the maximum value of <<acc>>.
	 */
	protected abstract void maxStack (IntWrapper acc, IntWrapper max);
	
	private Tipo tipo;
}
