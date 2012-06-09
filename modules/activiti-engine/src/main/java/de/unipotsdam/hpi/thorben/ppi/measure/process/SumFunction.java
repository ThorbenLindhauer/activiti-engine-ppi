package de.unipotsdam.hpi.thorben.ppi.measure.process;

import java.util.List;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureValue;

public class SumFunction<T extends Number, K extends BaseMeasureValue> implements AggregationFunction<T, K> {

	private TypeHelper<T> helper;
	public SumFunction(TypeHelper<T> typeHelper) {
		this.helper = typeHelper;
	}
	
	@Override
	public T calculate(List<K> baseMeasureValues) {
		T sum = helper.create();
		for (K k : baseMeasureValues) {
			sum = helper.add(sum, helper.asType(k.calculate()));
		}
		return sum;
	}

}
