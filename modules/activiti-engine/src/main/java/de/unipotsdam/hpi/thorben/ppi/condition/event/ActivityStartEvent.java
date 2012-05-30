package de.unipotsdam.hpi.thorben.ppi.condition.event;

import org.activiti.engine.impl.pvm.process.ActivityImpl;

import de.unipotsdam.hpi.thorben.ppi.condition.ActivityStartCondition;

public class ActivityStartEvent extends ConditionEvent {

	private ActivityImpl activity;
	public ActivityStartEvent(ActivityImpl activity) {
		this.activity = activity;
	}
		
	public boolean fulfills(ActivityStartCondition condition) {
		if (activity == condition.getActivity()) {
			return true;
		} else {
			return false;
		}		
	}
}
