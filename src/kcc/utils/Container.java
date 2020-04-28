package kcc.utils;

/**
 * The master container class
 * 
 * @author Zackary Santana
 * @param <E> The first value type
 * @param <T> The second value type
 */
public class Container<E, T> {

	private E _value1;
	private T _value2;

	/**
	 * Initializes the two values
	 *
	 * @param value1 The first value
	 * @param value2 The second value
	 */
	public Container(E value1, T value2) {
		this._value1 = value1;
		this._value2 = value2;
	}

	/**
	 * @return The first value
	 */
	public E getValue1() {
		return this._value1;
	}

	/**
	 * Sets the value of the first input
	 *
	 * @param value1 The new first value
	 * @return The old first value
	 */
	public E setValue1(E value1) {
		E oldValue = this._value1;
		this._value1 = value1;
		return oldValue;
	}

	/**
	 * @return The second value
	 */
	public T getValue2() {
		return this._value2;
	}

	/**
	 * Sets the value of the second input
	 *
	 * @param value2 The new second value
	 * @return The old second value
	 */
	public T setValue2(T value2) {
		T oldValue = this._value2;
		this._value2 = value2;
		return oldValue;
	}
}