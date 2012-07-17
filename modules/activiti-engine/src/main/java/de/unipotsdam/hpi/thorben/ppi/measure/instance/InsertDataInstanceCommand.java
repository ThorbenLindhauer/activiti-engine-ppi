package de.unipotsdam.hpi.thorben.ppi.measure.instance;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.DataMeasureInstance;

public class InsertDataInstanceCommand implements Command<Void> {

	private DataMeasureInstance value;
	
	public InsertDataInstanceCommand(DataMeasureInstance value) {
		this.value = value;
	}

	public Void execute(CommandContext commandContext) {
		commandContext.getBaseMeasureManager().insertDataMeasureInstance(value);
		return null;
	}

}
