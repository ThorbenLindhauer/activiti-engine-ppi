package de.unipotsdam.hpi.thorben.ppi.measure.process;

public class DoubleHelper implements TypeHelper<Double> {

	public Double createZero() {
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

	public Double createMaxValue() {
		return new Double(Double.MAX_VALUE);
	}

	public Double createMinValue() {
		return new Double(-Double.MAX_VALUE);
	}

	public boolean greaterThan(Double valueA, Double valueB) {
		return valueA > valueB;
	}

	public boolean lowerThan(Double valueA, Double valueB) {
		return valueA < valueB;
	}

	public Double parseType(String s) {
		return Double.parseDouble(s);
	}

}
