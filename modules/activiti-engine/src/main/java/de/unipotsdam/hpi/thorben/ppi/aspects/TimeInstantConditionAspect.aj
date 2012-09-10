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

import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.runtime.AtomicOperation;
import org.activiti.engine.impl.pvm.runtime.AtomicOperationActivityExecute;
import org.activiti.engine.impl.pvm.runtime.AtomicOperationTransitionDestroyScope;

import de.unipotsdam.hpi.thorben.ppi.condition.event.ActivityEndEvent;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ActivityStartEvent;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ConditionEvent;

/**
 * Defines all points of emitting time instant condition events.
 * @author Thorben
 *
 */
public aspect TimeInstantConditionAspect {
	/**
	 * Captures the ActivityStartCondition
	 * @param operation
	 * @param execution
	 */
	pointcut execute(AtomicOperationActivityExecute operation,
			ExecutionEntity execution) : target(operation) && call(void AtomicOperation+.execute(..)) && args(execution);
	
	/**
	 * Signals the ActivityStartCondition
	 * @param operation
	 * @param execution
	 */
	before(AtomicOperationActivityExecute operation, ExecutionEntity execution) : execute(operation, execution) {
		ActivityImpl activity = (ActivityImpl) execution.getActivity();
		
		ConditionEvent event = new ActivityStartEvent(activity);
		event.setProcessInstanceId(execution.getProcessInstanceId());

		activity.notifyObservers(event);
	}
	
	/**
	 * Captures the ActivityEndCondition
	 * @param operation
	 * @param execution
	 */
	pointcut finish(AtomicOperationTransitionDestroyScope operation, ExecutionEntity execution) : target(operation) && call(void AtomicOperation+.execute(..)) && args(execution);
	
	/**
	 * Signals the ActivityEndCondition
	 * @param operation
	 * @param execution
	 */
	before(AtomicOperationTransitionDestroyScope operation, ExecutionEntity execution) : finish(operation, execution) {
		ActivityImpl activity = (ActivityImpl) execution.getActivity();

		ConditionEvent event = new ActivityEndEvent(activity);
		event.setProcessInstanceId(execution.getProcessInstanceId());
		activity.notifyObservers(event);
	}
	
	
}
