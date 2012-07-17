package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.command;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.CountMeasureInstance;


public class InsertCountInstanceCommand implements Command<Void> {

	private CountMeasureInstance value;
	
	public InsertCountInstanceCommand(CountMeasureInstance value) {
		this.value = value;
	}

	public Void execute(CommandContext commandContext) {
		commandContext.getBaseMeasureManager().insertCountMeasureInstance(value);
		return null;
	}

}
