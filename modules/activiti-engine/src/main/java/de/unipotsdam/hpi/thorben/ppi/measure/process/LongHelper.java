package de.unipotsdam.hpi.thorben.ppi.measure.process;

public class LongHelper implements TypeHelper<Long> {

	@Override
	public Long create() {
		return new Long(0L);
	}

	@Override
	public Long add(Long summandA, Long summandB) {
		return summandA + summandB;
	}

	@Override
	public Long divide(Long dividend, Long divisor) {
		return dividend/divisor;
	}

	@Override
	public Long asType(Number n) {
		return new Long(n.longValue());
	}

}
