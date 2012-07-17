package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

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
