package tipos;

import identificadores.VisitanteGramatica;

import java.util.Vector;
/**
 * Represents the function type.
 *
 */
public class TipoFuncion extends Tipo{
	
	public TipoFuncion(Vector<Tipo> argumentos, Tipo devuelto) {
		this.argumentos = argumentos;
		this.tdevuelto = devuelto;
	}
	/**
	 * Components of the type.
	 */
	private Vector<Tipo> argumentos;
	private Tipo tdevuelto;
	/**
	 * The size of the type.
	 */
	@Override
	public int tam() {
		return 1;
	}
	/**
	 * The class of the type.
	 */
	@Override
	public Clases clase() {
		return Clases.Funcion;
	}
	/**
	 * Returns the subtype: the returning type.
	 */
	@Override
	public Tipo desempaquetar(String context) {
		return tdevuelto;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TipoComplejo)
			return obj.equals(this);
		
		boolean eq = true;
		if (obj instanceof TipoFuncion){
			TipoFuncion tmp = (TipoFuncion) obj;
			eq &= tmp.tdevuelto.equals(tdevuelto);
			if (argumentos.size() != tmp.argumentos.size()) eq = false;
			for (int i = 0; i<argumentos.size();i++)
				eq &= argumentos.get(i).equals(tmp.argumentos.get(i));
		}
		else eq = false;
		
		return eq;
	}

	@Override
	public void aceptaVisitante(VisitanteGramatica v) {}

}
