package de.unipotsdam.hpi.thorben.ppi.parse;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.CommandExecutor;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.test.Deployment;
import org.apache.log4j.Logger;
import org.junit.Assert;

import de.unipotsdam.hpi.thorben.ppi.measure.BaseMeasure;

public class PPIParseTest extends AbstractPPITest {

	private Logger logger = Logger.getLogger(PPIParseTest.class);

	@Deployment(resources = { "de/uni-potsdam/hpi/thorben/ppi/SimpleServiceTask.bpmn20.xml" })
	public void testParsePPIDefinition() {
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
										"simpleServiceTask");
					}
				});

		System.out.println("got "
				+ processDefinitionEntity.getActivities().size()
				+ " activities");
		for (ActivityImpl activity : processDefinitionEntity.getActivities()) {
			if (activity.getId().equals("serviceTask")) {
				logger.info("Found service task");
				List<BaseMeasure> startMeasures = activity.getStartMeasures();
				List<BaseMeasure> endMeasures = activity.getEndMeasures();
				
				Assert.assertEquals(1, startMeasures.size());
				Assert.assertEquals(1, endMeasures.size());
				
				BaseMeasure startMeasure = startMeasures.get(0);
				BaseMeasure endMeasure = endMeasures.get(0);
				Assert.assertTrue(startMeasure == endMeasure);
			}
		}
		Assert.assertTrue(true);
	}

	// @Before
	// public void setUp() {
	// engine = ProcessEngines.getDefaultProcessEngine();
	// }
	//
	// @Test
	// public void test() {
	// RepositoryService repoService = engine.getRepositoryService();
	//
	// repoService.createDeployment().addClasspathResource("SimpleServiceTask.bpmn20.xml").deploy();
	//
	// RuntimeService runtime = engine.getRuntimeService();
	// runtime.startProcessInstanceByKey("simpleServiceTask");
	// }

}
