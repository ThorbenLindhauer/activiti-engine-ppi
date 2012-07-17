package de.unipotsdam.hpi.thorben.ppi.service;

import org.activiti.engine.impl.ServiceImpl;

public class PPIServiceImpl extends ServiceImpl implements PPIService {

	public Number calculateAggregatedMeasure(String id, final String processDefinitionId) {
		return commandExecutor.execute(new CalculateProcessMeasureCommand(id, processDefinitionId));
	}

	public Number calculatePPI(String id, String processDefinitionId) {
		return commandExecutor.execute(new CalculatePPICommand(id, processDefinitionId));
	}

	public boolean PPIfulfilled(String id, String processDefinitionId) {
		return commandExecutor.execute(new PPIFulfilledCommand(id, processDefinitionId));
	}
}
