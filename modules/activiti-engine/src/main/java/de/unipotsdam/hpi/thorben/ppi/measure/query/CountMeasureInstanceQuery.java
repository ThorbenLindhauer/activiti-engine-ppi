package de.unipotsdam.hpi.thorben.ppi.measure.query;

import org.activiti.engine.query.Query;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.CountMeasureInstance;

public interface CountMeasureInstanceQuery extends Query<CountMeasureInstanceQuery, CountMeasureInstance> {

	CountMeasureInstanceQuery measureId(String measureId);
	
	CountMeasureInstanceQuery processInstanceId(String processInstanceId);
}