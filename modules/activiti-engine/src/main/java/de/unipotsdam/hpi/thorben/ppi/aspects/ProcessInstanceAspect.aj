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

package de.unipotsdam.hpi.thorben.ppi.aspects;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.PPIProcessInstanceEntity;

public aspect ProcessInstanceAspect {

	pointcut newInstance(ProcessDefinitionEntity processDefinition) : target(processDefinition) && call(ExecutionEntity ProcessDefinitionEntity.createProcessInstance(String, ActivityImpl));

	after(ProcessDefinitionEntity processDefinition) returning (ExecutionEntity processInstance): newInstance(processDefinition) {
		if (!processDefinition.getPPIs().isEmpty()) {
			PPIProcessInstanceEntity instanceEntity = PPIProcessInstanceEntity
					.fromExecution(processInstance);
			CommandContext commandContext = Context.getCommandContext();
			commandContext.getDbSqlSession().insert(instanceEntity);
		}
	}
}
