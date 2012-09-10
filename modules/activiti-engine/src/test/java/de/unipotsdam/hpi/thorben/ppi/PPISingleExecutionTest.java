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

package de.unipotsdam.hpi.thorben.ppi;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.test.Deployment;
import org.junit.Assert;

import de.unipotsdam.hpi.thorben.ppi.data.ComponentToDecorate;
import de.unipotsdam.hpi.thorben.ppi.engine.PPIProcessEngine;
import de.unipotsdam.hpi.thorben.ppi.service.PPIService;

public class PPISingleExecutionTest extends AbstractPPITest {

	private static final String TIME_MEASURE_DEFINITION_ID = "simpleTimeMeasure";
	private static final String COUNT_MEASURE_DEFINITION_ID = "simpleCountMeasure";
	private static final String DERIVED_MEASURE_DEFINITION_ID = "simpleDerivedProcessMeasure";
	private static final String DATA_MEASURE_DEFINITION_ID = "simpleDataMeasure";
	
	private static final String UNFULFILLABLE_PPI_DEFINITION_ID = "unfulfillablePPI";

	@Deployment(resources = { "de/uni-potsdam/hpi/thorben/ppi/SimpleTimeMeasure.bpmn20.xml" })
	public void testTimeMeasureExecution() throws InterruptedException {
		try {
			RuntimeService runtime = processEngine.getRuntimeService();
			runtime.startProcessInstanceByKey(TIME_MEASURE_DEFINITION_ID);
		} catch (Exception e) {
			Assert.fail();
		}
		
		PPIProcessEngine engine = (PPIProcessEngine)processEngine;
		PPIService ppiService = engine.getPPIService();
		Number result = ppiService.calculateAggregatedMeasure("aggMeasure", TIME_MEASURE_DEFINITION_ID);
		Assert.assertNotSame(0, result);
	}
	
	@Deployment(resources = { "de/uni-potsdam/hpi/thorben/ppi/SimpleCountMeasure.bpmn20.xml" })
	public void testCountMeasureExecution() throws InterruptedException {
		try {
			RuntimeService runtime = processEngine.getRuntimeService();
			runtime.startProcessInstanceByKey(COUNT_MEASURE_DEFINITION_ID);
		} catch (Exception e) {
			Assert.fail();
		}
		
	}
	
	@Deployment(resources = { "de/uni-potsdam/hpi/thorben/ppi/SimpleDerivedProcessMeasure.bpmn20.xml" })
	public void testDerivedProcessMeasureExecution() throws InterruptedException {
		RuntimeService runtime = processEngine.getRuntimeService();
		runtime.startProcessInstanceByKey(DERIVED_MEASURE_DEFINITION_ID);
		
		PPIProcessEngine engine = (PPIProcessEngine)processEngine;
		PPIService ppiService = engine.getPPIService();
		Number result = ppiService.calculateAggregatedMeasure("derivedMeasure", DERIVED_MEASURE_DEFINITION_ID);
		Assert.assertFalse(new Long(0).equals(result));
		LOGGER.info(result.toString());
	}
	
	@Deployment(resources = { "de/uni-potsdam/hpi/thorben/ppi/SimpleAggregatedDataMeasure.bpmn20.xml" })
	public void testDataMeasureExecutionWithValidVariables() throws InterruptedException {
		RuntimeService runtime = processEngine.getRuntimeService();
		Map<String, Object> variables = new HashMap<String, Object>();
		ComponentToDecorate dataObject = new ComponentToDecorate();
		dataObject.setNumber(4);
		variables.put("dataObject", dataObject);
		
		runtime.startProcessInstanceByKey(DATA_MEASURE_DEFINITION_ID, variables);
		
		PPIProcessEngine engine = (PPIProcessEngine)processEngine;
		PPIService ppiService = engine.getPPIService();
		Number result = ppiService.calculateAggregatedMeasure("aggMeasure", DATA_MEASURE_DEFINITION_ID);
		Assert.assertEquals(4.0, result);
		LOGGER.info(result.toString());
	}	

	@Deployment(resources = { "de/uni-potsdam/hpi/thorben/ppi/SimpleAggregatedDataMeasure.bpmn20.xml" })
	public void testDataMeasureExecutionWithNonExistingVariables() {
		RuntimeService runtime = processEngine.getRuntimeService();
		runtime.startProcessInstanceByKey(DATA_MEASURE_DEFINITION_ID);
		
		PPIProcessEngine engine = (PPIProcessEngine)processEngine;
		PPIService ppiService = engine.getPPIService();
		
		try {
			ppiService.calculateAggregatedMeasure("aggMeasure", DATA_MEASURE_DEFINITION_ID);
			Assert.fail("This should not succeed.");
		} catch (ActivitiException e) {
			// happy path
		}
		
	}
	
	@Deployment(resources = { "de/uni-potsdam/hpi/thorben/ppi/SimpleCountMeasure.bpmn20.xml" })
	public void testPPICalculation() {
		RuntimeService runtime = processEngine.getRuntimeService();
		runtime.startProcessInstanceByKey(COUNT_MEASURE_DEFINITION_ID);
		
		PPIProcessEngine engine = (PPIProcessEngine)processEngine;
		PPIService ppiService = engine.getPPIService();
		
		Number result = ppiService.calculatePPI("my_ppi", COUNT_MEASURE_DEFINITION_ID);
		Assert.assertEquals(1, result);
		
		boolean fulfilled = ppiService.PPIfulfilled("my_ppi", COUNT_MEASURE_DEFINITION_ID);
		Assert.assertTrue(fulfilled);
	}
	
	@Deployment(resources = { "de/uni-potsdam/hpi/thorben/ppi/UnfulfillablePPI.bpmn20.xml" })
	public void testUnfulfilledPPI() {
		RuntimeService runtime = processEngine.getRuntimeService();
		runtime.startProcessInstanceByKey(UNFULFILLABLE_PPI_DEFINITION_ID);
		
		PPIProcessEngine engine = (PPIProcessEngine)processEngine;
		PPIService ppiService = engine.getPPIService();
		
		boolean fulfilled = ppiService.PPIfulfilled("my_ppi", UNFULFILLABLE_PPI_DEFINITION_ID);
		Assert.assertFalse(fulfilled);
	}
	
}
