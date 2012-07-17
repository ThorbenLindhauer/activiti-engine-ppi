package de.unipotsdam.hpi.thorben.ppi.service;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;

import de.unipotsdam.hpi.thorben.ppi.PPI;
import de.unipotsdam.hpi.thorben.ppi.measure.process.ProcessMeasure;

public class CalculatePPICommand implements Command<Number> {

	private String ppiID;
	private String processDefinitionId;
	public CalculatePPICommand(String ppiID, String processDefinitionId) {
		this.ppiID = ppiID;
		this.processDefinitionId = processDefinitionId;
	}
	
	public Number execute(CommandContext commandContext) {
		ProcessDefinitionImpl processDefinition = Context
				.getProcessEngineConfiguration()
				.getDeploymentCache()
				.findDeployedLatestProcessDefinitionByKey(
						processDefinitionId);
		
		for (PPI ppi : processDefinition.getPPIs()) {
			if (ppi.getId().equals(ppiID)) {
				ProcessMeasure<?> measure = ppi.getProcessMeasure();
				return measure.calculate();
			}
		}
		
		throw new ActivitiException("No ppi with id " + ppiID + " defined.");
	}

}