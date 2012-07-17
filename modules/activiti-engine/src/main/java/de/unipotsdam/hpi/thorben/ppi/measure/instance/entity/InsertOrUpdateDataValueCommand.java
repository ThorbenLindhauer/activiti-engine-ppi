package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class InsertOrUpdateDataValueCommand implements Command<Void> {

	private DataMeasureInstance dataMeasureValue;

	public InsertOrUpdateDataValueCommand(DataMeasureInstance dataMeasureValue) {
		this.dataMeasureValue = dataMeasureValue;
	}

	public Void execute(CommandContext commandContext) {
		String measureId = dataMeasureValue.getMeasureId();
		String processInstanceId = dataMeasureValue.getProcessInstanceId();
		DataMeasureInstance value = commandContext.getBaseMeasureManager().findDataMeasureInstance(measureId, processInstanceId);
		if (value == null) {
			commandContext.getBaseMeasureManager().insertDataMeasureInstance(dataMeasureValue);
		} else {
			value.update(dataMeasureValue);
		}
		
		return null;
	}

}
