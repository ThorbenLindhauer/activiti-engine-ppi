package de.unipotsdam.hpi.thorben.ppi.measure.process;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureInstance;

public abstract class AbstractAggregationFunction<T extends Number, K extends BaseMeasureInstance>
		implements AggregationFunction<T, K> {

	protected TypeHelper<T> helper;
	
	public AbstractAggregationFunction(TypeHelper<T> typeHelper) {
		this.helper = typeHelper;
	}
	
}
