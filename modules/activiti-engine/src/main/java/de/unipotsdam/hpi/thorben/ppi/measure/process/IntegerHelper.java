package de.unipotsdam.hpi.thorben.ppi.measure.process;

public class IntegerHelper implements TypeHelper<Integer> {

	public Integer create() {
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

}
