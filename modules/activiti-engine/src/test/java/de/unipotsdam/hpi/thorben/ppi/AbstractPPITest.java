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
