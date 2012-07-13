package de.unipotsdam.hpi.thorben.ppi.measure.process;

import java.util.List;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureInstance;

public class MinimumFunction<T extends Number, K extends BaseMeasureInstance>
		implements AggregationFunction<T, K> {

	private TypeHelper<T> helper;
	public MinimumFunction(TypeHelper<T> typeHelper) {
		this.helper = typeHelper;
	}
	
	public T calculate(List<K> baseMeasureValues) {
		T minimum = helper.createMaxValue();
		for (K k : baseMeasureValues) {
			T currentValue = helper.asType(k.calculate());
			if (helper.lowerThan(currentValue, minimum)) {
				minimum = currentValue;
			}
		}
		return minimum;
	}

}
