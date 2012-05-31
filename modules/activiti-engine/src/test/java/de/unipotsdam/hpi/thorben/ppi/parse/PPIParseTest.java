package de.unipotsdam.hpi.thorben.ppi.parse;

import java.util.List;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.CommandExecutor;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.test.Deployment;
import org.apache.log4j.Logger;
import org.junit.Assert;

import de.unipotsdam.hpi.thorben.observer.Observer;
import de.unipotsdam.hpi.thorben.ppi.AbstractPPITest;
import de.unipotsdam.hpi.thorben.ppi.measure.CountMeasure;
import de.unipotsdam.hpi.thorben.ppi.measure.TimeMeasure;

public class PPIParseTest extends AbstractPPITest {

	private Logger logger = Logger.getLogger(PPIParseTest.class);

	@Deployment(resources = { "de/uni-potsdam/hpi/thorben/ppi/SimpleTimeMeasure.bpmn20.xml" })
	public void testParseSimpleTimeMeasureDefinition() {
		CommandExecutor commandExecutor = processEngineConfiguration
				.getCommandExecutorTxRequired();
		ProcessDefinitionEntity processDefinitionEntity = commandExecutor
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

		for (ActivityImpl activity : processDefinitionEntity.getActivities()) {
			if (activity.getId().equals("serviceTask")) {
				logger.info("Found service task");
				
				List<Observer> measures = activity.getObservers();
				
				// nur 1, da die Observers in einem set gespeichert sind (auch wenn die TimeMeasure zweimal hinzugefügt wurde)
				Assert.assertEquals(1, measures.size());
				Assert.assertTrue(measures.get(0) instanceof TimeMeasure);
			}
		}
	}
	
	@Deployment(resources = { "de/uni-potsdam/hpi/thorben/ppi/SimpleCountMeasure.bpmn20.xml" })
	public void testParseSimpleCountMeasureDefinition() {
		CommandExecutor commandExecutor = processEngineConfiguration
				.getCommandExecutorTxRequired();
		ProcessDefinitionEntity processDefinitionEntity = commandExecutor
				.execute(new Command<ProcessDefinitionEntity>() {
					public ProcessDefinitionEntity execute(
							CommandContext commandContext) {
						return Context
								.getProcessEngineConfiguration()
								.getDeploymentCache()
								.findDeployedLatestProcessDefinitionByKey(
										"simpleCountMeasure");
					}
				});

		for (ActivityImpl activity : processDefinitionEntity.getActivities()) {
			if (activity.getId().equals("serviceTask")) {
				logger.info("Found service task");
				
				List<Observer> measures = activity.getObservers();
				
				// nur 1, da die Observers in einem set gespeichert sind (auch wenn die TimeMeasure zweimal hinzugefügt wurde)
				Assert.assertEquals(1, measures.size());
				Assert.assertTrue(measures.get(0) instanceof CountMeasure);
			}
		}
	}
}
