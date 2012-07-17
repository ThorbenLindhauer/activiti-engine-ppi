package de.unipotsdam.hpi.thorben.ppi.service;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;

import de.unipotsdam.hpi.thorben.ppi.PPI;
import de.unipotsdam.hpi.thorben.ppi.measure.process.ProcessMeasure;

public class PPIFulfilledCommand implements Command<Boolean> {

	private String ppiID;
	private String processDefinitionId;
	public PPIFulfilledCommand(String ppiID, String processDefinitionId) {
		this.ppiID = ppiID;
		this.processDefinitionId = processDefinitionId;
	}
	
	public Boolean execute(CommandContext commandContext) {
		ProcessDefinitionImpl processDefinition = Context
				.getProcessEngineConfiguration()
				.getDeploymentCache()
				.findDeployedLatestProcessDefinitionByKey(
						processDefinitionId);
		
		for (PPI ppi : processDefinition.getPPIs()) {
			if (ppi.getId().equals(ppiID)) {
				ProcessMeasure<?> measure = ppi.getProcessMeasure();
				Number result = measure.calculate();
				return ppi.isFulfilledBy(result);
			}
		}
		
		throw new ActivitiException("No ppi with id " + ppiID + " defined.");
	}

}
