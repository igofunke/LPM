package tipos;

import java.util.Vector;

import errores.ErrorDispatcher;
import sabstracta.Definicion;
/**
 * Represents a register type.
 *
 */
public class TipoRegistro extends Tipo {

	public TipoRegistro(Vector<Definicion> tipos, String id) {
		this.tipos = tipos;
		this.id = id;
	}
	/**
	 * Components of the type.
	 */
	private Vector<Definicion> tipos;
	private String id;
	
	public String getIdentificador(){
		return id;
	}
	/**
	 * The size of the type.
	 */
	@Override
	public int tam() {
		int t = 0;
		for (Definicion d: tipos) t+= d.compilaTipo().tam();
		
		return t;
	}
	/**
	 * Returns the type of its internal definition specified
	 * by context.
	 */
	@Override
	public Tipo desempaquetar(String context) {
		for (Definicion d: tipos)
			if (d.getIdentificador().equals(context))
				return d.compilaTipo();
		
		ErrorDispatcher.getInstance().sendErrorTipos("No existe el elemento de registro: " + context,-1);
		return TipoBasico.VOID;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TipoComplejo)
			return obj.equals(this);
		
		boolean eq = true;
		if (obj instanceof TipoRegistro){
			TipoRegistro tmp = (TipoRegistro) obj;
			for (int i=0;i<tipos.size();i++)
				eq &= tipos.get(i).equals(tmp.tipos.get(i));
		}
		else eq = false;
		
		return eq;
	}
	/**
	 * The class of the type.
	 */
	@Override
	public Clases clase() {
		return Clases.Registro;
	}

}
