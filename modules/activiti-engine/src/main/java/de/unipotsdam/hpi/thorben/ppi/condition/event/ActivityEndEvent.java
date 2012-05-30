package de.unipotsdam.hpi.thorben.ppi.condition.event;

import org.activiti.engine.impl.pvm.process.ActivityImpl;

import de.unipotsdam.hpi.thorben.ppi.condition.ActivityEndCondition;

public class ActivityEndEvent extends ConditionEvent {

	private ActivityImpl activity;
	public ActivityEndEvent(ActivityImpl activity) {
		this.activity = activity;
	}
		
	public boolean fulfills(ActivityEndCondition condition) {
		if (activity == condition.getActivity()) {
			return true;
		} else {
			return false;
		}		
	}
}
