package de.unipotsdam.hpi.thorben.ppi;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.test.Deployment;

public class PPIParallelExecutionTest extends AbstractPPITest {

	private static final String PROCESS_DEFINITION_ID = "simpleTimeMeasure";
	private static final int INSTANCES = 20;

	@Deployment(resources = { "de/uni-potsdam/hpi/thorben/ppi/SimpleTimeMeasure.bpmn20.xml" })
	public void testPPIProcessExecution() throws InterruptedException {
		final RuntimeService runtime = processEngine.getRuntimeService();
		
		List<Thread> instanceThreads = new ArrayList<Thread>();
		for (int i = 0; i < INSTANCES; i++) {
			Thread t = new Thread(new Runnable() {
				public void run() {
					runtime.startProcessInstanceByKey(PROCESS_DEFINITION_ID);
				}
			});
			t.start();
			instanceThreads.add(t);
		}
		
		// wait until all threads are done
		for (Thread t : instanceThreads) {
			t.join();
		}
		// TODO assert that values have been written to the database
	}
}
