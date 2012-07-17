package de.unipotsdam.hpi.thorben.ppi.measure.process;

import java.util.List;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureInstance;
import de.unipotsdam.hpi.thorben.ppi.typehelper.TypeHelper;

public class SumFunction<T extends Number, K extends BaseMeasureInstance> implements AggregationFunction<T, K> {

	private TypeHelper<T> helper;
	public SumFunction(TypeHelper<T> typeHelper) {
		this.helper = typeHelper;
	}
	
	public T calculate(List<K> baseMeasureValues) {
		T sum = helper.createZero();
		for (K k : baseMeasureValues) {
			sum = helper.add(sum, helper.asType(k.calculate()));
		}
		return sum;
	}

}
