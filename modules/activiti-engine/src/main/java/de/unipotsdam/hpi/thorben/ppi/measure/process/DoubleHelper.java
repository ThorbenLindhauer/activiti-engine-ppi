package de.unipotsdam.hpi.thorben.ppi.measure.process;

public class DoubleHelper implements TypeHelper<Double> {

	@Override
	public Double create() {
		return new Double(0);
	}

	@Override
	public Double add(Double summandA, Double summandB) {
		return summandA + summandB;
	}

	@Override
	public Double divide(Double dividend, Double divisor) {
		return dividend / divisor;
	}

	@Override
	public Double asType(Number n) {
		return new Double(n.doubleValue());
	}

}
