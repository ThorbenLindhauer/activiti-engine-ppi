package de.unipotsdam.hpi.thorben.ppi.measure.process;

public interface TargetFunction<N extends Number> {

	boolean apply(N operator1, N operator2);
}
