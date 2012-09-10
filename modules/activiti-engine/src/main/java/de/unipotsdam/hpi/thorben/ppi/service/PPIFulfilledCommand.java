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

package de.unipotsdam.hpi.thorben.ppi.service;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;

import de.unipotsdam.hpi.thorben.ppi.PPI;
import de.unipotsdam.hpi.thorben.ppi.measure.process.ProcessMeasure;

public class PPIFulfilledCommand implements Command<Boolean> {

	private String ppiID;
	private String processDefinitionId;
	public PPIFulfilledCommand(String ppiID, String processDefinitionId) {
		this.ppiID = ppiID;
		this.processDefinitionId = processDefinitionId;
	}
	
	public Boolean execute(CommandContext commandContext) {
		ProcessDefinitionImpl processDefinition = Context
				.getProcessEngineConfiguration()
				.getDeploymentCache()
				.findDeployedLatestProcessDefinitionByKey(
						processDefinitionId);
		
		for (PPI ppi : processDefinition.getPPIs()) {
			if (ppi.getId().equals(ppiID)) {
				ProcessMeasure<?> measure = ppi.getProcessMeasure();
				Number result = measure.calculate();
				return ppi.isFulfilledBy(result);
			}
		}
		
		throw new ActivitiException("No ppi with id " + ppiID + " defined.");
	}

}
