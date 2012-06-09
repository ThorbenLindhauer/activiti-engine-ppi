package de.unipotsdam.hpi.thorben.ppi;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.test.Deployment;
import org.junit.Assert;

import de.unipotsdam.hpi.thorben.ppi.engine.PPIProcessEngine;
import de.unipotsdam.hpi.thorben.ppi.service.PPIService;

public class PPISingleExecutionTest extends AbstractPPITest {

	private static final String TIME_MEASURE_DEFINITION_ID = "simpleTimeMeasure";
	private static final String COUNT_MEASURE_DEFINITION_ID = "simpleCountMeasure";
	private static final String DERIVED_MEASURE_DEFINITION_ID = "simpleDerivedProcessMeasure";

	@Deployment(resources = { "de/uni-potsdam/hpi/thorben/ppi/SimpleTimeMeasure.bpmn20.xml" })
	public void testTimeMeasureExecution() throws InterruptedException {
		RuntimeService runtime = processEngine.getRuntimeService();
		runtime.startProcessInstanceByKey(TIME_MEASURE_DEFINITION_ID);
		// TODO assert that values have been written to the database
	}
	
	@Deployment(resources = { "de/uni-potsdam/hpi/thorben/ppi/SimpleCountMeasure.bpmn20.xml" })
	public void testCountMeasureExecution() throws InterruptedException {
		RuntimeService runtime = processEngine.getRuntimeService();
		runtime.startProcessInstanceByKey(COUNT_MEASURE_DEFINITION_ID);
		// TODO assert that values have been written to the database
	}
	
	@Deployment(resources = { "de/uni-potsdam/hpi/thorben/ppi/SimpleDerivedProcessMeasure.bpmn20.xml" })
	public void testDerivedProcessMeasureExecution() throws InterruptedException {
		RuntimeService runtime = processEngine.getRuntimeService();
		runtime.startProcessInstanceByKey(DERIVED_MEASURE_DEFINITION_ID);
		
		PPIProcessEngine engine = (PPIProcessEngine)processEngine;
		PPIService ppiService = engine.getPPIService();
		Number result = ppiService.calculateAggregatedMeasure("derivedMeasure", DERIVED_MEASURE_DEFINITION_ID);
		Assert.assertNotSame(0, result);
		System.out.println(result);
	}
}
