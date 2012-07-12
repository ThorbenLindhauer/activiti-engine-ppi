package de.unipotsdam.hpi.thorben.ppi.condition;

import org.activiti.engine.impl.pvm.process.ActivityImpl;

import de.unipotsdam.hpi.thorben.ppi.condition.event.ConditionEvent;

public class ActivityEndCondition implements PPICondition {

	private ActivityImpl activity;
	public ActivityEndCondition(ActivityImpl activity) {
		this.activity = activity;
	}
	
	public boolean isFulfilledBy(ConditionEvent event) {
		return event.fulfills(this);
	}
	
	public ActivityImpl getActivity() {
		return activity;
	}
}
