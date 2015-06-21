package codigo;
import codigo.CodeWriter.Etiqueta;

/**
 * An instruction to be written by the writer that internally stores
 * a tag to be calculated at some point.
 * @author Ignacio
 *
 */
public class InstEtiquetaSimple extends InstWriter {
	/**
	 * @param instruccion The instruction to be written
	 * @param etiqueta The tag that is associated with
	 */
	public InstEtiquetaSimple(String instruccion, Etiqueta etiqueta) {
		super(instruccion);
		this._etiqueta = etiqueta;
	}
	
	/**
	 * The tag has been marked in the code and the instruction can
	 * be safely written.
	 */
	@Override
	public boolean ready() {
		return _etiqueta.isSet();
	}
	
	/**
	 * Returns the instruction with the tag printed.
	 */
	@Override
	public String compilaInstruccion() {
		return this._instruccion + " " + _etiqueta.value();
	}
	
	private Etiqueta _etiqueta;
}
