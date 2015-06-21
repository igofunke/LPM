package sabstracta;

import identificadores.VisitanteGramatica;

/**
 * General element type for all the elements in the syntax tree.
 * @author Ignacio
 *
 */
public abstract class Elemento {	
	public abstract void aceptaVisitante (VisitanteGramatica v) ;
	
	public void setFila (int fila) {
		this.fila = fila;
	}
	public int getFila (){
		return this.fila;
	}
	private int fila;
}
