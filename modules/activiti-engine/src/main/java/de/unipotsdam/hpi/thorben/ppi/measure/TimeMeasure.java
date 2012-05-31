package de.unipotsdam.hpi.thorben.ppi.measure;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.util.ClockUtil;

import de.unipotsdam.hpi.thorben.ppi.condition.PPICondition;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ConditionEvent;
import de.unipotsdam.hpi.thorben.ppi.measure.entity.InsertTimeValueCommand;
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
		if (fromCondition.isFulfilledBy(event)) {
			timeMeasureValue = new TimeMeasureValue();
			String processInstanceId = event.getProcessInstanceId();
			timeMeasureValue.setFrom(ClockUtil.getCurrentTime());
			timeMeasureValue.setMeasureId(id);
			timeMeasureValue.setProcessInstanceId(processInstanceId);
			CommandContext commandContext = Context.getCommandContext();
			
			new InsertTimeValueCommand(timeMeasureValue).execute(commandContext);
			commandContext.getDbSqlSession().flush();
		}
		
		
		if (toCondition.isFulfilledBy(event)) {
			
			timeMeasureValue = new TimeMeasureValue();
			String processInstanceId = event.getProcessInstanceId();
			
			
			TimeMeasureValueQuery query = new TimeMeasureValueQueryImpl();
			TimeMeasureValue value = query.measureId(id).processInstanceId(processInstanceId).singleResult();			
			
//			timeMeasureValue.setTo(ClockUtil.getCurrentTime());
//			timeMeasureValue.setMeasureId(id);
//			timeMeasureValue.setProcessInstanceId(processInstanceId);
			
			value.setTo(ClockUtil.getCurrentTime());
//			
//			CommandContext commandContext = Context.getCommandContext();
//			new InsertTimeValueCommand(timeMeasureValue).execute(commandContext);
		}
		
	}	
}
