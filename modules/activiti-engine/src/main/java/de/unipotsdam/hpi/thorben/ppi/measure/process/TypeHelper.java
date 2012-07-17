package de.unipotsdam.hpi.thorben.ppi.measure.process;

public interface TypeHelper<T extends Number> {

	T createZero();
	
	T add(T summandA, T summandB);
	
	T divide(T dividend, T divisor);
	
	T asType(Number n);
	
	T createMaxValue();
	
	T createMinValue();
	
	/**
	 * True, if valueA is greater than valueB.
	 * @param valueA
	 * @param valueB
	 * @return
	 */
	boolean greaterThan(T valueA, T valueB);
	
	/**
	 * True, if valueA lower than valueB.
	 * @param valueA
	 * @param valueB
	 * @return
	 */
	boolean lowerThan(T valueA, T valueB);
	
	T parseType(String s);
}
