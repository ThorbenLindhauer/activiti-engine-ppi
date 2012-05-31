package de.unipotsdam.hpi.thorben.ppi.measure;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.util.ClockUtil;

import de.unipotsdam.hpi.thorben.ppi.condition.PPICondition;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ConditionEvent;
import de.unipotsdam.hpi.thorben.ppi.measure.entity.InsertOrUpdateTimeValueCommand;
import de.unipotsdam.hpi.thorben.ppi.measure.entity.TimeMeasureValue;
import de.unipotsdam.hpi.thorben.ppi.measure.query.TimeMeasureValueQuery;
import de.unipotsdam.hpi.thorben.ppi.measure.query.TimeMeasureValueQueryImpl;

public class TimeMeasure extends BaseMeasure {


	private PPICondition fromCondition;
	private PPICondition toCondition;
	
	public TimeMeasure(String id) {
		super(id);
	}
		
	public void setFromCondition(PPICondition fromCondition) {
		this.fromCondition = fromCondition;
	}
	
	public void setToCondition(PPICondition toCondition) {
		this.toCondition = toCondition;
	}

	@Override
	public void update(ConditionEvent event) {
		TimeMeasureValue timeMeasureValue;
		
		// TODO refactor
		// TODO add exception handling for cases in which the time measure was not modelled in a "sound" way, such that 
		// from and to condition would be triggered in reverse order or multiple times.
		if (fromCondition.isFulfilledBy(event)) {
			timeMeasureValue = new TimeMeasureValue();
			String processInstanceId = event.getProcessInstanceId();
			timeMeasureValue.setFrom(ClockUtil.getCurrentTime());
			timeMeasureValue.setMeasureId(id);
			timeMeasureValue.setProcessInstanceId(processInstanceId);
			
			CommandContext commandContext = Context.getCommandContext();			
			new InsertOrUpdateTimeValueCommand(timeMeasureValue).execute(commandContext);
		}
		
		
		if (toCondition.isFulfilledBy(event)) {
			
			timeMeasureValue = new TimeMeasureValue();
			String processInstanceId = event.getProcessInstanceId();
			timeMeasureValue.setMeasureId(id);
			timeMeasureValue.setProcessInstanceId(processInstanceId);
			
			timeMeasureValue.setTo(ClockUtil.getCurrentTime());
			
			CommandContext commandContext = Context.getCommandContext();			
			new InsertOrUpdateTimeValueCommand(timeMeasureValue).execute(commandContext);
		}
		
	}	
}
