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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.db.PersistentObject;

public class SingleTimeMeasureValue implements PersistentObject {

	private String id;
	private Date timestamp;
	private String timeMeasureId;
	private String processInstanceId;
	private boolean from;
	private boolean to;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getTimeMeasureId() {
		return timeMeasureId;
	}

	public void setTimeMeasureId(String timeMeasureId) {
		this.timeMeasureId = timeMeasureId;
	}
	
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public Date getTimetamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isFrom() {
		return from;
	}

	public void setFrom(boolean from) {
		this.from = from;
	}

	public boolean isTo() {
		return to;
	}

	public void setTo(boolean to) {
		this.to = to;
	}

	public Object getPersistentState() {
		Map<String, Object> persistentState = new HashMap<String, Object>();
	    persistentState.put("id", id);
	    persistentState.put("timestamp", timestamp);
	    persistentState.put("timeMeasureId", timeMeasureId);
	    persistentState.put("processInstanceId", processInstanceId);
	    return persistentState;
	}

	public void update(SingleTimeMeasureValue value) {
		if (value.timestamp != null) {
			this.timestamp = value.timestamp;
		}
	}
}
