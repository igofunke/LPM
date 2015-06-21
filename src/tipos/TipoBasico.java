package tipos;

import errores.ErrorDispatcher;

/**
 * Represents a basic type: int,bool,void...
 *
 */
public class TipoBasico extends Tipo {

	public static TipoBasico INT = new TipoBasico("int");
	public static TipoBasico BOOL = new TipoBasico("bool");
	public static TipoBasico REAL = new TipoBasico("real");
	public static TipoBasico VOID = new TipoBasico("void");
	
	private TipoBasico (String tipo){
		this.tipo = tipo;
	}
	/**
	 * The class of the type.
	 */
	@Override
	public Clases clase() {
		return Clases.Basico;
	}
	/**
	 * The size of the type.
	 */
	@Override
	public int tam() {
		return 1;
	}
	/**
	 * No subtypes. Returns type VOID.
	 */
	@Override
	public Tipo desempaquetar(String context) {
		ErrorDispatcher.getInstance().sendErrorTipos("Se esperaba un nivel más de tipos",1);
		return TipoBasico.VOID;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TipoBasico){
			TipoBasico tmp = (TipoBasico)obj;
			if (tmp.tipo.equals(tipo)) return true;
		}
		
		return false;
	}
	
	private String tipo;

}
