package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.db.PersistentObject;

public class SingleTimeMeasureValue implements PersistentObject {

	private String id;
	private Date from;
	private Date to;
	private String timeMeasureId;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTimeMeasureId() {
		return timeMeasureId;
	}

	public void setTimeMeasureId(String timeMeasureId) {
		this.timeMeasureId = timeMeasureId;
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

	@Override
	public Object getPersistentState() {
		Map<String, Object> persistentState = new HashMap<String, Object>();
	    persistentState.put("id", id);
	    persistentState.put("from", from);
	    persistentState.put("to", to);
	    persistentState.put("timeMeasureId", timeMeasureId);
	    return persistentState;
	}

	public void update(SingleTimeMeasureValue value) {
		if (value.from != null) {
			this.from = value.from;
		}
		if (value.to != null) {
			this.to = value.to;
		}
	}
}
