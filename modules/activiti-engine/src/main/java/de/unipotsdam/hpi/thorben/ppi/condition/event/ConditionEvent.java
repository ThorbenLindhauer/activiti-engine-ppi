package de.unipotsdam.hpi.thorben.ppi.condition.event;

import de.unipotsdam.hpi.thorben.ppi.condition.ActivityEndCondition;
import de.unipotsdam.hpi.thorben.ppi.condition.ActivityStartCondition;
import de.unipotsdam.hpi.thorben.ppi.condition.PPICondition;

/**
 * Corresponds to Time Instant Conditions in the PPI ontology.
 * @author Thorben
 *
 */
public abstract class ConditionEvent {

	private String processInstanceId;
	
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	/*
	 *  pseudo double dispatch methods, to be overriden in subclasses
	 */
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
