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

public class MeasureIsolationTest extends AbstractPPITest {

	private static final int INSTANCES = 20;

	private static final String DATA_MEASURE_DEFINITION_ID = "simpleDataMeasure";
	private static final String ANOTHER_DATA_MEASURE_DEFINITION_ID = "anotherSimpleDataMeasure";
	
	private static final String MEASURE_NAME = "aggMeasure";

	/**
	 * Asserts, that two measures with the same id in different process
	 * definitions to not conflict with each other (e.g. values of single
	 * measures should be distinguished by their process definition).
	 * 
	 * @throws InterruptedException
	 */
	@Deployment(resources = {
			"de/uni-potsdam/hpi/thorben/ppi/SimpleAggregatedDataMeasure.bpmn20.xml",
			"de/uni-potsdam/hpi/thorben/ppi/AnotherAggregatedDataMeasure.bpmn20.xml" })
	public void testMeasureIsolation() throws InterruptedException {
		List<Thread> instanceThreads = new ArrayList<Thread>();
		int number = 1;
		int anotherNumber = 2;

		for (int i = 0; i < INSTANCES; i++) {
			Thread instanceThread = startProcessInstance(
					DATA_MEASURE_DEFINITION_ID, number);
			instanceThreads.add(instanceThread);
			instanceThread = startProcessInstance(
					ANOTHER_DATA_MEASURE_DEFINITION_ID, anotherNumber);
			instanceThreads.add(instanceThread);
		}

		// wait until all threads are done
		for (Thread t : instanceThreads) {
			t.join();
		}

		PPIProcessEngine engine = (PPIProcessEngine) processEngine;
		PPIService ppiService = engine.getPPIService();
		Number result = ppiService.calculateAggregatedMeasure(MEASURE_NAME,
				DATA_MEASURE_DEFINITION_ID);

		Assert.assertNotSame(
				"The measure " + MEASURE_NAME + " defined in "
						+ DATA_MEASURE_DEFINITION_ID
						+ " should not return the sum of all measures with the same name in all existing process definitions.",
				INSTANCES * ((double) number + (double) anotherNumber), result);
		Assert.assertEquals(
				"The measure " + MEASURE_NAME + " defined in "
						+ DATA_MEASURE_DEFINITION_ID
						+ " should only return values that were actually measured by process instances of it.",
				INSTANCES * (double) number, result);
		LOGGER.info(result.toString());
	}

	private Thread startProcessInstance(String definitionId,
			int dataPropertyValue) {
		RuntimeService runtime = processEngine.getRuntimeService();

		Map<String, Object> variables = new HashMap<String, Object>();
		ComponentToDecorate dataObject = new ComponentToDecorate();

		dataObject.setNumber(dataPropertyValue);
		variables.put("dataObject", dataObject);
		Thread t = createInstantiationThread(runtime, definitionId, variables);
		t.start();

		return t;
	}

}
