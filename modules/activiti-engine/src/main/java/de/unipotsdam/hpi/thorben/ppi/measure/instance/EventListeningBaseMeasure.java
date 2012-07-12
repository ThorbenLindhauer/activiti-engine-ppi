package de.unipotsdam.hpi.thorben.ppi.measure.instance;

import org.activiti.engine.repository.ProcessDefinition;

import de.unipotsdam.hpi.thorben.observer.Observer;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ConditionEvent;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureInstance;

public abstract class EventListeningBaseMeasure<B extends BaseMeasureInstance> extends BaseMeasure<B> implements Observer {

	public EventListeningBaseMeasure(String id, ProcessDefinition processDefinition) {
		super(id, processDefinition);
	}

	@Override
	public abstract void update(ConditionEvent event);
}
