package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

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
