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

package de.unipotsdam.hpi.thorben.ppi.measure.instance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.repository.ProcessDefinition;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.DataMeasureInstance;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.command.GetDataMeasureInstanceCommand;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.command.InsertDataInstanceCommand;
import de.unipotsdam.hpi.thorben.ppi.measure.query.DataMeasureInstanceQuery;

public class DataMeasure extends BaseMeasure<DataMeasureInstance> {

	private String dataObjectName;
	private String dataFieldName;
	private Map<String, DataMeasureInstance> instancesCache = new HashMap<String, DataMeasureInstance>();

	public DataMeasure(String id, ProcessDefinition processDefinition) {
		super(id, processDefinition);
	}

	public void updateDataValue(String processInstanceId, Object value) {
		DataMeasureInstance dataMeasureInstance = findCachedDataMeasureInstance(processInstanceId);
		dataMeasureInstance.setValue(value.toString());
	}

	/**
	 * Finds a data measure instance (or creates one) and ensures that it is in Activiti's cache.
	 * 
	 * It is also added to a local cache in this DataMeasure, as the select commands do not access the Activiti cache.
	 * Otherwise a DataMeasureInstance that was already created before, but not committed, could not be found.
	 * @param processInstanceId
	 * @return
	 */
	private DataMeasureInstance findCachedDataMeasureInstance(String processInstanceId) {
		CommandContext commandContext = Context.getCommandContext();
		DataMeasureInstance dataMeasureValue = new GetDataMeasureInstanceCommand(id, processInstanceId).execute(commandContext);
		if (dataMeasureValue == null) {
			dataMeasureValue = instancesCache.get(processInstanceId);
		}
		if (dataMeasureValue == null) {
			dataMeasureValue = new DataMeasureInstance();
			dataMeasureValue.setMeasureId(id);
			dataMeasureValue.setProcessInstanceId(processInstanceId);
			new InsertDataInstanceCommand(dataMeasureValue).execute(commandContext);
		}
		instancesCache.put(processInstanceId, dataMeasureValue);
		
		return dataMeasureValue;
	}

	@Override
	public List<DataMeasureInstance> getAllValues() {
		CommandContext context = Context.getCommandContext();
		DataMeasureInstanceQuery query = context.getBaseMeasureManager()
				.createNewDataMeasureValueQuery()
				.processDefinitionId(processDefinition.getId()).measureId(id);
		return query.list();
	}

	public String getDataObjectName() {
		return dataObjectName;
	}

	public void setDataObjectName(String dataObjectName) {
		this.dataObjectName = dataObjectName;
	}

	public String getDataFieldName() {
		return dataFieldName;
	}

	public void setDataFieldName(String dataFieldName) {
		this.dataFieldName = dataFieldName;
	}

}
