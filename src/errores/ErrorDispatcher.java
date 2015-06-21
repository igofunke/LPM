package errores;

import lexico.UnidadLexica;

/**
 * Manages the identifiers, types and compiling stages errors.
 * Currently it prints them to the standard error output.
 *
 */
public class ErrorDispatcher {
	private ErrorDispatcher(){}
	
	/**
	 * We are using this class as a singleton.
	 */
	public static ErrorDispatcher getInstance(){
		if (instance == null)
			instance = new ErrorDispatcher();
		return instance;
	}
	/**
	 * Manages types stage errors.
	 * @param msg The msg explaining the error.
	 * @param fila The row in file in which the error is located.
	 */
	public void sendErrorTipos(String msg, int fila){
		error = true;
		System.err.println("[" + module + "]" + 
				" Error en fase de comprobación de tipos (fila " +
				fila +"): " + msg);
	}
	/**
	 * Manages identifiers stage errors.
	 * @param msg The msg explaining the error.
	 * @param fila The row in file in which the error is located.
	 */
	public void sendErrorIdentificacion(String msg, int fila){
		error = true;
		System.err.println("[" + module + "]" + 
				" Error en fase de identificación "
				+ "de identificadores (fila " + fila +"): " + msg);
	}
	/**
	 * Manages compiling stage errors.
	 * @param msg The msg explaining the error.
	 * @param fila The row in file in which the error is located.
	 */
	public void sendErrorCompilacion(String msg){
		error = true;
		System.err.println("[" + module + "]" + 
				" Error en fase de compilación: " + msg);
	}
	/**
	 * Manages lexical errors. Lexical errors are fatal errors, therefore
	 * the compilation is stopped immediately.
	 * @param fila The row in the file in which the error occurred.
	 * @param lexema Not expected lexeme
	 */
	public void sendLexicalError(int fila, String lexema){
		System.err.println("[" + module + "]" + 
				" Error léxico en la fase de parseado (fila  "+
				fila +") - Caracter inexperado: "+lexema);
		System.out.println("No se pudo completar la compilación, hay errores.");
		System.exit(8);
	}
	/**
	 * Manages syntax errors. Syntax errors are fatal errors, therefore
	 * the compilation is stopped immediately.
	 * @param unidadLexica Not expected lexical unit.
	 */
	public void sendSyntaxError (UnidadLexica unidadLexica){
		System.err.println("[" + module + "]" + 
				" Error sintáctico en la fase de parseado (fila  " + 
				unidadLexica.fila() +") - Elemento inexperado: "+
				unidadLexica.lexema());
		System.out.println("No se pudo completar la compilación, hay errores.");
		System.exit(9);
	}
	/**
	 * Checks if an error has occurred.
	 */
	public boolean error(){
		return error;
	}
	/**
	 * Sets the module that is being analyzed. All the errors sent
	 * refer to this concrete module.
	 */
	public void setModule(String module) {
		this.module = module;
	}
	
	private String module = "main";
	private boolean error = false;
	private static ErrorDispatcher instance;
}
