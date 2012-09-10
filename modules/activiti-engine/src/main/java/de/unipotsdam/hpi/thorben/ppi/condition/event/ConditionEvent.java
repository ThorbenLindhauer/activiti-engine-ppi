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

package de.unipotsdam.hpi.thorben.ppi.condition.event;

import de.unipotsdam.hpi.thorben.ppi.condition.ActivityEndCondition;
import de.unipotsdam.hpi.thorben.ppi.condition.ActivityStartCondition;
import de.unipotsdam.hpi.thorben.ppi.condition.PPICondition;

/**
 * Corresponds to Time Instant Conditions in the PPI ontology.
 * @author Thorben
 *
 */
public abstract class ConditionEvent {

	private String processInstanceId;
	
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	/*
	 *  pseudo double dispatch methods, to be overriden in subclasses
	 */
	public boolean fulfills(PPICondition condition) {
		return false;
	}
	
	public boolean fulfills(ActivityStartCondition condition) {
		return false;	
	}
	
	public boolean fulfills(ActivityEndCondition condition) {
		return false;	
	}	
}
