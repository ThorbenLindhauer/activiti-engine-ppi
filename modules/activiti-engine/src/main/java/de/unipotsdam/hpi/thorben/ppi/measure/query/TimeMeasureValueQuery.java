package de.unipotsdam.hpi.thorben.ppi.measure.query;

import org.activiti.engine.query.Query;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.TimeMeasureValue;

public interface TimeMeasureValueQuery extends Query<TimeMeasureValueQuery, TimeMeasureValue> {

	TimeMeasureValueQuery measureId(String measureId);
	
	TimeMeasureValueQuery processInstanceId(String processInstanceId);
}
