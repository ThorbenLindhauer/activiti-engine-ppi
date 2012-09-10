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

import org.activiti.engine.ActivitiException;

import de.unipotsdam.hpi.thorben.ppi.measure.process.ProcessMeasure;
import de.unipotsdam.hpi.thorben.ppi.measure.process.TargetFunction;

public class PPI {

	private String id;
	private ProcessMeasure<?> processMeasure;
	private Number targetValue;
	
	private TargetFunction targetFunction;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ProcessMeasure<?> getProcessMeasure() {
		return processMeasure;
	}
	public void setProcessMeasure(ProcessMeasure<?> processMeasure) {
		this.processMeasure = processMeasure;
	}
	public void setTargetValue(Number targetValue) {
		this.targetValue = targetValue;
	}
	public void setTargetFunction(TargetFunction targetFunction) {
		this.targetFunction = targetFunction;
	}
	
	public boolean isFulfilledBy(Number number) {
		if (targetFunction == null || targetValue == null) {
			throw new ActivitiException("No target function or value was specified for this ppi.");
		}
		return targetFunction.apply(number, targetValue);
	}
	
}
