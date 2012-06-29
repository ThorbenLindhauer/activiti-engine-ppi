package de.unipotsdam.hpi.thorben.ppi.measure.query;

import org.activiti.engine.query.Query;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.DataMeasureInstance;

public interface DataMeasureInstanceQuery extends Query<DataMeasureInstanceQuery, DataMeasureInstance> {

	DataMeasureInstanceQuery measureId(String measureId);
	
	DataMeasureInstanceQuery processInstanceId(String processInstanceId);
}
