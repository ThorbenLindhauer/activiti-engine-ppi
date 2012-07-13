package de.unipotsdam.hpi.thorben.ppi.measure.process;

import java.util.List;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureInstance;

public class MaximumFunction<T extends Number, K extends BaseMeasureInstance>
		implements AggregationFunction<T, K> {
	
	private TypeHelper<T> helper;
	public MaximumFunction(TypeHelper<T> typeHelper) {
		this.helper = typeHelper;
	}

	public T calculate(List<K> baseMeasureValues) {
		T maximum = helper.createMinValue();
		for (K k : baseMeasureValues) {
			T currentValue = helper.asType(k.calculate());
			if (helper.greaterThan(currentValue, maximum)) {
				maximum = currentValue;
			}
		}
		return maximum;
	}

}
