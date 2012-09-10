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

import org.activiti.engine.repository.ProcessDefinition;

import de.unipotsdam.hpi.thorben.observer.Observer;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ConditionEvent;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureInstance;

/**
 * Base Measures that take action triggered by Time Instant Conditions subclass
 * from this.
 * 
 * @author Thorben
 * 
 * @param <B>
 */
public abstract class EventListeningBaseMeasure<B extends BaseMeasureInstance>
		extends BaseMeasure<B> implements Observer {

	public EventListeningBaseMeasure(String id,
			ProcessDefinition processDefinition) {
		super(id, processDefinition);
	}

	public abstract void update(ConditionEvent event);
}
