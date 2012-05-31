package de.unipotsdam.hpi.thorben.ppi.measure.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.db.PersistentObject;

public class TimeMeasureValue implements PersistentObject {

	private String id;
	private Date from;
	private Date to;
	private String measureId;
	private String processInstanceId;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
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
	
	@Override
	public Object getPersistentState() {
		Map<String, Object> persistentState = new HashMap<String, Object>();
	    persistentState.put("id", id);
	    persistentState.put("from", from);
	    persistentState.put("to", to);
	    persistentState.put("measureId", measureId);
	    persistentState.put("processInstanceId", processInstanceId);
	    return persistentState;
	}
	
	
}
