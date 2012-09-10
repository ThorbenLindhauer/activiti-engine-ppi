/* Copyright 2012 Thorben Lindhauer
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
