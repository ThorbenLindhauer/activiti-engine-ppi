package de.unipotsdam.hpi.thorben.ppi;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.test.Deployment;
import org.junit.Assert;

import de.unipotsdam.hpi.thorben.ppi.engine.PPIProcessEngine;
import de.unipotsdam.hpi.thorben.ppi.service.PPIService;

public class PPIParallelExecutionTest extends AbstractPPITest {

	private static final String TIME_MEASURE_DEFINITION_ID = "simpleTimeMeasure";
	private static final String COUNT_MEASURE_DEFINITION_ID = "simpleCountMeasure";
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
		// TODO assert that values have been written to the database
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
		// TODO assert that values have been written to the database
	}
	
	@Deployment(resources = { "de/uni-potsdam/hpi/thorben/ppi/SimpleCountMeasure.bpmn20.xml" })
	public void testAggregatedMeasureCalculation() throws InterruptedException {
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
		Number result = ppiService.calculateAggregatedMeasure("aggMeasure");
		Assert.assertNotSame(0, result);
		// TODO assert that values have been written to the database
	}
	
	private Thread createInstantiationThread(final RuntimeService runtime, final String processDefinitionKey) {
		return new Thread(new Runnable() {
			public void run() {
				runtime.startProcessInstanceByKey(processDefinitionKey);
			}
		});
	}
}
