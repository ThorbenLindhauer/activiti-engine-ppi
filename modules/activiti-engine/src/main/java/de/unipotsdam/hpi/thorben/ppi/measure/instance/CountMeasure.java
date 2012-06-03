package de.unipotsdam.hpi.thorben.ppi.measure.instance;

import java.util.List;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.CommandContext;

import de.unipotsdam.hpi.thorben.ppi.condition.PPICondition;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ConditionEvent;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.CountMeasureValue;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.InsertOrUpdateCountValueCommand;

public class CountMeasure extends BaseMeasure<CountMeasureValue> {

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
			CountMeasureValue value = commandContext.getBaseMeasureManager().findCountMeasureValue(id, processInstanceId);
			
			if (value == null) {
				value = new CountMeasureValue();
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
	public List<CountMeasureValue> getAllValues() {
		// TODO Auto-generated method stub
		return null;
	}

}
