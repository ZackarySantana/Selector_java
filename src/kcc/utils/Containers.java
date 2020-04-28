package kcc.utils;

/**
 * Contains all container type classes
 * 
 * @author Zackary Santana
 */
public class Containers {
	
	/**
	 * The integer container type object
	 *
	 * @author Zackary Santana
	 */
	public static class IntegerContainer extends Container<Integer, Integer> {

		public IntegerContainer(Integer value1, Integer value2) {
			super(value1, value2);
		}
	}
	
	/**
	 * The double container type object
	 *
	 * @author Zackary Santana
	 */
	public static class DoubleContainer extends Container<Double, Double> {

		public DoubleContainer(Double value1, Double value2) {
			super(value1, value2);
		}
	}
}