package de.unipotsdam.hpi.thorben.ppi.proxy;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.runtime.AtomicOperation;
import org.activiti.engine.impl.pvm.runtime.AtomicOperationActivityEnd;
import org.activiti.engine.impl.pvm.runtime.InterpretableExecution;

import de.unipotsdam.hpi.thorben.ppi.measure.BaseMeasure;

public class AtomicOperationActivityEndPPIProxy implements AtomicOperation {
	
	private AtomicOperationActivityEnd wrappedOperation;
	public AtomicOperationActivityEndPPIProxy(AtomicOperationActivityEnd startOperation) {
		wrappedOperation = startOperation;
	}

	@Override
	public void execute(InterpretableExecution execution) {
		CommandContext commandContext = Context.getCommandContext();
		ActivityImpl activity = (ActivityImpl) execution.getActivity();
		for (BaseMeasure measure : activity.getEndMeasures()) {
			measure.measure(execution, commandContext);
		}
		wrappedOperation.execute(execution);
		
	}

	@Override
	public boolean isAsync(InterpretableExecution execution) {
		return wrappedOperation.isAsync(execution);
	}

}
