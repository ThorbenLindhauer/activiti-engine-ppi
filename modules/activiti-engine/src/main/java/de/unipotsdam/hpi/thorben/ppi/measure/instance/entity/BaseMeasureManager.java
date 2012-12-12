/* Copyright 2012 Thorben Lindhauer
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.AbstractManager;

import de.unipotsdam.hpi.thorben.ppi.measure.query.CountMeasureInstanceQuery;
import de.unipotsdam.hpi.thorben.ppi.measure.query.CountMeasureInstanceQueryImpl;
import de.unipotsdam.hpi.thorben.ppi.measure.query.DataMeasureInstanceQuery;
import de.unipotsdam.hpi.thorben.ppi.measure.query.DataMeasureInstanceQueryImpl;
import de.unipotsdam.hpi.thorben.ppi.measure.query.SingleTimeMeasureValueQuery;
import de.unipotsdam.hpi.thorben.ppi.measure.query.SingleTimeMeasureValueQueryImpl;

public class BaseMeasureManager extends AbstractManager {

	public void insertCountMeasureInstance(CountMeasureInstance value) {
		getDbSqlSession().insert(value);
	}

	public void insertSingleTimeMeasureValue(SingleTimeMeasureValue singleValue) {
		getDbSqlSession().insert(singleValue);
	}

	public void insertDataMeasureInstance(DataMeasureInstance value) {
		getDbSqlSession().insert(value);
	}

	public TimeMeasureInstance findTimeMeasureInstance(String measureId,
			String processInstanceId) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("measureId", measureId);
		parameters.put("processInstanceId", processInstanceId);
		return (TimeMeasureInstance) getDbSqlSession()
				.selectOne(
						"de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.TimeMeasureInstance.selectByMeasureIdAndProcessInstance",
						parameters);
	}

	public SingleTimeMeasureValue findSingleTimeMeasureValue(String id) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("id", id);
		return (SingleTimeMeasureValue) getDbSqlSession().selectOne(
				"selectSingleTimeMeasureValue", parameters);
	}

	public CountMeasureInstance findCountMeasureInstance(String measureId,
			String processInstanceId) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("measureId", measureId);
		parameters.put("processInstanceId", processInstanceId);
		return (CountMeasureInstance) getDbSqlSession()
				.selectOne(
						"de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.CountMeasureInstance.selectByMeasureIdAndProcessInstance",
						parameters);
	}

	public DataMeasureInstance findDataMeasureInstance(String measureId,
			String processInstanceId) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("measureId", measureId);
		parameters.put("processInstanceId", processInstanceId);
		return (DataMeasureInstance) getDbSqlSession()
				.selectOne(
						"de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.DataMeasureInstance.selectByMeasureIdAndProcessInstance",
						parameters);
	}

	public SingleTimeMeasureValueQuery createNewSingleTimeMeasureValueQuery() {
		return new SingleTimeMeasureValueQueryImpl(Context
				.getProcessEngineConfiguration().getCommandExecutorTxRequired());
	}

	public long findSingleTimeMeasureValueCountByQueryCriteria(
			SingleTimeMeasureValueQueryImpl query) {
		return (Long) getDbSqlSession()
				.selectOne(
						"de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.SingleTimeMeasureValue.selectByQueryCriteria",
						query);
	}

	public List<SingleTimeMeasureValue> findSingleTimeMeasureValueByQueryCriteria(
			SingleTimeMeasureValueQueryImpl query, Page page) {
		return getDbSqlSession()
				.selectList(
						"de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.SingleTimeMeasureValue.selectByQueryCriteria",
						query, page);
	}

	public CountMeasureInstanceQuery createNewCountMeasureValueQuery() {
		return new CountMeasureInstanceQueryImpl(Context
				.getProcessEngineConfiguration().getCommandExecutorTxRequired());
	}

	public long findCountMeasureInstanceCountByQueryCriteria(
			CountMeasureInstanceQueryImpl query) {
		return (Long) getDbSqlSession()
				.selectOne(
						"de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.CountMeasureInstance.selectCountByQueryCriteria",
						query);
	}

	public List<CountMeasureInstance> findCountMeasureInstanceByQueryCriteria(
			CountMeasureInstanceQueryImpl query, Page page) {
		return getDbSqlSession()
				.selectList(
						"de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.CountMeasureInstance.selectByQueryCriteria",
						query, page);
	}

	public DataMeasureInstanceQuery createNewDataMeasureValueQuery() {
		return new DataMeasureInstanceQueryImpl(Context
				.getProcessEngineConfiguration().getCommandExecutorTxRequired());
	}

	public long findDataMeasureInstanceCountByQueryCriteria(
			DataMeasureInstanceQueryImpl query) {
		return (Long) getDbSqlSession()
				.selectOne(
						"de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.DataMeasureInstance.selectCountByQueryCriteria",
						query);
	}

	public List<DataMeasureInstance> findDataMeasureInstanceByQueryCriteria(
			DataMeasureInstanceQueryImpl query, Page page) {
		return getDbSqlSession()
				.selectList(
						"de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.DataMeasureInstance.selectByQueryCriteria",
						query, page);
	}
}
