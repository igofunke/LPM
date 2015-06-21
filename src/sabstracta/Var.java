package sabstracta;

import tipos.Clases;
import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
/**
 * Represents a lefthand expression in the syntax tree.
 *
 */
public abstract class Var extends Expresion{
	/**
	 * Generates the codeL for this lefthand expression it represents.
	 * @param env Current environment of identifiers.
	 * @param writer Codewriter used to write the code.
	 * @throws ExcepcionWriter If there is a writting error.
	 */
	public abstract void codeL(Entorno env, CodeWriter writer) throws ExcepcionWriter;
	
	/**
	 * Generates the codeR for the expression.
	 * @param env Current environment of identifiers.
	 * @param writer Codewriter used to write the code.
	 * @throws ExcepcionWriter If there is a writting error.
	 */
	@Override
	public void codeR(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		this.codeL(env,writer);
		writer.write("ind");
	}
	/**
	 * Generates the codeA for the expression.
	 * @param env Current environment of identifiers.
	 * @param writer Codewriter used to write the code.
	 * @throws ExcepcionWriter If there is a writting error.
	 */
	@Override
	public void codeA(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		if (this.getTipo().clase().equals(Clases.Basico))
			codeR(env, writer);
		else codeL(env, writer);
	}
}
