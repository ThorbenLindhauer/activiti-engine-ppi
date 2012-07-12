package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class InsertOrUpdateCountValueCommand implements Command<Void> {

	private CountMeasureInstance countMeasureValue;

	public InsertOrUpdateCountValueCommand(CountMeasureInstance countMeasureValue) {
		this.countMeasureValue = countMeasureValue;
	}

	public Void execute(CommandContext commandContext) {
		String measureId = countMeasureValue.getMeasureId();
		String processInstanceId = countMeasureValue.getProcessInstanceId();
		CountMeasureInstance value = commandContext.getBaseMeasureManager().findCountMeasureInstance(measureId, processInstanceId);
		if (value == null) {
			commandContext.getBaseMeasureManager().insertCountMeasureInstance(countMeasureValue);
		} else {
			value.update(countMeasureValue);
		}
		
		// commit this stuff immediately
		commandContext.getDbSqlSession().flush();
		return null;
	}

}
