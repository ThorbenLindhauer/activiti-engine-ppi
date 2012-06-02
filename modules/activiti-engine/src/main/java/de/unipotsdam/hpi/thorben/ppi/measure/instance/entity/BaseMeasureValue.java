package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity;

import org.activiti.engine.impl.db.PersistentObject;

public abstract class BaseMeasureValue implements PersistentObject {

	protected String id;
	protected String measureId;
	protected String processInstanceId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMeasureId() {
		return measureId;
	}
	public void setMeasureId(String measureId) {
		this.measureId = measureId;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public abstract Number calculate();
	
}
