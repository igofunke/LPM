package tipos;

import identificadores.ExcepcionIdentificador;
import identificadores.VisitanteGramatica;

/**
 * Represents the array type.
 *
 */
public class TipoArray extends Tipo{
	public TipoArray (Tipo subtipo, Integer dimension){
		this.subtipo = subtipo;
		this.dimension = dimension;
	}
	/**
	 * The class of the type.
	 */
	@Override
	public Clases clase() {
		return Clases.Array;
	}
	/**
	 * The size of the type.
	 */
	@Override
	public int tam() {
		return dimension*subtipo.tam();
	}
	/**
	 * Returns the subtype.
	 */
	@Override
	public Tipo desempaquetar(String context) {
		return subtipo;
	}
	/**
	 * Visits the subtype.
	 */
	@Override
	public void aceptaVisitante(VisitanteGramatica v)
			throws ExcepcionIdentificador {
		subtipo.aceptaVisitante(v);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TipoArray){
			TipoArray tmp = (TipoArray) obj;
			return tmp.subtipo.equals(subtipo);
		}
		return false;
	}
	/**
	 * Components of the type.
	 */
	private Integer dimension;
	private Tipo subtipo;
}
