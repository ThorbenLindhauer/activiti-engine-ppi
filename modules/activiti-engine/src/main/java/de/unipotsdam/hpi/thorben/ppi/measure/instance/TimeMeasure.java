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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.util.ClockUtil;
import org.activiti.engine.repository.ProcessDefinition;

import de.unipotsdam.hpi.thorben.ppi.condition.PPICondition;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ConditionEvent;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.SingleTimeMeasureValue;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.TimeMeasureInstance;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.command.InsertSingleTimeValueCommand;
import de.unipotsdam.hpi.thorben.ppi.measure.query.SingleTimeMeasureValueQuery;

public class TimeMeasure extends EventListeningBaseMeasure<TimeMeasureInstance> {

	public static final int FROM = 0;
	public static final int TO = 1;
	
	private PPICondition fromCondition;
	private PPICondition toCondition;

	public TimeMeasure(String id, ProcessDefinition processDefinition) {
		super(id, processDefinition);
	}

	public void setFromCondition(PPICondition fromCondition) {
		this.fromCondition = fromCondition;
	}

	public void setToCondition(PPICondition toCondition) {
		this.toCondition = toCondition;
	}

	@Override
	public void update(ConditionEvent event) {
		CommandContext commandContext = Context.getCommandContext();

		String processInstanceId = event.getProcessInstanceId();
		SingleTimeMeasureValue singleValue = new SingleTimeMeasureValue();
		singleValue.setTimestamp(ClockUtil.getCurrentTime());
		singleValue.setProcessInstanceId(processInstanceId);
		singleValue.setTimeMeasureId(id);
		if (fromCondition.isFulfilledBy(event)) {

			singleValue.setFrom(true);
			singleValue.setTo(false);
			new InsertSingleTimeValueCommand(singleValue)
					.execute(commandContext);
		} else if (toCondition.isFulfilledBy(event)) {
			singleValue.setFrom(false);
			singleValue.setTo(true);
			new InsertSingleTimeValueCommand(singleValue)
			.execute(commandContext);
		}
	}

	@Override
	public List<TimeMeasureInstance> getAllValues() {
		CommandContext context = Context.getCommandContext();
		SingleTimeMeasureValueQuery query = context.getBaseMeasureManager()
				.createNewSingleTimeMeasureValueQuery()
				.processDefinitionId(processDefinition.getId()).timeMeasureId(id);
		
		Map<String, TimeMeasureInstance> instances = new HashMap<String, TimeMeasureInstance>();
		List<SingleTimeMeasureValue> singleValues = query.list();
		for (SingleTimeMeasureValue val : singleValues) {
			String instanceKey = val.getProcessInstanceId() + "#" + val.getTimeMeasureId();
			TimeMeasureInstance instance = instances.get(instanceKey);
			if (instance == null) {
				instance = new TimeMeasureInstance();
				instance.setMeasureId(id);
				instance.setProcessInstanceId(val.getProcessInstanceId());
				instances.put(instanceKey, instance);
			}
			instance.addSingleValue(val);
		}
		return new ArrayList<TimeMeasureInstance>(instances.values());
	}
}
