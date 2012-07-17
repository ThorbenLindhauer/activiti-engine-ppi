package de.unipotsdam.hpi.thorben.ppi.measure.process;

public abstract class AbstractTargetFunction<N extends Number> implements
		TargetFunction<N> {
	
	protected TypeHelper<N> helper;
	
	public AbstractTargetFunction(TypeHelper<N> typeHelper) {
		this.helper = typeHelper;
	}

	public abstract boolean apply(N operator1, N operator2);

}
