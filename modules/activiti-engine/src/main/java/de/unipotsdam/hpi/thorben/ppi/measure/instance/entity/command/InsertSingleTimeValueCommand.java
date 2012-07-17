package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.command;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.SingleTimeMeasureValue;


public class InsertSingleTimeValueCommand implements
		Command<Void> {
	
	private SingleTimeMeasureValue value;

	public InsertSingleTimeValueCommand(SingleTimeMeasureValue value) {
		this.value = value;
	}

	public Void execute(CommandContext commandContext) {
		commandContext.getBaseMeasureManager().insertSingleTimeMeasureValue(value);
		return null;
	}

}
