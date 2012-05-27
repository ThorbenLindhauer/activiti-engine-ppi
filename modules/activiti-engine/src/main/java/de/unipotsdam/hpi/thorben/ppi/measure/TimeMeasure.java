package de.unipotsdam.hpi.thorben.ppi.measure;

import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.runtime.InterpretableExecution;
import org.activiti.engine.impl.util.ClockUtil;
import org.activiti.engine.runtime.Execution;

import de.unipotsdam.hpi.thorben.ppi.measure.entity.InsertTimeValueCommand;
import de.unipotsdam.hpi.thorben.ppi.measure.entity.TimeMeasureValue;

public class TimeMeasure extends BaseMeasure {

	private ActivityImpl fromActivity;
	private ActivityImpl toActivity;
	
	public TimeMeasure(String id) {
		super(id);
	}
	
	@Override
	public void measure(InterpretableExecution execution, CommandContext context) {
		TimeMeasureValue timeMeasureValue = new TimeMeasureValue();
		String processInstanceId = execution.getProcessInstanceId();
		ActivityImpl activity = (ActivityImpl) execution.getActivity();
		if (activity.getId().equals(fromActivity.getId())) {
			timeMeasureValue.setFrom(ClockUtil.getCurrentTime());
		} else if (activity.getId().equals(toActivity.getId())){
			timeMeasureValue.setTo(ClockUtil.getCurrentTime());
		}
		timeMeasureValue.setMeasureId(id);
		timeMeasureValue.setProcessInstanceId(processInstanceId);
		new InsertTimeValueCommand(timeMeasureValue).execute(context);
		System.out.println("executed measure");
	}
	
	public void setFromActivity(ActivityImpl activity) {
		this.fromActivity = activity;
	}
	
	public ActivityImpl getFromActivity() {
		return fromActivity;
	}

	public void setToActivity(ActivityImpl activity) {
		this.toActivity = activity;
	}

	public ActivityImpl getToActivity() {
		return toActivity;
	}
}
