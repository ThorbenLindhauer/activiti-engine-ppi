package de.unipotsdam.hpi.thorben.ppi.service;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;

import de.unipotsdam.hpi.thorben.ppi.measure.process.ProcessMeasure;

public class CalculateProcessMeasureCommand implements Command<Number> {

	private String measureId;
	private String processDefinitionId;
	public CalculateProcessMeasureCommand(String measureId, String processDefinitionId) {
		this.measureId = measureId;
		this.processDefinitionId = processDefinitionId;
	}
	
	public Number execute(CommandContext commandContext) {
		ProcessDefinitionImpl processDefinition = Context
				.getProcessEngineConfiguration()
				.getDeploymentCache()
				.findDeployedLatestProcessDefinitionByKey(
						processDefinitionId);
		
		for (ProcessMeasure<?> measure : processDefinition.getProcessMeasures()) {
			if (measure.getId().equals(measureId)) {
				return measure.calculate();
			}
		}
		
		throw new ActivitiException("No process measure with id " + measureId + " defined.");
	}

}
