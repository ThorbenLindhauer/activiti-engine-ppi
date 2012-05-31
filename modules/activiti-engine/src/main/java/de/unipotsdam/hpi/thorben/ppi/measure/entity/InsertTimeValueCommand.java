package de.unipotsdam.hpi.thorben.ppi.measure.entity;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class InsertTimeValueCommand implements Command<Void> {

	private TimeMeasureValue timeMeasureValue;

	public InsertTimeValueCommand(TimeMeasureValue timeMeasureValue) {
		this.timeMeasureValue = timeMeasureValue;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		commandContext.getBaseMeasureManager().insertTimeMeasureValue(timeMeasureValue);
		// commit this stuff immediately
		commandContext.getDbSqlSession().flush();
		return null;
	}

}
