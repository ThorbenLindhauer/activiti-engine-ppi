package de.unipotsdam.hpi.thorben.ppi.measure;

import java.util.Observable;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.util.ClockUtil;

import de.unipotsdam.hpi.thorben.ppi.condition.PPICondition;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ConditionEvent;
import de.unipotsdam.hpi.thorben.ppi.measure.entity.InsertTimeValueCommand;
import de.unipotsdam.hpi.thorben.ppi.measure.entity.TimeMeasureValue;

public class TimeMeasure extends BaseMeasure {


	private PPICondition fromCondition;
	private PPICondition toCondition;
	
	public TimeMeasure(String id) {
		super(id);
	}
	
	

//	@Override
//	public void measure(InterpretableExecution execution, CommandContext context) {
//		TimeMeasureValue timeMeasureValue = new TimeMeasureValue();
//		String processInstanceId = execution.getProcessInstanceId();
//		ActivityImpl activity = (ActivityImpl) execution.getActivity();
//		
//		// TODO das ist nicht korrekt, da from und toActivity gleich sein können
////		rot hier, da hier weiter arbeiten
//		if (activity.getId().equals(fromActivity.getId())) {
//			timeMeasureValue.setFrom(ClockUtil.getCurrentTime());
//		} else if (activity.getId().equals(toActivity.getId())) {
//			timeMeasureValue.setTo(ClockUtil.getCurrentTime());
//			return;
//		}
//		timeMeasureValue.setMeasureId(id);
//		timeMeasureValue.setProcessInstanceId(processInstanceId);
//		new InsertTimeValueCommand(timeMeasureValue).execute(context);
//		System.out.println("executed measure");
//	}
	
	public void setFromCondition(PPICondition fromCondition) {
		this.fromCondition = fromCondition;
	}
	
	public void setToCondition(PPICondition toCondition) {
		this.toCondition = toCondition;
	}

	@Override
	public void update(Observable processElement, Object eventObject) {
		ConditionEvent event = (ConditionEvent)eventObject;
		TimeMeasureValue timeMeasureValue;
		if (fromCondition.isFulfilledBy(event)) {
			timeMeasureValue = new TimeMeasureValue();
			String processInstanceId = event.getProcessInstanceId();
			timeMeasureValue.setFrom(ClockUtil.getCurrentTime());
			timeMeasureValue.setMeasureId(id);
			timeMeasureValue.setProcessInstanceId(processInstanceId);
			CommandContext commandContext = Context.getCommandContext();
			new InsertTimeValueCommand(timeMeasureValue).execute(commandContext);
		}
		
		
		if (toCondition.isFulfilledBy(event)) {
			timeMeasureValue = new TimeMeasureValue();
			String processInstanceId = event.getProcessInstanceId();
			timeMeasureValue.setTo(ClockUtil.getCurrentTime());
			timeMeasureValue.setMeasureId(id);
			timeMeasureValue.setProcessInstanceId(processInstanceId);
			CommandContext commandContext = Context.getCommandContext();
			new InsertTimeValueCommand(timeMeasureValue).execute(commandContext);
		}
		
	}	
}
