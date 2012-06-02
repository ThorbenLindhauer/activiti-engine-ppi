package de.unipotsdam.hpi.thorben.ppi.measure.process;

import java.util.List;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureValue;

public interface AggregationFunction<T extends Number, K extends BaseMeasureValue> {

	T calculate(List<K> baseMeasureValues);
}
