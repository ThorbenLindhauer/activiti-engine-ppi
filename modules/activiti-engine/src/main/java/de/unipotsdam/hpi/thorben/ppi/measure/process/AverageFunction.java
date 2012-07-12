package de.unipotsdam.hpi.thorben.ppi.measure.process;

import java.util.List;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureInstance;

public class AverageFunction<T extends Number, K extends BaseMeasureInstance> implements AggregationFunction<T, K> {

	private TypeHelper<T> helper;
	public AverageFunction(TypeHelper<T> typeHelper) {
		this.helper = typeHelper;
	}
	
	public T calculate(List<K> baseMeasureValues) {
		T sum = helper.create();
		for (K k : baseMeasureValues) {
			sum = helper.add(sum, helper.asType(k.calculate()));
		}
		if (baseMeasureValues.size() != 0) {
			return helper.divide(sum, helper.asType(baseMeasureValues.size()));
		} else {
			return helper.create();
		}
	}
}
