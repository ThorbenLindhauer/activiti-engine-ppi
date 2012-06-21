package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity;

import java.util.HashMap;
import java.util.Map;

public class CountMeasureInstance extends BaseMeasureInstance {

	private int count;
	
	public CountMeasureInstance() {
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
	
	public void update(CountMeasureInstance value) {
		count = value.count;
	}

	@Override
	public Number calculate() {
		return count;
	}


}
