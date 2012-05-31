package de.unipotsdam.hpi.thorben.ppi;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.test.Deployment;

public class PPISingleExecutionTest extends AbstractPPITest {

	private static final String TIME_MEASURE_DEFINITION_ID = "simpleTimeMeasure";
	private static final String COUNT_MEASURE_DEFINITION_ID = "simpleCountMeasure";

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
}
