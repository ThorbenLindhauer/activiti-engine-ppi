package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.AbstractManager;

import de.unipotsdam.hpi.thorben.ppi.measure.query.CountMeasureInstanceQuery;
import de.unipotsdam.hpi.thorben.ppi.measure.query.CountMeasureInstanceQueryImpl;
import de.unipotsdam.hpi.thorben.ppi.measure.query.TimeMeasureInstanceQuery;
import de.unipotsdam.hpi.thorben.ppi.measure.query.TimeMeasureInstanceQueryImpl;

public class BaseMeasureManager extends AbstractManager {

	public void insertTimeMeasureInstance(TimeMeasureInstance value) {
		getDbSqlSession().insert(value);
	}
	
	public void insertCountMeasureInstance(CountMeasureInstance value) {
		getDbSqlSession().insert(value);
	}
	
	public void insertSingleTimeMeasureValue(SingleTimeMeasureValue singleValue) {
		getDbSqlSession().insert(singleValue);
	}
	
	public TimeMeasureInstance findTimeMeasureInstance(String measureId, String processInstanceId) {
	    Map<String, String> parameters = new HashMap<String, String>();
	    parameters.put("measureId", measureId);
	    parameters.put("processInstanceId", processInstanceId);
	    return (TimeMeasureInstance) getDbSqlSession().selectOne("de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.TimeMeasureInstance.selectByMeasureIdAndProcessInstance", parameters);
	}
	

	public SingleTimeMeasureValue findSingleTimeMeasureValue(String id) {
		Map<String, String> parameters = new HashMap<String, String>();
	    parameters.put("id", id);
	    return (SingleTimeMeasureValue) getDbSqlSession().selectOne("selectSingleTimeMeasureValue", parameters);
	}
	
	public CountMeasureInstance findCountMeasureInstance(String measureId, String processInstanceId) {
	    Map<String, String> parameters = new HashMap<String, String>();
	    parameters.put("measureId", measureId);
	    parameters.put("processInstanceId", processInstanceId);
	    return (CountMeasureInstance) getDbSqlSession().selectOne("de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.CountMeasureInstance.selectByMeasureIdAndProcessInstance", parameters);
	}
	
//	public void updateUser(TimeMeasureValue updatedValue) {
//	    UserEntity persistentUser = findUserById(updatedValue.getId());
//	    persistentUser.update((UserEntity) updatedValue);
//	  }
	
	public TimeMeasureInstanceQuery createNewTimeMeasureInstanceQuery() {
		return new TimeMeasureInstanceQueryImpl(Context.getProcessEngineConfiguration().getCommandExecutorTxRequired());
	}
	
	public long findTimeMeasureInstanceCountByQueryCriteria(
			TimeMeasureInstanceQueryImpl query) {
		return (Long) getDbSqlSession().selectOne("de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.TimeMeasureInstance.selectByQueryCriteria", query);
	}

	public List<TimeMeasureInstance> findTimeMeasureInstanceByQueryCriteria(
			TimeMeasureInstanceQueryImpl query, Page page) {
		return getDbSqlSession().selectList("de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.TimeMeasureInstance.selectByQueryCriteria", query, page);
	}

	public CountMeasureInstanceQuery createNewCountMeasureValueQuery() {
		return new CountMeasureInstanceQueryImpl(Context.getProcessEngineConfiguration().getCommandExecutorTxRequired());
	}
	
	public long findCountMeasureInstanceCountByQueryCriteria(
			CountMeasureInstanceQueryImpl query) {
		return (Long) getDbSqlSession().selectOne("de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.CountMeasureInstance.selectCountByQueryCriteria", query);
	}

	public List<CountMeasureInstance> findCountMeasureInstanceByQueryCriteria(
			CountMeasureInstanceQueryImpl query, Page page) {
		return getDbSqlSession().selectList("de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.CountMeasureInstance.selectByQueryCriteria", query, page);
	}
}
