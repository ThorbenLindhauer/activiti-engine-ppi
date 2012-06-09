package de.unipotsdam.hpi.thorben.ppi.measure.process;

public class IntegerHelper implements TypeHelper<Integer> {

	@Override
	public Integer create() {
		return new Integer(0);
	}

	@Override
	public Integer add(Integer summandA, Integer summandB) {
		return summandA + summandB;
	}

	@Override
	public Integer divide(Integer dividend, Integer divisor) {
		return dividend/divisor;
	}

	@Override
	public Integer asType(Number n) {
		return new Integer(n.intValue());
	}

}
