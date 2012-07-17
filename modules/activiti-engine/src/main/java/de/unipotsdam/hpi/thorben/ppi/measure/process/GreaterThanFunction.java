package de.unipotsdam.hpi.thorben.ppi.measure.process;

public class GreaterThanFunction<N extends Number> extends AbstractTargetFunction<N> {

	public GreaterThanFunction(TypeHelper<N> typeHelper) {
		super(typeHelper);
	}

	public boolean applySpecific(N operator1, N operator2) {
		return helper.greaterThan(operator1, operator2);
	}
}