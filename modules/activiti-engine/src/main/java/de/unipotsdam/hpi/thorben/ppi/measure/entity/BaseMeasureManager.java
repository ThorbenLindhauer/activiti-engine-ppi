package de.unipotsdam.hpi.thorben.ppi.measure.entity;

import org.activiti.engine.impl.persistence.AbstractManager;

public class BaseMeasureManager extends AbstractManager {

	public void insertTimeMeasureValue(TimeMeasureValue value) {
		getDbSqlSession().insert(value);
	}
}
