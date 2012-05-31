package de.unipotsdam.hpi.thorben.ppi.measure.entity;

import java.util.List;

import org.activiti.engine.identity.User;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.AbstractManager;
import org.activiti.engine.impl.persistence.entity.UserEntity;

import de.unipotsdam.hpi.thorben.ppi.measure.query.TimeMeasureValueQueryImpl;

public class BaseMeasureManager extends AbstractManager {

	public void insertTimeMeasureValue(TimeMeasureValue value) {
		getDbSqlSession().insert(value);
	}
	
//	public void updateUser(TimeMeasureValue updatedValue) {
//	    UserEntity persistentUser = findUserById(updatedValue.getId());
//	    persistentUser.update((UserEntity) updatedValue);
//	  }
	
	public long findTimeMeasureValueCountByQueryCriteria(
			TimeMeasureValueQueryImpl query) {
		return (Long) getDbSqlSession().selectOne("selectTimeMeasureValueCountByQueryCriteria", query);
	}

	public List<TimeMeasureValue> findTimeMeasureValueByQueryCriteria(
			TimeMeasureValueQueryImpl query, Page page) {
		return getDbSqlSession().selectList("selectTimeMeasureValueByQueryCriteria", query, page);
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
