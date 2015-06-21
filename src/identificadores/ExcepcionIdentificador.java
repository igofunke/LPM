package identificadores;

import errores.ExcepcionLPM;
/**
 * Exception thrown if an error occurs in the identifiers stage.
 *
 */
public class ExcepcionIdentificador extends ExcepcionLPM {

	public ExcepcionIdentificador(String msg) {
		super(msg);
	}
	
	public ExcepcionIdentificador(String msg, int fila) {
		super(msg,fila);
	}
	
	private static final long serialVersionUID = 1L;

}
