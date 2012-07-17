package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.command;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.DataMeasureInstance;

public class GetDataMeasureInstanceCommand implements
		Command<DataMeasureInstance> {
	
	private String measureId;
	private String processInstanceId;

	public GetDataMeasureInstanceCommand(String measureId,
			String processInstanceId) {
		this.measureId = measureId;
		this.processInstanceId = processInstanceId;
	}

	public DataMeasureInstance execute(CommandContext commandContext) {
		return commandContext.getBaseMeasureManager().findDataMeasureInstance(measureId, processInstanceId);
	}

}
