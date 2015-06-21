package lexico;

import java_cup.runtime.Symbol;

/**
 * Represents a lexical unit.
 *
 */
public class UnidadLexica extends Symbol {
   private int fila;
   public UnidadLexica(int fila, int clase, String lexema) {
     super(clase,lexema);
	 this.fila = fila;
   }
   /**
    * Internal symbol associated with this unit.
    */
   public int clase () {return sym;}
   /**
    * Lexeme for this unit.
    */
   public String lexema() {return (String)value;}
   /**
    * The position (row) in the file.
    */
   public int fila() {return fila;}
   
   @Override
	public String toString() {
		return "" + fila() + "- Unidad Léxica: " + lexema();
	}
}
