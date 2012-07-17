package de.unipotsdam.hpi.thorben.ppi.measure.instance;

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
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.command.GetSingleTimeValueCommand;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.command.GetTimeMeasureInstanceCommand;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.command.InsertSingleTimeValueCommand;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.command.InsertTimeInstanceCommand;
import de.unipotsdam.hpi.thorben.ppi.measure.query.TimeMeasureInstanceQuery;

public class TimeMeasure extends EventListeningBaseMeasure<TimeMeasureInstance> {

	private PPICondition fromCondition;
	private PPICondition toCondition;

	private Map<String, TimeMeasureInstance> instancesCache = new HashMap<String, TimeMeasureInstance>();

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
		if (fromCondition.isFulfilledBy(event)) {
			TimeMeasureInstance timeMeasureValue = findCachedTimeMeasureInstance(processInstanceId);
			SingleTimeMeasureValue singleValue = new SingleTimeMeasureValue();
			singleValue.setFrom(ClockUtil.getCurrentTime());
			singleValue.setTimeMeasureId(timeMeasureValue.getId());
			timeMeasureValue.getSingleValues().add(singleValue);

			new InsertSingleTimeValueCommand(singleValue)
					.execute(commandContext);
		}

		if (toCondition.isFulfilledBy(event)) {
			TimeMeasureInstance timeMeasureValue = findCachedTimeMeasureInstance(processInstanceId);

			for (SingleTimeMeasureValue singleValue : timeMeasureValue
					.getSingleValues()) {
				if (singleValue.getTo() == null) {
					// TODO refactor, this is hardly understandable
					// The following is null, if the value was not commited into
					// the database yet, but is still in Activiti's cache.
					SingleTimeMeasureValue persistedValue = new GetSingleTimeValueCommand(
							singleValue.getId()).execute(commandContext);
					if (persistedValue == null) {
						// this is the case that the single value was recently
						// created.
						// It should then be in the DBSqlSession cache and this
						// update should be sufficient.
						singleValue.setTo(ClockUtil.getCurrentTime());
					} else {
						persistedValue.setTo(ClockUtil.getCurrentTime());
					}
					break;
				}
			}
		}

	}

	/**
	 * Finds a count measure instance (or creates one) and ensures that it is in
	 * Activiti's cache. 
	 * It is also added to a local cache in this TimeMeasure, as the select commands do not access the Activiti cache.
	 * Otherwise a TimeMeasureInstance that was already created before, but not committed, could not be found.
	 * 
	 * Note: The in the TimeMeasureInstance referenced SingleTimeMeasureValues are loaded to Activiti's cache
	 * when the owning TimeMeasureInstance is loaded. This means,
	 * that if you later want to ensure that they are added to Activiti's cache,
	 * you have to explicitly load them again from the database.
	 * 
	 * @param processInstanceId
	 * @return
	 */
	private TimeMeasureInstance findCachedTimeMeasureInstance(
			String processInstanceId) {
		CommandContext commandContext = Context.getCommandContext();

		TimeMeasureInstance timeMeasureValue = new GetTimeMeasureInstanceCommand(
				id, processInstanceId).execute(commandContext);
		if (timeMeasureValue == null) {
			timeMeasureValue = instancesCache.get(processInstanceId);
		}
		if (timeMeasureValue == null) {
			timeMeasureValue = new TimeMeasureInstance();
			timeMeasureValue.setMeasureId(id);
			timeMeasureValue.setProcessInstanceId(processInstanceId);
			new InsertTimeInstanceCommand(timeMeasureValue)
					.execute(commandContext);
		}
		instancesCache.put(processInstanceId, timeMeasureValue);

		return timeMeasureValue;
	}

	@Override
	public List<TimeMeasureInstance> getAllValues() {
		CommandContext context = Context.getCommandContext();
		TimeMeasureInstanceQuery query = context.getBaseMeasureManager()
				.createNewTimeMeasureInstanceQuery()
				.processDefinitionId(processDefinition.getId()).measureId(id);
		return query.list();
	}
}
