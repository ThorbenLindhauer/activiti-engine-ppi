package de.unipotsdam.hpi.thorben.ppi.measure.process;

public class IntegerHelper implements TypeHelper<Integer> {

	public Integer createZero() {
		return new Integer(0);
	}

	public Integer add(Integer summandA, Integer summandB) {
		return summandA + summandB;
	}

	public Integer divide(Integer dividend, Integer divisor) {
		return dividend/divisor;
	}

	public Integer asType(Number n) {
		return new Integer(n.intValue());
	}

	public Integer createMaxValue() {
		return new Integer(Integer.MAX_VALUE);
	}

	public Integer createMinValue() {
		return new Integer(Integer.MIN_VALUE);
	}

	public boolean greaterThan(Integer valueA, Integer valueB) {
		return valueA > valueB;
	}

	public boolean lowerThan(Integer valueA, Integer valueB) {
		return valueA < valueB;
	}

}
