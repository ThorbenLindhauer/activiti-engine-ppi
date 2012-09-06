package de.unipotsdam.hpi.thorben.ppi;

import java.util.Map;
import java.util.logging.Logger;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;

public abstract class AbstractPPITest extends PluggableActivitiTestCase {

	protected static final Logger LOGGER = Logger.getLogger(AbstractPPITest.class.getName());
	private static final String PPI_ENGINE_CONFIG = "de/uni-potsdam/hpi/thorben/ppi/activiti.h2.cfg.xml";
	
	protected void initializeProcessEngine() {
		if (cachedProcessEngine == null) {
			cachedProcessEngine = ProcessEngineConfiguration
					.createProcessEngineConfigurationFromResource(
							PPI_ENGINE_CONFIG).buildProcessEngine();
			if (cachedProcessEngine == null) {
				throw new ActivitiException(
						"no default process engine available");
			}
		}
		processEngine = cachedProcessEngine;
	}
	

	protected Thread createInstantiationThread(final RuntimeService runtime, final String processDefinitionKey) {
		return new Thread(new Runnable() {
			public void run() {
				runtime.startProcessInstanceByKey(processDefinitionKey);
			}
		});
	}
	
	protected Thread createInstantiationThread(final RuntimeService runtime, final String processDefinitionKey, final Map<String, Object> variables) {
		return new Thread(new Runnable() {
			public void run() {
				runtime.startProcessInstanceByKey(processDefinitionKey, variables);
			}
		});
	}
}
