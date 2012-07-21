package de.unipotsdam.hpi.thorben.ppi.aspects;

import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.runtime.AtomicOperation;
import org.activiti.engine.impl.pvm.runtime.AtomicOperationActivityExecute;
import org.activiti.engine.impl.pvm.runtime.AtomicOperationTransitionDestroyScope;

import de.unipotsdam.hpi.thorben.ppi.condition.event.ActivityEndEvent;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ActivityStartEvent;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ConditionEvent;

/**
 * Defines all points of emitting time instant condition events.
 * @author Thorben
 *
 */
public aspect TimeInstantConditionAspect {
	/**
	 * Captures the ActivityStartCondition
	 * @param operation
	 * @param execution
	 */
	pointcut execute(AtomicOperationActivityExecute operation,
			ExecutionEntity execution) : target(operation) && call(void AtomicOperation+.execute(..)) && args(execution);
	
	/**
	 * Signals the ActivityStartCondition
	 * @param operation
	 * @param execution
	 */
	before(AtomicOperationActivityExecute operation, ExecutionEntity execution) : execute(operation, execution) {
		ActivityImpl activity = (ActivityImpl) execution.getActivity();
		
		ConditionEvent event = new ActivityStartEvent(activity);
		event.setProcessInstanceId(execution.getProcessInstanceId());

		activity.notifyObservers(event);
	}
	
	/**
	 * Captures the ActivityEndCondition
	 * @param operation
	 * @param execution
	 */
	pointcut finish(AtomicOperationTransitionDestroyScope operation, ExecutionEntity execution) : target(operation) && call(void AtomicOperation+.execute(..)) && args(execution);
	
	/**
	 * Signals the ActivityEndCondition
	 * @param operation
	 * @param execution
	 */
	before(AtomicOperationTransitionDestroyScope operation, ExecutionEntity execution) : finish(operation, execution) {
		ActivityImpl activity = (ActivityImpl) execution.getActivity();

		ConditionEvent event = new ActivityEndEvent(activity);
		event.setProcessInstanceId(execution.getProcessInstanceId());
		activity.notifyObservers(event);
	}
	
	
}
