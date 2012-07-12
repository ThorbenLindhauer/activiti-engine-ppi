package de.unipotsdam.hpi.thorben.observer;

import de.unipotsdam.hpi.thorben.ppi.condition.event.ConditionEvent;

public class TestObserver implements Observer {
	
	private int timesNotified;
	
	public TestObserver() {
		timesNotified = 0;
	}

	public void update(ConditionEvent event) {
		timesNotified++;

	}
	
	public int getTimesNotified() {
		return timesNotified;
	}

}
