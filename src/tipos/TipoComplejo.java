package tipos;

import identificadores.ExcepcionIdentificador;
import identificadores.VisitanteGramatica;
/**
 * It can represent register types or function types.
 *
 */
public class TipoComplejo extends Tipo {
	
	public TipoComplejo(String id) {
		this.id = id;
	}
	private String id;
	private Tipo tipoConcreto;
	/**
	 * The size of the type.
	 */
	@Override
	public int tam() {
		return tipoConcreto.tam();
	}

	@Override
	public Clases clase() {
		return tipoConcreto.clase();
	}
	
	@Override
	public Tipo desempaquetar(String context) {
		return tipoConcreto.desempaquetar(context);
	}
	
	@Override
	public Tipo simplificar() {
		return tipoConcreto;
	}
	
	@Override
	public boolean equals(Object obj) {
		return tipoConcreto.equals(obj);
	}

	@Override
	public void aceptaVisitante(VisitanteGramatica v) throws ExcepcionIdentificador {
		tipoConcreto = v.visitaIdentificador(id).compilaTipo();
	}

}
