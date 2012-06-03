package de.unipotsdam.hpi.thorben.ppi.measure.process;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.BaseMeasure;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureValue;

public class AggregatedMeasure<M extends BaseMeasure<V>, V extends BaseMeasureValue, N extends Number> implements ProcessMeasure<N> {

	private String id;
	private M baseMeasure;
	private AggregationFunction<N, V> aggregationFunction;
	
	@Override
	public N calculate() {
		return aggregationFunction.calculate(baseMeasure.getAllValues());
	}
	

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public M getBaseMeasure() {
		return baseMeasure;
	}

	public void setBaseMeasure(M baseMeasure) {
		this.baseMeasure = baseMeasure;
	}

	public void setAggregationFunction(
			AggregationFunction<N, V> function) {
		this.aggregationFunction = function;
		
	}
	
	

}
