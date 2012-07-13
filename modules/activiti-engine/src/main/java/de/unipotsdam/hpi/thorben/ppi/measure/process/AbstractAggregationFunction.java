package de.unipotsdam.hpi.thorben.ppi.measure.process;

import java.util.List;

import org.activiti.engine.ActivitiException;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureInstance;

public abstract class AbstractAggregationFunction<T extends Number, K extends BaseMeasureInstance>
		implements AggregationFunction<T, K> {

	protected TypeHelper<T> helper;
	
	public AbstractAggregationFunction(TypeHelper<T> typeHelper) {
		this.helper = typeHelper;
	}
	
	public T calculate(List<K> baseMeasureValues) {
		if (baseMeasureValues == null || baseMeasureValues.isEmpty()) {
			throw new ActivitiException("Cannot calculate aggregation function: No BaseMeasure values were supplied");
		}
		return performCalculation(baseMeasureValues);
	}
	
	protected abstract T performCalculation(List<K> baseMeasureValues);
}
