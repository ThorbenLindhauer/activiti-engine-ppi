package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class GetSingleTimeValueCommand implements
		Command<SingleTimeMeasureValue> {
	
	private String id;
	
	

	public GetSingleTimeValueCommand(String id) {
		this.id = id;
	}



	public SingleTimeMeasureValue execute(CommandContext commandContext) {
		SingleTimeMeasureValue persistedSingleValue = commandContext
				.getBaseMeasureManager().findSingleTimeMeasureValue(
						id);
		return persistedSingleValue;
	}

}
