package codigo;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
/**
 * Writes the code in a file. Makes sure that the instruction to be
 * written has its possibly associated tag calculated, storing it
 * in a buffer otherwise.
 */
public class CodeWriter {
	public CodeWriter(Writer writer) {
		this._w = writer;
	}
	
	/**
	 * Writes an instruction to the file or stores it in the buffer
	 * if there are unresolved tags.
	 * @param inst The instruction to be written
	 * @throws ExcepcionWriter
	 */
	public void write (String inst) throws ExcepcionWriter{
		pos++;
		if (buffer.size() > 0)
			buffer.add(new InstWriter(inst));
		else
			try {
				_w.write(inst + ";" + System.lineSeparator());
			} catch (IOException e) {
				throw new ExcepcionWriter("Error al escribir la instrucción: " + inst, e);
			}
	}
	/**
	 * Writes a complex instruction to the file or stores it in the
	 * buffer if there are unresolved tags.
	 * @param inst The instruction to be written
	 * @throws ExcepcionWriter
	 */
	public void write (InstWriter inst) throws ExcepcionWriter{
		pos++;
		if (buffer.size() > 0 || !inst.ready())
			buffer.add(inst);
		else
			try {
				_w.write(inst.compilaInstruccion() + ";n" + System.lineSeparator());
				pos++;
			} catch (IOException e) {
				throw new ExcepcionWriter("Error al escribir la instrucción: " + inst.compilaInstruccion(), e);
			}
	}
	
	/**
	 * Creates a new tag to mark the code with
	 * @return The created tag
	 */
	public Etiqueta reservaEtiqueta (){
		return new Etiqueta();
	}
	/**
	 * Marks a tag in the current position of the writer
	 * @param etiqueta The tag to be marked
	 * @throws ExcepcionWriter
	 */
	public void marca (Etiqueta etiqueta) throws ExcepcionWriter{
		etiqueta.value(pos);
		flush();
	}
	/**
	 * Writes all the instructions in the buffer until it founds one
	 * with an unresolved tag
	 * @throws ExcepcionWriter
	 */
	private void flush () throws ExcepcionWriter{
		while (!buffer.isEmpty() && buffer.peek().ready())
			try {
				_w.write(buffer.pop().compilaInstruccion() + ";" + System.lineSeparator());
				pos++;
			} catch (IOException e) {
				throw new ExcepcionWriter("Error al escribir instrucción del buffer", e);
			}
	}
	/**
	 * Current position of the writer
	 * @return
	 */
	public int pos(){
		return pos;
	}
	
	private int pos = 0;
	private LinkedList<InstWriter> buffer = new LinkedList<>();
	private Writer _w;
	
	public class Etiqueta {
		protected Etiqueta () {}
		
		protected void value (int value){
			this._value = value;
		}
		public int value (){
			return this._value;
		}
		public boolean isSet (){
			return _value == -1 ? false : true;
		}
		private int _value = -1;
	}

}
