package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.command;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.CountMeasureInstance;


public class GetCountMeasureInstanceCommand implements
		Command<CountMeasureInstance> {
	
	private String measureId;
	private String processInstanceId;
	
	public GetCountMeasureInstanceCommand(String measureId,
			String processInstanceId) {
		this.measureId = measureId;
		this.processInstanceId = processInstanceId;
	}

	public CountMeasureInstance execute(CommandContext commandContext) {
		return commandContext.getBaseMeasureManager().findCountMeasureInstance(measureId, processInstanceId);
	}

}
