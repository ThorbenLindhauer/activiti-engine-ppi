package de.unipotsdam.hpi.thorben.observer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.activiti.engine.impl.pvm.process.ActivityImpl;

import de.unipotsdam.hpi.thorben.ppi.condition.event.ConditionEvent;

/**
 * We need our own Observer pattern implementation as the {@link java.lang.Observable} implementation puts state in the Observable
 * that will change during process runtime (i.e. the changed-flag). As we want to observe e.g. {@link ActivityImpl} objects,
 * this could lead to concurrent modifications by several executions, e.g. from different process instances, which could
 * ultimately lead to undesired Observer-behavior.
 * @author Thorben
 *
 */
public abstract class Observable {
	
	private Set<Observer> observers;
	
	public Observable() {
		observers = new HashSet<Observer>();
	}
	
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	public void notifyObservers(ConditionEvent event) {
		for (Observer observer : observers) {
			observer.update(event);
		}
	}
	
	public List<Observer> getObservers() {		
		return new ArrayList<Observer>(observers);
	}
}
