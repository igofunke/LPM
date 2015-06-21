package codigo;

import errores.ExcepcionLPM;

/**
 * Exception thrown by the CodeWriter. Currently its an alias for
 * a IOException.
 */
public class ExcepcionWriter extends ExcepcionLPM{
	public ExcepcionWriter(String msg) {
		super(msg);
	}
	
	public ExcepcionWriter (String msg, Throwable cause){
		super(msg,cause);
	}
	
	private static final long serialVersionUID = 6636049675637340104L;
}
