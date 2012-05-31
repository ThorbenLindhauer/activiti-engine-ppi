package de.unipotsdam.hpi.thorben.ppi.measure.entity;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class InsertOrUpdateCountValueCommand implements Command<Void> {

	private CountMeasureValue countMeasureValue;

	public InsertOrUpdateCountValueCommand(CountMeasureValue countMeasureValue) {
		this.countMeasureValue = countMeasureValue;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		String measureId = countMeasureValue.getMeasureId();
		String processInstanceId = countMeasureValue.getProcessInstanceId();
		CountMeasureValue value = commandContext.getBaseMeasureManager().findCountMeasureValue(measureId, processInstanceId);
		if (value == null) {
			commandContext.getBaseMeasureManager().insertTimeCountValue(countMeasureValue);
		} else {
			value.update(countMeasureValue);
		}
		
		// commit this stuff immediately
		commandContext.getDbSqlSession().flush();
		return null;
	}

}
