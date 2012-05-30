package de.unipotsdam.hpi.thorben.observer;

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
public interface Observer {

	void update(ConditionEvent event);
}
