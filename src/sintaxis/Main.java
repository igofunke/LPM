package sintaxis;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import lexico.AnalizadorLexicoLPM;
import sabstracta.Programa;
import codigo.ExcepcionWriter;
import errores.ErrorDispatcher;

public class Main {
	public static void main(String[] args){
		Reader input = null;
		Writer output = null;
		try {
			if (args.length != 2) {
				System.out.println("Uso: lmp archivoEntrada archivoSalida");
				System.exit(0);
			} 
			input = new InputStreamReader(
					new FileInputStream(args[0])
					);
			output = new BufferedWriter(
					new OutputStreamWriter(
							new FileOutputStream(args[1])));

			AnalizadorLexicoLPM al = new AnalizadorLexicoLPM(input);
			AnalizadorSintacticoLPM asint = new AnalizadorSintacticoLPM(al);
			asint.setScanner(al);

			Programa g = (Programa)asint.parse().value;
			g.loadImports();
			g.calculaSimbolos();
			if (ErrorDispatcher.getInstance().error()){
				System.err.flush();
				System.out.println("No se pudo completar la compilación, hay errores.");
				input.close();
				output.close();
				System.exit(1);
			}
			g.compruebaTipos();
			if (ErrorDispatcher.getInstance().error()){
				System.err.flush();
				System.out.println("No se pudo completar la compilación, hay errores.");
				input.close();
				output.close();
				System.exit(2);
			}
			g.compila(output);
			System.out.println("Compilación completada con éxito");

			input.close();
			output.close();
		} catch (IOException e){
			if (output == null){
				System.err.println("Error al abrir el archivo de entrada.");
				System.exit(3);
			}
			else {
				System.err.println("Error al abrir el archivo de salida.");
				System.exit(4);
			}

		} catch (ExcepcionWriter e) {
			System.err.println("Error en fase de compilación: " + e.getMessage());
			System.err.flush();
			System.out.println("No se pudo completar la compilación, hay errores.");
			System.exit(5);

		} catch (Exception e){
			System.err.println("Error de parseado: " + e.getMessage());
			e.printStackTrace();
		}
	}
}   

