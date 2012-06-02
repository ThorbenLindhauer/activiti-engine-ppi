package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class InsertOrUpdateTimeValueCommand implements Command<Void> {

	private TimeMeasureValue timeMeasureValue;

	public InsertOrUpdateTimeValueCommand(TimeMeasureValue timeMeasureValue) {
		this.timeMeasureValue = timeMeasureValue;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		String measureId = timeMeasureValue.getMeasureId();
		String processInstanceId = timeMeasureValue.getProcessInstanceId();
		TimeMeasureValue value = commandContext.getBaseMeasureManager().findTimeMeasureValue(measureId, processInstanceId);
		if (value == null) {
			commandContext.getBaseMeasureManager().insertTimeMeasureValue(timeMeasureValue);
		} else {
			value.update(timeMeasureValue);
		}
		
		// commit this stuff immediately
		commandContext.getDbSqlSession().flush();
		return null;
	}

}
