package de.unipotsdam.hpi.thorben.ppi.measure.instance;

import java.util.List;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.CommandContext;

import de.unipotsdam.hpi.thorben.ppi.condition.PPICondition;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ConditionEvent;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.CountMeasureInstance;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.InsertOrUpdateCountValueCommand;
import de.unipotsdam.hpi.thorben.ppi.measure.query.CountMeasureInstanceQuery;

public class CountMeasure extends EventListeningBaseMeasure<CountMeasureInstance> {

	private PPICondition condition;
	
	public CountMeasure(String id) {
		super(id);
	}
	
	public void setCondition(PPICondition condition) {
		this.condition = condition;
	}
	
	@Override
	public void update(ConditionEvent event) {
		if (condition.isFulfilledBy(event)) {
			String processInstanceId = event.getProcessInstanceId();
			CommandContext commandContext = Context.getCommandContext();
			CountMeasureInstance value = commandContext.getBaseMeasureManager().findCountMeasureInstance(id, processInstanceId);
			
			if (value == null) {
				value = new CountMeasureInstance();
				value.setMeasureId(id);
				value.setProcessInstanceId(processInstanceId);
				value.increaseCount();
				new InsertOrUpdateCountValueCommand(value).execute(commandContext);
			} else {
				value.increaseCount();
				new InsertOrUpdateCountValueCommand(value).execute(commandContext);
			}
		}
	}

	@Override
	public List<CountMeasureInstance> getAllValues() {
		CommandContext context = Context.getCommandContext();		
		CountMeasureInstanceQuery query = context.getBaseMeasureManager().createNewCountMeasureValueQuery().measureId(id);
		return query.list();
	}

}
