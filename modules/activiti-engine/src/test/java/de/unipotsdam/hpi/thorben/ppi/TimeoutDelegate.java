package de.unipotsdam.hpi.thorben.ppi;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class TimeoutDelegate implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {		
		// sleep between 0 and 5 seconds
		double sleepTime = (Math.random() * 5 * 1000) + 1;
		Thread.sleep((long)sleepTime);
	}

}
