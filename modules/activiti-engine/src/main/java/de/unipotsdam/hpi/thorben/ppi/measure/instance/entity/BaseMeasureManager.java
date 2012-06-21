package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.AbstractManager;

import de.unipotsdam.hpi.thorben.ppi.measure.query.CountMeasureValueQuery;
import de.unipotsdam.hpi.thorben.ppi.measure.query.CountMeasureValueQueryImpl;
import de.unipotsdam.hpi.thorben.ppi.measure.query.TimeMeasureValueQuery;
import de.unipotsdam.hpi.thorben.ppi.measure.query.TimeMeasureValueQueryImpl;

public class BaseMeasureManager extends AbstractManager {

	public void insertTimeMeasureValue(TimeMeasureValue value) {
		getDbSqlSession().insert(value);
	}
	
	public void insertTimeCountValue(CountMeasureValue value) {
		getDbSqlSession().insert(value);
	}
	
	public void insertSingleTimeMeasureValue(SingleTimeMeasureValue singleValue) {
		getDbSqlSession().insert(singleValue);
	}
	
	public TimeMeasureValue findTimeMeasureValue(String measureId, String processInstanceId) {
	    Map<String, String> parameters = new HashMap<String, String>();
	    parameters.put("measureId", measureId);
	    parameters.put("processInstanceId", processInstanceId);
	    return (TimeMeasureValue) getDbSqlSession().selectOne("selectTMVByMeasureIdAndProcessInstance", parameters);
	}
	

	public SingleTimeMeasureValue findSingleTimeMeasureValue(String id) {
		Map<String, String> parameters = new HashMap<String, String>();
	    parameters.put("id", id);
	    return (SingleTimeMeasureValue) getDbSqlSession().selectOne("selectSingleTimeMeasureValue", parameters);
	}
	
	public CountMeasureValue findCountMeasureValue(String measureId, String processInstanceId) {
	    Map<String, String> parameters = new HashMap<String, String>();
	    parameters.put("measureId", measureId);
	    parameters.put("processInstanceId", processInstanceId);
	    return (CountMeasureValue) getDbSqlSession().selectOne("selectCMVByMeasureIdAndProcessInstance", parameters);
	}
	
//	public void updateUser(TimeMeasureValue updatedValue) {
//	    UserEntity persistentUser = findUserById(updatedValue.getId());
//	    persistentUser.update((UserEntity) updatedValue);
//	  }
	
	public TimeMeasureValueQuery createNewTimeMeasureValueQuery() {
		return new TimeMeasureValueQueryImpl(Context.getProcessEngineConfiguration().getCommandExecutorTxRequired());
	}
	
	public long findTimeMeasureValueCountByQueryCriteria(
			TimeMeasureValueQueryImpl query) {
		return (Long) getDbSqlSession().selectOne("selectTimeMeasureValueCountByQueryCriteria", query);
	}

	public List<TimeMeasureValue> findTimeMeasureValueByQueryCriteria(
			TimeMeasureValueQueryImpl query, Page page) {
		return getDbSqlSession().selectList("selectTimeMeasureValueByQueryCriteria", query, page);
	}

	public CountMeasureValueQuery createNewCountMeasureValueQuery() {
		return new CountMeasureValueQueryImpl(Context.getProcessEngineConfiguration().getCommandExecutorTxRequired());
	}
	
	public long findCountMeasureValueCountByQueryCriteria(
			CountMeasureValueQueryImpl query) {
		return (Long) getDbSqlSession().selectOne("selectCountMeasureValueCountByQueryCriteria", query);
	}

	public List<CountMeasureValue> findCountMeasureValueByQueryCriteria(
			CountMeasureValueQueryImpl query, Page page) {
		return getDbSqlSession().selectList("selectCountMeasureValueByQueryCriteria", query, page);
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
