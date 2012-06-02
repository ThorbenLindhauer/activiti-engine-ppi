package de.unipotsdam.hpi.thorben.ppi.measure.process;

public interface TypeHelper<T extends Number> {

	T create();
	
	T add(T summandA, T summandB);
	
	T divide(T dividend, T divisor);
	
	T asType(Number n);
	
}
