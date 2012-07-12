package de.unipotsdam.hpi.thorben.ppi.measure.process;

public class DoubleHelper implements TypeHelper<Double> {

	public Double create() {
		return new Double(0);
	}

	public Double add(Double summandA, Double summandB) {
		return summandA + summandB;
	}

	public Double divide(Double dividend, Double divisor) {
		return dividend / divisor;
	}

	public Double asType(Number n) {
		return new Double(n.doubleValue());
	}

}
