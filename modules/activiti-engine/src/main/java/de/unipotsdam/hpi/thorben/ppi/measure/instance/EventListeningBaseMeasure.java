package de.unipotsdam.hpi.thorben.ppi.measure.instance;

import de.unipotsdam.hpi.thorben.observer.Observer;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ConditionEvent;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureInstance;

public abstract class EventListeningBaseMeasure<B extends BaseMeasureInstance> extends BaseMeasure<B> implements Observer {

	public EventListeningBaseMeasure(String id) {
		super(id);
	}

	@Override
	public abstract void update(ConditionEvent event);
}
