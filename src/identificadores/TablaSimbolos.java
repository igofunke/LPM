package identificadores;


import java.util.HashMap;
import java.util.Stack;
import java.util.Vector;

/**
 * Stores the identifiers for each program block.
 *
 */
public class TablaSimbolos{
	

	public TablaSimbolos(){
		lista = new Vector<Ambito>();
		tabla = new HashMap<String, Stack<Ambito>>();
		abreBloque();
	}
	/**
	 * Tells the table that a new block is being entered.
	 */
	public void abreBloque(){
		Ambito ambito = new Ambito();
		lista.add(ambito);
	}
	/**
	 * Tells the table that the last block was left.
	 */
	public void cierraBloque(){
		Ambito ambito = lista.lastElement();
		Ambito aux = new Ambito();
		lista.remove(lista.size()-1);
		while(ambito.getPuntero() != null){	
			aux = tabla.get(ambito.getPuntero()).pop();
			ambito = aux;
		}
	}
	/**
	 * Stores a new identifier.
	 * @param id The identifier to be stored.
	 * @param simbolo The symbol associated with the identifier.
	 */
	public void insertar(String id, Simbolo simbolo){
		Ambito ambito = new Ambito();
		if (tabla.containsKey(id)){
			Ambito aux = lista.lastElement();
			while(aux.getPuntero()!=null){
				aux = tabla.get(aux.getPuntero()).peek();
			}
			ambito.setSimbolo(simbolo);
			aux.setPuntero(id);
			tabla.get(id).push(ambito);
		}
		else{
			Stack<Ambito> st = new Stack<Ambito>();
			ambito.setSimbolo(simbolo);
			lista.lastElement().setPuntero(id);
			st.add(ambito);
			tabla.put(id, st);
		}
	}
	/**
	 * Returns the symbol associated with the identifier provided.
	 * @param id
	 * @return
	 * @throws ExcepcionIdentificador If the id does not exits.
	 */
	Simbolo buscaId(String id) throws ExcepcionIdentificador{
		Simbolo simbolo = null;
		if(tabla.containsKey(id))
			simbolo = tabla.get(id).peek().getSimbolo();
		else throw new ExcepcionIdentificador("No existe el identificador");
		return simbolo;
	}
	
	private HashMap<String, Stack<Ambito>> tabla;
	private Vector<Ambito> lista;
}
