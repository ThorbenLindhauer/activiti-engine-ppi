package de.unipotsdam.hpi.thorben.ppi.measure.entity;

import org.activiti.engine.impl.persistence.AbstractManager;

public class BaseMeasureManager extends AbstractManager {

	public void insertTimeMeasureValue(TimeMeasureValue value) {
		getDbSqlSession().insert(value);
	}
	
//	public void updateTimeMeasureValue(TimeMeasureValue measure) {
//		TimeMeasureValue measureEntity = findTimeMeasureById(measure.getMeasureId());
//		value
//	}
//	
//	public TimeMeasureValue findTimeMeasureById(String measureId) {
//	    return (TimeMeasureValue) getDbSqlSession().selectOne("selectMeasureById", measureId);
//	  }
}
