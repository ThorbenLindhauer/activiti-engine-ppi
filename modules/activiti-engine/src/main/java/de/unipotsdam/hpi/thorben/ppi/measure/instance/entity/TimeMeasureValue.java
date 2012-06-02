package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TimeMeasureValue extends BaseMeasureValue {

	private Date from;
	private Date to;
	
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
	
	public void update(TimeMeasureValue value) {
		if (value.from != null) {
			this.from = value.from;
		}
		if (value.to != null) {
			this.to = value.to;
		}
	}
}
