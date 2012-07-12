package de.unipotsdam.hpi.thorben.ppi.measure.query;

import org.activiti.engine.query.Query;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.TimeMeasureInstance;

public interface TimeMeasureInstanceQuery extends Query<TimeMeasureInstanceQuery, TimeMeasureInstance> {

	TimeMeasureInstanceQuery measureId(String measureId);
	
	TimeMeasureInstanceQuery processInstanceId(String processInstanceId);
	
	TimeMeasureInstanceQuery processDefinitionId(String processDefinitionId);
}
