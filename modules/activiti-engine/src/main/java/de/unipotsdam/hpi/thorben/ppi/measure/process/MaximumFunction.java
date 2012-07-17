package de.unipotsdam.hpi.thorben.ppi.measure.process;

import java.util.List;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureInstance;
import de.unipotsdam.hpi.thorben.ppi.typehelper.TypeHelper;

public class MaximumFunction<T extends Number, K extends BaseMeasureInstance>
		extends AbstractAggregationFunction<T, K> {
	
	public MaximumFunction(TypeHelper<T> typeHelper) {
		super(typeHelper);
	}

	protected T performCalculation(List<K> baseMeasureValues) {
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