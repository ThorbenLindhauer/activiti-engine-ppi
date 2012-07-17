package de.unipotsdam.hpi.thorben.ppi.measure.process;

import java.util.List;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureInstance;
import de.unipotsdam.hpi.thorben.ppi.typehelper.TypeHelper;

public class MinimumFunction<T extends Number, K extends BaseMeasureInstance>
		extends AbstractAggregationFunction<T, K> {

	public MinimumFunction(TypeHelper<T> typeHelper) {
		super(typeHelper);
	}
	
	protected T performCalculation(List<K> baseMeasureValues) {
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
