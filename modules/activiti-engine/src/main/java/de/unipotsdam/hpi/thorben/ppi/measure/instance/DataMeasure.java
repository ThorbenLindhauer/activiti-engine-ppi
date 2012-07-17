package de.unipotsdam.hpi.thorben.ppi.measure.instance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.repository.ProcessDefinition;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.DataMeasureInstance;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.GetDataMeasureInstanceCommand;
import de.unipotsdam.hpi.thorben.ppi.measure.query.DataMeasureInstanceQuery;

public class DataMeasure extends BaseMeasure<DataMeasureInstance> {

	private String dataObjectName;
	private String dataFieldName;
	private Map<String, DataMeasureInstance> instancesCache = new HashMap<String, DataMeasureInstance>();

	public DataMeasure(String id, ProcessDefinition processDefinition) {
		super(id, processDefinition);
	}

	public void updateDataValue(String processInstanceId, Object value) {
		DataMeasureInstance dataMeasureInstance = findDataMeasureInstance(processInstanceId);
		dataMeasureInstance.setValue(value.toString());
	}

	private DataMeasureInstance findDataMeasureInstance(String processInstanceId) {
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
