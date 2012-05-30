package de.unipotsdam.hpi.thorben.ppi;

import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.runtime.AtomicOperation;
import org.activiti.engine.impl.pvm.runtime.AtomicOperationActivityExecute;
import org.activiti.engine.impl.pvm.runtime.AtomicOperationTransitionDestroyScope;

import de.unipotsdam.hpi.thorben.ppi.condition.event.ActivityEndEvent;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ActivityStartEvent;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ConditionEvent;

public aspect ExecutionAspect {
	pointcut execute(AtomicOperationActivityExecute operation, ExecutionEntity execution) : target(operation) && call(void AtomicOperation+.execute(..)) && args(execution);
	
	before(AtomicOperationActivityExecute operation, ExecutionEntity execution) : execute(operation, execution) {
		ActivityImpl activity = (ActivityImpl) execution.getActivity();
		System.out.println("In execution " + execution.toString() + " Start of " + activity.toString() + " with behavior " + operation.toString());
		
		ConditionEvent event = new ActivityStartEvent(activity);
		event.setProcessInstanceId(execution.getProcessInstanceId());

		activity.notifyObservers(event);
	}
	
	pointcut finish(AtomicOperationTransitionDestroyScope operation, ExecutionEntity execution) : target(operation) && call(void AtomicOperation+.execute(..)) && args(execution);
	before(AtomicOperationTransitionDestroyScope operation, ExecutionEntity execution) : finish(operation, execution) {
		ActivityImpl activity = (ActivityImpl) execution.getActivity();
		System.out.println("In execution " + execution.toString() + " End of " + activity.toString() + " with behavior " + operation.toString());

		ConditionEvent event = new ActivityEndEvent(activity);
		event.setProcessInstanceId(execution.getProcessInstanceId());
		activity.notifyObservers(event);
	}
}
