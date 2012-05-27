package de.unipotsdam.hpi.thorben.ppi;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.runtime.AtomicOperation;
import org.activiti.engine.impl.pvm.runtime.AtomicOperationActivityExecute;
import org.activiti.engine.impl.pvm.runtime.AtomicOperationTransitionDestroyScope;

import de.unipotsdam.hpi.thorben.ppi.measure.BaseMeasure;

public aspect ExecutionAspect {
	pointcut execute(AtomicOperationActivityExecute operation, ExecutionEntity execution) : target(operation) && call(void AtomicOperation+.execute(..)) && args(execution);
	
	before(AtomicOperationActivityExecute operation, ExecutionEntity execution) : execute(operation, execution) {
		ActivityImpl activity = (ActivityImpl) execution.getActivity();
		System.out.println("In execution " + execution.toString() + " Start of " + activity.toString() + " with behavior " + operation.toString());
		CommandContext commandContext = Context.getCommandContext();
		for (BaseMeasure measure : activity.getStartMeasures()) {
			measure.measure(execution, commandContext);
		}
	}
	
	pointcut finish(AtomicOperationTransitionDestroyScope operation, ExecutionEntity execution) : target(operation) && call(void AtomicOperation+.execute(..)) && args(execution);
	before(AtomicOperationTransitionDestroyScope operation, ExecutionEntity execution) : finish(operation, execution) {
		ActivityImpl activity = (ActivityImpl) execution.getActivity();
		System.out.println("In execution " + execution.toString() + " End of " + activity.toString() + " with behavior " + operation.toString());
		CommandContext commandContext = Context.getCommandContext();
		for (BaseMeasure measure : activity.getEndMeasures()) {
			measure.measure(execution, commandContext);
		}
	}
}
