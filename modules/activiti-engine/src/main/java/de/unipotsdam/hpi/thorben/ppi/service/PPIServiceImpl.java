package de.unipotsdam.hpi.thorben.ppi.service;

import org.activiti.engine.impl.ServiceImpl;

public class PPIServiceImpl extends ServiceImpl implements PPIService {

	public Number calculateAggregatedMeasure(String id, final String processDefinitionId) {
		return commandExecutor.execute(new CalculateProcessMeasureCommand(id, processDefinitionId));
	}
}
