package de.unipotsdam.hpi.thorben.ppi.measure;

import org.activiti.engine.impl.pvm.process.ActivityImpl;

public class TimeMeasure extends BaseMeasure {

	private ActivityImpl fromActivity;
	private ActivityImpl toActivity;
	
	public TimeMeasure(String id) {
		super(id);
	}
	
	@Override
	public void measure() {
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
