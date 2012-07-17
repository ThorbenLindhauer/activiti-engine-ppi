package de.unipotsdam.hpi.thorben.ppi.measure.process;

public class PPI<N extends Number> {

	private String id;
	private ProcessMeasure<?> processMeasure;
	private N targetValue;
	
	private TargetFunction<N> targetFunction;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ProcessMeasure<?> getProcessMeasure() {
		return processMeasure;
	}
	public void setProcessMeasure(ProcessMeasure<?> processMeasure) {
		this.processMeasure = processMeasure;
	}
	public void setTargetValue(N targetValue) {
		this.targetValue = targetValue;
	}
	public void setTargetFunction(TargetFunction<N> targetFunction) {
		this.targetFunction = targetFunction;
	}
	
	public boolean isFulfilledBy(N number) {
		return targetFunction.apply(number, targetValue);
	}
	
}
