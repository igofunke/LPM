package errores;
/**
 * General exception type for this project.
 *
 */
public abstract class ExcepcionLPM extends Exception {
	public ExcepcionLPM (String msg){
		super(msg);
	}
	
	public ExcepcionLPM (String msg, int fila){
		super(msg);
		this.fila = fila;
	}
	
	public ExcepcionLPM (String msg, Throwable cause){
		super(msg,cause);
	}
	
	public int getFila (){
		return fila;
	}
	
	private int fila = -1;
	
	private static final long serialVersionUID = -5248683097223713723L;
}
