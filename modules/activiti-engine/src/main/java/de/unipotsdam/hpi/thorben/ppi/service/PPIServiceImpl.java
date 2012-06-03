package de.unipotsdam.hpi.thorben.ppi.service;

import org.activiti.engine.impl.ServiceImpl;

import de.unipotsdam.hpi.thorben.ppi.measure.query.CreateTimeMeasureValueQueryCmd;
import de.unipotsdam.hpi.thorben.ppi.measure.query.TimeMeasureValueQuery;

public class PPIServiceImpl extends ServiceImpl implements PPIService {

	@Override
	public Number calculateAggregatedMeasure(String id, final String processDefinitionId) {
		return commandExecutor.execute(new CalculateProcessMeasureCommand(id, processDefinitionId));
	}

	@Override
	public TimeMeasureValueQuery createTimeMeasureValueQuery() {
		return commandExecutor.execute(new CreateTimeMeasureValueQueryCmd());
	}
	
	

}
