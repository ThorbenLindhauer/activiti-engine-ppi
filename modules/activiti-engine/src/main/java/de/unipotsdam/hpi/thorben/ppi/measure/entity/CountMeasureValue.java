package de.unipotsdam.hpi.thorben.ppi.measure.entity;

import java.util.HashMap;
import java.util.Map;

public class CountMeasureValue extends BaseMeasureValue {

	private int count;
	
	public CountMeasureValue() {
		count = 0;
	}
	
	@Override
	public Object getPersistentState() {
		Map<String, Object> persistentState = new HashMap<String, Object>();
	    persistentState.put("id", id);
	    persistentState.put("count", count);
	    persistentState.put("measureId", measureId);
	    persistentState.put("processInstanceId", processInstanceId);
	    return persistentState;
	}
	
	public void increaseCount() {
		count++;
	}
	
	public void update(CountMeasureValue value) {
		count = value.count;
	}


}
