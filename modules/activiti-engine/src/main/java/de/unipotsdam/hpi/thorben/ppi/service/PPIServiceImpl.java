package de.unipotsdam.hpi.thorben.ppi.service;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.ServiceImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;

import de.unipotsdam.hpi.thorben.ppi.measure.process.ProcessMeasure;

public class PPIServiceImpl extends ServiceImpl implements PPIService {

	@Override
	public Number calculateAggregatedMeasure(String id) {
		// TODO refactor heavily
		// check if this command can work in a regular environment
		ProcessDefinitionImpl processDefinition = commandExecutor
				.execute(new Command<ProcessDefinitionEntity>() {
					public ProcessDefinitionEntity execute(
							CommandContext commandContext) {
						return Context
								.getProcessEngineConfiguration()
								.getDeploymentCache()
								.findDeployedLatestProcessDefinitionByKey(
										"simpleTimeMeasure");
					}
				});
		for (ProcessMeasure<?> measure : processDefinition.getMeasures()) {
			if (measure.getId().equals(id)) {
				return measure.calculate();
			}
		}
		throw new ActivitiException("No process measure with id " + id + " defined.");
	}

}
