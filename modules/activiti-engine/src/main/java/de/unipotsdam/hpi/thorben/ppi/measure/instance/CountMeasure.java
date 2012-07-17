package de.unipotsdam.hpi.thorben.ppi.measure.instance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.repository.ProcessDefinition;

import de.unipotsdam.hpi.thorben.ppi.condition.PPICondition;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ConditionEvent;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.CountMeasureInstance;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.command.GetCountMeasureInstanceCommand;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.command.InsertCountInstanceCommand;
import de.unipotsdam.hpi.thorben.ppi.measure.query.CountMeasureInstanceQuery;

public class CountMeasure extends EventListeningBaseMeasure<CountMeasureInstance> {

	private PPICondition condition;
	private Map<String, CountMeasureInstance> instancesCache = new HashMap<String, CountMeasureInstance>();
	
	public CountMeasure(String id, ProcessDefinition processDefinition) {
		super(id, processDefinition);
	}
	
	public void setCondition(PPICondition condition) {
		this.condition = condition;
	}
	
	@Override
	public void update(ConditionEvent event) {
		if (condition.isFulfilledBy(event)) {
			String processInstanceId = event.getProcessInstanceId();
			CountMeasureInstance countMeasureInstance = findCachedCountMeasureInstance(processInstanceId);
			countMeasureInstance.increaseCount();
		}
	}

	/**
	 * Finds a count measure instance (or creates one) and ensures that it is in Activiti's cache
	 * @param processInstanceId
	 * @return
	 */
	private CountMeasureInstance findCachedCountMeasureInstance(
			String processInstanceId) {
		CommandContext commandContext = Context.getCommandContext();
		CountMeasureInstance countMeasureValue = new GetCountMeasureInstanceCommand(id, processInstanceId).execute(commandContext);
		if (countMeasureValue == null) {
			countMeasureValue = instancesCache.get(processInstanceId);
		}
		if (countMeasureValue == null) {
			countMeasureValue = new CountMeasureInstance();
			countMeasureValue.setMeasureId(id);
			countMeasureValue.setProcessInstanceId(processInstanceId);
			new InsertCountInstanceCommand(countMeasureValue).execute(commandContext);
		}
		instancesCache.put(processInstanceId, countMeasureValue);
		
		return countMeasureValue;
	}

	@Override
	public List<CountMeasureInstance> getAllValues() {
		CommandContext context = Context.getCommandContext();		
		CountMeasureInstanceQuery query = context.getBaseMeasureManager().createNewCountMeasureValueQuery().processDefinitionId(processDefinition.getId()).measureId(id);
		return query.list();
	}

}
