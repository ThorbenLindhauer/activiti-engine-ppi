package de.unipotsdam.hpi.thorben.ppi.measure.instance;

import java.util.List;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.util.ClockUtil;

import de.unipotsdam.hpi.thorben.ppi.condition.PPICondition;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ConditionEvent;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.InsertOrUpdateTimeInstanceCommand;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.SingleTimeMeasureValue;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.TimeMeasureInstance;
import de.unipotsdam.hpi.thorben.ppi.measure.query.TimeMeasureInstanceQuery;

public class TimeMeasure extends EventListeningBaseMeasure<TimeMeasureInstance> {


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
		TimeMeasureInstance timeMeasureValue;
		
		// TODO refactor
		// TODO add exception handling for cases in which the time measure was not modelled in a "sound" way, such that 
		// from and to condition would be triggered in reverse order or multiple times.
		if (fromCondition.isFulfilledBy(event)) {
			timeMeasureValue = new TimeMeasureInstance();
			String processInstanceId = event.getProcessInstanceId();
			timeMeasureValue.setMeasureId(id);
			timeMeasureValue.setProcessInstanceId(processInstanceId);
			
			SingleTimeMeasureValue singleValue = new SingleTimeMeasureValue();
			singleValue.setFrom(ClockUtil.getCurrentTime());
			
			CommandContext commandContext = Context.getCommandContext();			
			new InsertOrUpdateTimeInstanceCommand(timeMeasureValue, singleValue).execute(commandContext);
		}
		
		
		if (toCondition.isFulfilledBy(event)) {
			
			timeMeasureValue = new TimeMeasureInstance();
			String processInstanceId = event.getProcessInstanceId();
			timeMeasureValue.setMeasureId(id);
			timeMeasureValue.setProcessInstanceId(processInstanceId);
			
			SingleTimeMeasureValue singleValue = new SingleTimeMeasureValue();
			singleValue.setTo(ClockUtil.getCurrentTime());
			
			CommandContext commandContext = Context.getCommandContext();			
			new InsertOrUpdateTimeInstanceCommand(timeMeasureValue, singleValue).execute(commandContext);
		}
		
	}

	@Override
	public List<TimeMeasureInstance> getAllValues() {
		CommandContext context = Context.getCommandContext();		
		TimeMeasureInstanceQuery query = context.getBaseMeasureManager().createNewTimeMeasureInstanceQuery().measureId(id);
		return query.list();
	}	
}
