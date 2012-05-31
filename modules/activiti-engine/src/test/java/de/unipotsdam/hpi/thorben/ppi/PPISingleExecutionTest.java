package de.unipotsdam.hpi.thorben.ppi;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.test.Deployment;

public class PPISingleExecutionTest extends AbstractPPITest {

	private static final String PROCESS_DEFINITION_ID = "simpleServiceTask";

	@Deployment(resources = { "de/uni-potsdam/hpi/thorben/ppi/SimpleServiceTask.bpmn20.xml" })
	public void testPPIProcessExecution() throws InterruptedException {
		RuntimeService runtime = processEngine.getRuntimeService();
		runtime.startProcessInstanceByKey(PROCESS_DEFINITION_ID);
		// TODO assert that values have been written to the database
	}
}
