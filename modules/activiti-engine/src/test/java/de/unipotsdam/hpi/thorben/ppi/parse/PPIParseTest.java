/* Copyright 2012 Thorben Lindhauer
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
import de.unipotsdam.hpi.thorben.ppi.measure.instance.CountMeasure;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.TimeMeasure;
import de.unipotsdam.hpi.thorben.ppi.measure.process.ProcessMeasure;

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
				
				// only 1, as the observers are stored in a set
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
				
				// only 1, as the observers are stored in a set
				Assert.assertEquals(1, measures.size());
				Assert.assertTrue(measures.get(0) instanceof CountMeasure);
			}
		}
	}
	
	// TODO write another test case that base measure is not owned by aggregated measure (see comment below)
	/**
	 * Only looking at the case that the aggregated measure has the base measures as sub elements.
	 */
	@Deployment(resources = { "de/uni-potsdam/hpi/thorben/ppi/SimpleTimeMeasure.bpmn20.xml" })
	public void testParseSimpleAggregatedMeasureDefinition() {
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

		List<ProcessMeasure<?>> measures = processDefinitionEntity.getProcessMeasures();
		Assert.assertEquals(1, measures.size());
	}
}
