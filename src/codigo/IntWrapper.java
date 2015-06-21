package codigo;

/**
 * Wraps an int into a class. Used to pass an int by reference to
 * a function (Java doesn't provide a built in way to achieve this).
 *
 */
public class IntWrapper {
	public IntWrapper (int i){this.i = i;}
	public int i;
}
