package codigo;

/**
 * A complex instruction to be written. It is used by the CodeWriter.
 * It generally involves compiling the instruction because of some
 * additional information it needs (like a tag).
 *
 */
public class InstWriter {
	public InstWriter(String instruccion) {
		this._instruccion = instruccion;
	}
	/**
	 * The instruction is ready to be printed.
	 */
	public boolean ready(){
		return true;
	}
	/**
	 * Generates the instruction.
	 */
	public String compilaInstruccion (){
		return _instruccion;
	}
	protected String _instruccion;
}
