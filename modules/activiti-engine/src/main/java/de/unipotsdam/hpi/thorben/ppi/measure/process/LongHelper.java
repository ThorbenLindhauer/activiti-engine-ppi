package de.unipotsdam.hpi.thorben.ppi.measure.process;

public class LongHelper implements TypeHelper<Long> {

	public Long create() {
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

}
