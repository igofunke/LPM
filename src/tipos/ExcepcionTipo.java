package tipos;

import errores.ExcepcionLPM;
/**
 * Exception thrown when an error occurs in the type checking stage.
 *
 */
public class ExcepcionTipo extends ExcepcionLPM {

	public ExcepcionTipo (String msg){
		super(msg);
	}
	
	public ExcepcionTipo (String msg, int fila){
		super(msg,fila);
	}
	
	private static final long serialVersionUID = 1L;

}
