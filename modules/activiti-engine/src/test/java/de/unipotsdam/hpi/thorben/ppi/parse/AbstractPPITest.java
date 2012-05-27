package de.unipotsdam.hpi.thorben.ppi.parse;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;

public abstract class AbstractPPITest extends PluggableActivitiTestCase {

	private static final String PPI_ENGINE_CONFIG = "de/uni-potsdam/hpi/thorben/ppi/activiti.cfg.xml";
	
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
}
