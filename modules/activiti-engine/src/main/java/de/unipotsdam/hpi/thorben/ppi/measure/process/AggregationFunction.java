package de.unipotsdam.hpi.thorben.ppi.measure.process;

import java.util.List;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureInstance;

public interface AggregationFunction<T extends Number, K extends BaseMeasureInstance> {

	T calculate(List<K> baseMeasureValues);
}
