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

package de.unipotsdam.hpi.thorben.ppi.measure.instance;

import java.util.List;

import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.pvm.runtime.InterpretableExecution;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.log4j.Logger;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureInstance;

public abstract class BaseMeasure<B extends BaseMeasureInstance> {
	private Logger log = Logger.getLogger(BaseMeasure.class);
	protected String id;
	protected ProcessDefinition processDefinition;

	public BaseMeasure(String id, ProcessDefinition processDefinition) {
		this.id = id;
		this.processDefinition = processDefinition;
	}
	
	protected void measure(InterpretableExecution execution, CommandContext context) {
		log.info("executed measure");
	}

	
	public abstract List<B> getAllValues();

	
}
