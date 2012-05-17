package de.unipotsdam.hpi.thorben.ppi.proxy;

import org.activiti.engine.impl.pvm.runtime.AtomicOperation;
import org.activiti.engine.impl.pvm.runtime.AtomicOperationActivityStart;
import org.activiti.engine.impl.pvm.runtime.InterpretableExecution;

public class AtomicOperationActivityStartPPIProxy implements AtomicOperation {
	
	private AtomicOperationActivityStart wrappedOperation;
	public AtomicOperationActivityStartPPIProxy(AtomicOperationActivityStart startOperation) {
		wrappedOperation = startOperation;
	}

	@Override
	public void execute(InterpretableExecution execution) {
		wrappedOperation.execute(execution);
		
	}

	@Override
	public boolean isAsync(InterpretableExecution execution) {
		return wrappedOperation.isAsync(execution);
	}

}
