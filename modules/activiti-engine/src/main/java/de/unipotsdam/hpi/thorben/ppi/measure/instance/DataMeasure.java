package de.unipotsdam.hpi.thorben.ppi.measure.instance;

import java.util.List;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.repository.ProcessDefinition;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.DataMeasureInstance;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.InsertOrUpdateDataValueCommand;
import de.unipotsdam.hpi.thorben.ppi.measure.query.DataMeasureInstanceQuery;

public class DataMeasure extends BaseMeasure<DataMeasureInstance> {

	private String dataObjectName;
	private String dataFieldName;

	public DataMeasure(String id, ProcessDefinition processDefinition) {
		super(id, processDefinition);
	}

	public void updateDataValue(String processInstanceId, Object value) {
		CommandContext commandContext = Context.getCommandContext();
		DataMeasureInstance dataMeasureInstance = new DataMeasureInstance();
		dataMeasureInstance.setMeasureId(id);
		dataMeasureInstance.setProcessInstanceId(processInstanceId);
		dataMeasureInstance.setValue(value.toString());
		new InsertOrUpdateDataValueCommand(dataMeasureInstance)
				.execute(commandContext);
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
