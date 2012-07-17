package de.unipotsdam.hpi.thorben.ppi.typehelper;


public class LongHelper implements TypeHelper<Long> {

	public Long createZero() {
		return new Long(0L);
	}

	public Long add(Long summandA, Long summandB) {
		return summandA + summandB;
	}

	public Long divide(Long dividend, Long divisor) {
		return dividend/divisor;
	}

	public Long asType(Number n) {
		return new Long(n.longValue());
	}

	public Long createMaxValue() {
		return new Long(Long.MAX_VALUE);
	}

	public Long createMinValue() {
		return new Long(Long.MIN_VALUE);
	}

	public boolean greaterThan(Long valueA, Long valueB) {
		return valueA > valueB;
	}

	public boolean lowerThan(Long valueA, Long valueB) {
		return valueA < valueB;
	}

	public Long parseType(String s) {
		return Long.parseLong(s);
	}

}
