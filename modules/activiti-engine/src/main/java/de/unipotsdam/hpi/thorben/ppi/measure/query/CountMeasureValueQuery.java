package de.unipotsdam.hpi.thorben.ppi.measure.query;

import org.activiti.engine.query.Query;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.CountMeasureValue;

public interface CountMeasureValueQuery extends Query<CountMeasureValueQuery, CountMeasureValue> {

	CountMeasureValueQuery measureId(String measureId);
	
	CountMeasureValueQuery processInstanceId(String processInstanceId);
}