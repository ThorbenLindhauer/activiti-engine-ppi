package de.unipotsdam.hpi.thorben.ppi.condition.event;

import de.unipotsdam.hpi.thorben.ppi.condition.ActivityEndCondition;
import de.unipotsdam.hpi.thorben.ppi.condition.ActivityStartCondition;
import de.unipotsdam.hpi.thorben.ppi.condition.PPICondition;

public abstract class ConditionEvent {

	public boolean fulfills(PPICondition condition) {
		return false;
	}
	
	public boolean fulfills(ActivityStartCondition condition) {
		return false;	
	}
	
	public boolean fulfills(ActivityEndCondition condition) {
		return false;	
	}
}
