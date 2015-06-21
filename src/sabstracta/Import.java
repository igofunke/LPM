package sabstracta;

import identificadores.VisitanteGramatica;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Vector;

import lexico.AnalizadorLexicoLPM;
import sintaxis.AnalizadorSintacticoImports;
import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import errores.ErrorDispatcher;

/**
 * Represents an import statement in the syntax tree.
 *
 */
public class Import extends Elemento {
	
	public Import (String _cad){
		this.path = _cad.substring(1, _cad.length()-1);
	}
	/**
	 * Components of the statement: import "path"
	 */
	private String path;
	/**
	 * Components of the import syntax tree once loaded
	 */
	private boolean loaded = false;
	private Vector<Registro> registers;
	private Vector<Funcion> functions;

	/**
	 * Visits the registers and functions of the import in
	 * order to identify identifiers.
	 */
	@Override
	public void aceptaVisitante(VisitanteGramatica v) {
		if (!loaded) load();
		ErrorDispatcher.getInstance().setModule(path);
		for (Registro r: registers) r.aceptaVisitante(v);
		for (Funcion f : functions) f.aceptaVisitante(v);
		ErrorDispatcher.getInstance().setModule("main");
	}
	/**
	 * Checks if the types of its elements are correct.
	 */
	public void compruebaTipos() {
		if (!loaded) load();
		ErrorDispatcher.getInstance().setModule(path);
		for (Funcion f : functions) f.compruebaTipos();
		ErrorDispatcher.getInstance().setModule("main");
	}
	/**
	 * Generates code for its elements.
	 */
	public void code(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		if (!loaded) load();
		ErrorDispatcher.getInstance().setModule(path);
		for (Registro r: registers) r.code(env, writer);
		for (Funcion f : functions) f.code(env, writer);
		ErrorDispatcher.getInstance().setModule("main");
	}
	/**
	 * Loads the file that the import refers to and adds its
	 * functions and registers to the syntax tree.
	 */
	public void load (){
		if (loaded) return;
		
		Reader input;
		try {
			input = new InputStreamReader(
					new FileInputStream(path)
					);
			ErrorDispatcher.getInstance().setModule(path);
			AnalizadorLexicoLPM al = new AnalizadorLexicoLPM(input);
			AnalizadorSintacticoImports asint = new AnalizadorSintacticoImports(al);
			asint.setScanner(al);

			Aux a = (Aux)asint.parse().value;
			ErrorDispatcher.getInstance().setModule("main");
			this.registers = a.registers;
			this.functions = a.functions;
			
			loaded = true;
		} catch (FileNotFoundException e) {
			System.err.println("No se pudo cargar el import: " + path);
			System.exit(6);
		} catch (Exception e) {
			System.err.println("Error al parsear el import: " + path);
			System.exit(7);
		}
	}
	/**
	 * Auxiliary class to store the functions and registers of
	 * the imported file. They will be copied into the import element
	 * once loaded.
	 *
	 */
	public static class Aux {
		public Aux (Vector<Registro> registers, Vector<Funcion> functions){
			this.registers = registers;
			this.functions = functions;
		}
		
		protected Vector<Registro> registers;
		protected Vector<Funcion> functions;
	}
}
