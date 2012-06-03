package de.unipotsdam.hpi.thorben.ppi.measure.process;

public interface ProcessMeasure<T extends Number> {

	String getId();
	
	T calculate();
}
