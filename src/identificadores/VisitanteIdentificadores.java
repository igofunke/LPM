package identificadores;

/**
 * Specific visitor that visits the syntax tree adding identifiers
 * to a symbol table and returning symbols when needed.
 *
 */
public class VisitanteIdentificadores extends VisitanteGramatica {
	
	private TablaSimbolos t;
	
	public VisitanteIdentificadores (){
		t = new TablaSimbolos();
	}
	
	@Override
	public void entraBloque() {
		t.abreBloque();	
	}
	
	@Override
	public void salBloque() {
		t.cierraBloque();
	}
	
	/**
	 * Visits a symbol. Inserts it in the symbol table.
	 */
	@Override
	public void visitaSimbolo(Simbolo simbolo) {
		t.insertar(simbolo.getIdentificador(), simbolo);	
	}
	/**
	 * Visits an identifier. Returns the symbol associated.
	 */
	@Override
	public Simbolo visitaIdentificador(String id) throws ExcepcionIdentificador{
		return t.buscaId(id);
	}
	
}
