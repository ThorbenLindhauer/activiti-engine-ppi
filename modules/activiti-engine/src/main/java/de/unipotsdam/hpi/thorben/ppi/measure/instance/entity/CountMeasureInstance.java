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

package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity;

import java.util.HashMap;
import java.util.Map;

public class CountMeasureInstance extends BaseMeasureInstance {

	private int count;
	
	public CountMeasureInstance() {
		count = 0;
	}
	
	public Object getPersistentState() {
		Map<String, Object> persistentState = new HashMap<String, Object>();
	    persistentState.put("id", id);
	    persistentState.put("count", count);
	    persistentState.put("measureId", measureId);
	    persistentState.put("processInstanceId", processInstanceId);
	    return persistentState;
	}
	
	public void increaseCount() {
		count++;
	}
	
	public void update(CountMeasureInstance value) {
		count = value.count;
	}

	@Override
	public Number calculate() {
		return count;
	}


}
