package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.command;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.TimeMeasureInstance;


public class InsertTimeInstanceCommand implements Command<Void> {

	private TimeMeasureInstance instance;
	
	public InsertTimeInstanceCommand(TimeMeasureInstance instance) {
		this.instance = instance;
	}
	
	public Void execute(CommandContext commandContext) {
		commandContext.getBaseMeasureManager().insertTimeMeasureInstance(
				instance);
		return null;
	}

}
