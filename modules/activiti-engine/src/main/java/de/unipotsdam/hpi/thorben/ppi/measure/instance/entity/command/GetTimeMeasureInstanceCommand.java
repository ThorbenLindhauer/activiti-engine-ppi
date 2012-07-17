package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.command;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.TimeMeasureInstance;

public class GetTimeMeasureInstanceCommand implements Command<TimeMeasureInstance> {

	private String measureId;
	private String processInstanceId;
	
	public GetTimeMeasureInstanceCommand(String measureId,
			String processInstanceId) {
		this.measureId = measureId;
		this.processInstanceId = processInstanceId;
	}

	public TimeMeasureInstance execute(CommandContext commandContext) {
		TimeMeasureInstance persistedValue = commandContext
				.getBaseMeasureManager().findTimeMeasureInstance(measureId,
						processInstanceId);
		return persistedValue;
	}

}
