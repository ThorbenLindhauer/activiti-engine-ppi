package de.unipotsdam.hpi.thorben.ppi.measure.process;

import java.rmi.activation.ActivateFailedException;

import org.activiti.engine.ActivitiException;

public class PPI {

	private String id;
	private ProcessMeasure<?> processMeasure;
	private Number targetValue;
	
	private TargetFunction targetFunction;
	
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
	public void setTargetValue(Number targetValue) {
		this.targetValue = targetValue;
	}
	public void setTargetFunction(TargetFunction targetFunction) {
		this.targetFunction = targetFunction;
	}
	
	public boolean isFulfilledBy(Number number) {
		if (targetFunction == null || targetValue == null) {
			throw new ActivitiException("No target function or value was specified for this ppi.");
		}
		return targetFunction.apply(number, targetValue);
	}
	
}
