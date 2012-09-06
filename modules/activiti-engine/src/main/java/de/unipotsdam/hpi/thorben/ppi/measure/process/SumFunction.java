package de.unipotsdam.hpi.thorben.ppi.measure.process;

import java.util.List;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureInstance;
import de.unipotsdam.hpi.thorben.ppi.typehelper.TypeHelper;

public class SumFunction<T extends Number, K extends BaseMeasureInstance> extends AbstractAggregationFunction<T, K> {

	public SumFunction(TypeHelper<T> typeHelper) {
		super(typeHelper);
		this.helper = typeHelper;
	}

	@Override
	protected T performCalculation(List<K> baseMeasureValues) {
		T sum = helper.createZero();
		for (K k : baseMeasureValues) {
			sum = helper.add(sum, helper.asType(k.calculate()));
		}
		return sum;
	}

}
