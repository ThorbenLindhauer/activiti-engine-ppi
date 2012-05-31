package de.unipotsdam.hpi.thorben.ppi;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class SysoutDelegate implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("Hello World!");
		
		// sleep between 1 and 5 seconds
		double sleepTime = (Math.random() * 5 * 1000) + 1;
		Thread.sleep((long)sleepTime);
	}

}
