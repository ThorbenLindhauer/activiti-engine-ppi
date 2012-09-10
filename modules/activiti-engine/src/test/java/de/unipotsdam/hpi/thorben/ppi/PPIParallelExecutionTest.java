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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.test.Deployment;
import org.junit.Assert;

import de.unipotsdam.hpi.thorben.ppi.data.ComponentToDecorate;
import de.unipotsdam.hpi.thorben.ppi.engine.PPIProcessEngine;
import de.unipotsdam.hpi.thorben.ppi.service.PPIService;

public class PPIParallelExecutionTest extends AbstractPPITest {

	private static final String TIME_MEASURE_DEFINITION_ID = "simpleTimeMeasure";
	private static final String COUNT_MEASURE_DEFINITION_ID = "simpleCountMeasure";
	private static final String DATA_MEASURE_DEFINITION_ID = "simpleDataMeasure";
	private static final int INSTANCES = 20;

	@Deployment(resources = { "de/uni-potsdam/hpi/thorben/ppi/SimpleTimeMeasure.bpmn20.xml" })
	public void testTimeMeasureExecution() throws InterruptedException {
		final RuntimeService runtime = processEngine.getRuntimeService();
		
		List<Thread> instanceThreads = new ArrayList<Thread>();
		for (int i = 0; i < INSTANCES; i++) {
			Thread t = createInstantiationThread(runtime, TIME_MEASURE_DEFINITION_ID);
			t.start();
			instanceThreads.add(t);
		}
		
		// wait until all threads are done
		for (Thread t : instanceThreads) {
			t.join();
		}
	}
	
	@Deployment(resources = { "de/uni-potsdam/hpi/thorben/ppi/SimpleCountMeasure.bpmn20.xml" })
	public void testCountMeasureExecution() throws InterruptedException {
		final RuntimeService runtime = processEngine.getRuntimeService();
		
		List<Thread> instanceThreads = new ArrayList<Thread>();
		for (int i = 0; i < INSTANCES; i++) {
			Thread t = createInstantiationThread(runtime, COUNT_MEASURE_DEFINITION_ID);
			t.start();
			instanceThreads.add(t);
		}
		
		// wait until all threads are done
		for (Thread t : instanceThreads) {
			t.join();
		}
	}
	
	@Deployment(resources = { "de/uni-potsdam/hpi/thorben/ppi/SimpleTimeMeasure.bpmn20.xml" })
	public void testAverageMeasureCalculation() throws InterruptedException {
		final RuntimeService runtime = processEngine.getRuntimeService();
		
		List<Thread> instanceThreads = new ArrayList<Thread>();
		for (int i = 0; i < INSTANCES; i++) {
			Thread t = createInstantiationThread(runtime, TIME_MEASURE_DEFINITION_ID);
			t.start();
			instanceThreads.add(t);
		}
		
		// wait until all threads are done
		for (Thread t : instanceThreads) {
			t.join();
		}
		
		PPIProcessEngine engine = (PPIProcessEngine)processEngine;
		PPIService ppiService = engine.getPPIService();
		Number result = ppiService.calculateAggregatedMeasure("aggMeasure", TIME_MEASURE_DEFINITION_ID);
		Assert.assertNotSame(0, result);
		LOGGER.info(result.toString());
	}
	
	@Deployment(resources = { "de/uni-potsdam/hpi/thorben/ppi/SimpleCountMeasure.bpmn20.xml" })
	public void testSumMeasureCalculation() throws InterruptedException {
		final RuntimeService runtime = processEngine.getRuntimeService();
		
		List<Thread> instanceThreads = new ArrayList<Thread>();
		for (int i = 0; i < INSTANCES; i++) {
			Thread t = createInstantiationThread(runtime, COUNT_MEASURE_DEFINITION_ID);
			t.start();
			instanceThreads.add(t);
		}
		
		// wait until all threads are done
		for (Thread t : instanceThreads) {
			t.join();
		}
		
		PPIProcessEngine engine = (PPIProcessEngine)processEngine;
		PPIService ppiService = engine.getPPIService();
		Number result = ppiService.calculateAggregatedMeasure("aggMeasure", COUNT_MEASURE_DEFINITION_ID);
		Assert.assertNotSame(0, result);
		LOGGER.info(result.toString());
	}
	
	@Deployment(resources = { "de/uni-potsdam/hpi/thorben/ppi/SimpleAggregatedDataMeasure.bpmn20.xml" })
	public void testAggregatedDataMeasure() throws InterruptedException {
		RuntimeService runtime = processEngine.getRuntimeService();
		
		List<Thread> instanceThreads = new ArrayList<Thread>();
		for (int i = 0; i < INSTANCES; i++) {
			Map<String, Object> variables = new HashMap<String, Object>();
			ComponentToDecorate dataObject = new ComponentToDecorate();
			
			// set the data property to a random number between 1 and 10.
			int number = (int) ((Math.random() * 10) + 1);
			dataObject.setNumber(number);
			variables.put("dataObject", dataObject);
			Thread t = createInstantiationThread(runtime, DATA_MEASURE_DEFINITION_ID, variables);
			t.start();
			instanceThreads.add(t);
		}
		
		// wait until all threads are done
		for (Thread t : instanceThreads) {
			t.join();
		}
		
		PPIProcessEngine engine = (PPIProcessEngine)processEngine;
		PPIService ppiService = engine.getPPIService();
		Number result = ppiService.calculateAggregatedMeasure("aggMeasure", DATA_MEASURE_DEFINITION_ID);
		Assert.assertNotSame(0.0, result);
		LOGGER.info(result.toString());
	}
	
}
