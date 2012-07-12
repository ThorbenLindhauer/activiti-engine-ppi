package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity;

import java.util.HashMap;
import java.util.Map;

public class DataMeasureInstance extends BaseMeasureInstance {

	private String value;
	
	public Object getPersistentState() {
		Map<String, Object> persistentState = new HashMap<String, Object>();
	    persistentState.put("id", id);
	    persistentState.put("fieldName", value);
	    persistentState.put("measureId", measureId);
	    persistentState.put("processInstanceId", processInstanceId);
	    return persistentState;
	}

	@Override
	public Number calculate() {
		return Double.parseDouble(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public void update(DataMeasureInstance anotherDataMeasure) {
		value = anotherDataMeasure.value;
	}

}
