package de.unipotsdam.hpi.thorben.ppi.measure.process;

import java.util.List;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureInstance;

/**
 * This is a generic interface that can be implemented for different types of
 * Number sand BaseMeasureInstances as a different types of
 * {@link BaseMeasureInstance}s may return different {@link Number} types as
 * results.
 * 
 * @author Thorben
 * 
 * @param <T>
 * @param <K>
 */
public interface AggregationFunction<T extends Number, K extends BaseMeasureInstance> {

	T calculate(List<K> baseMeasureValues);
}
