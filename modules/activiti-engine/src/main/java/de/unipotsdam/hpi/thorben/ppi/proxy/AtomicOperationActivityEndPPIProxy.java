package de.unipotsdam.hpi.thorben.ppi.proxy;

import org.activiti.engine.impl.pvm.runtime.AtomicOperation;
import org.activiti.engine.impl.pvm.runtime.AtomicOperationActivityEnd;
import org.activiti.engine.impl.pvm.runtime.InterpretableExecution;

public class AtomicOperationActivityEndPPIProxy implements AtomicOperation {
	
	private AtomicOperationActivityEnd wrappedOperation;
	public AtomicOperationActivityEndPPIProxy(AtomicOperationActivityEnd startOperation) {
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
