package de.unipotsdam.hpi.thorben.ppi.condition;

import de.unipotsdam.hpi.thorben.ppi.condition.event.ConditionEvent;

public interface PPICondition {

	boolean isFulfilledBy(ConditionEvent event);
}
