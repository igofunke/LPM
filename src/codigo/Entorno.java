package codigo;

import java.util.TreeMap;

/**
 * Stores the identifiers of the current block with its position
 * in memory and the level where it was defined. 
 *
 */
public class Entorno {
	
	/**
	 * Creates a new environment, cloning the stored identifiers.
	 * Used when entering a new block.
	 * @return The new environment.
	 */
	@SuppressWarnings("unchecked")
	public Entorno newEnv (){
		Entorno env = new Entorno();
		env.n = n+1;
		env.map = (TreeMap<String, Info>) map.clone();
		
		return env;
	}
	/**
	 * Current nesting level.
	 */
	public int n (){
		return this.n+1;
	}
	/**
	 * Assigns the identifier <<identificador>> to the next available
	 * position in the memory based on the size <<tam>> of its type.
	 * @param identificador
	 * @param tam
	 */
	public void asignar (String identificador, int tam){
		map.put(identificador, new Info(cur, n+1,false));
		cur += tam;
	}
	/**
	 * Assigns the identifier <<identificador>> to the next available
	 * position in the memory based on the size <<tam>> of its type.
	 * Also sets if it is an argument provided by reference or not.
	 * @param identificador
	 * @param tam
	 */
	public void asignar (String identificador, int tam, boolean ref){
		if (ref) tam = 1;
		map.put(identificador, new Info(cur, n+1,ref));
		cur += tam;
	}
	/**
	 * Assigns the identifier <<identificador>> to the given absolute
	 * position <<mem>>.
	 * @param identificador
	 * @param mem
	 */
	public void asignarAbs (String identificador, int mem){
		map.put(identificador, new Info(mem, n+1,false));
	}
	/**
	 * Stores a function identifier. The information is treated differently,
	 * because the position refers to the position in the code of the function.
	 * @param identificador
	 * @param pos
	 */
	public void asignarFuncion (String identificador, int pos){
		map.put(identificador, new Info(pos, n+1,false));
	}
	/**
	 * The position in the memory/code of the identifier.
	 * @param identificador
	 * @return
	 */
	public int pos (String identificador){
		return map.get(identificador).pos;
	}
	/**
	 * The level in which the identifier was defined.
	 * @param identificador
	 * @return
	 */
	public int def (String identificador){
		return map.get(identificador).def;
	}
	/**
	 * True if the identifier is an argument of a function provided
	 * by reference.
	 * @param identificador
	 * @return
	 */
	public boolean ref (String identificador){
		return map.get(identificador).ref;
	}
	/**
	 * Sets the context type of the block associated with this environment.
	 * @param type
	 */
	public void contextType (ContextType type){
		this.contextType = type;
	}
	/**
	 * Returns the context type of the block associated with this environment.
	 */
	public ContextType contextType (){
		return contextType;
	}
	
	private int n = 0, cur = 5;
	private ContextType contextType = ContextType.Procedure;
	private TreeMap<String, Info> map = new TreeMap<String,Info>();
	/**
	 * Info (position and definition level for an identifier)
	 *
	 */
	private class Info {
		public Info(int pos, int def, boolean ref) {
			this.pos = pos;
			this.def = def;
			this.ref = ref;
		}
		public int pos;
		public int def;
		public boolean ref;
	}
}
