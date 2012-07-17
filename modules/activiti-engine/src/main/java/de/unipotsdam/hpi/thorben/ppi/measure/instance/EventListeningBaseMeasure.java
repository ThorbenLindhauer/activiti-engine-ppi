package de.unipotsdam.hpi.thorben.ppi.measure.instance;

import org.activiti.engine.repository.ProcessDefinition;

import de.unipotsdam.hpi.thorben.observer.Observer;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ConditionEvent;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureInstance;

/**
 * Base Measures that take action triggered by Time Instant Conditions subclass
 * from this.
 * 
 * @author Thorben
 * 
 * @param <B>
 */
public abstract class EventListeningBaseMeasure<B extends BaseMeasureInstance>
		extends BaseMeasure<B> implements Observer {

	public EventListeningBaseMeasure(String id,
			ProcessDefinition processDefinition) {
		super(id, processDefinition);
	}

	public abstract void update(ConditionEvent event);
}
