package de.unipotsdam.hpi.thorben.ppi;

import java.util.List;

import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;

public aspect ExecutionAspect {

	pointcut executes(ActivityBehavior activity) : target(activity) && call(void ActivityBehavior.execute(..));
	
	before(ActivityBehavior activity) : executes(activity) {
		System.out.println("Start of Activity" + activity.toString());
	}
	
	after(ActivityBehavior activity) : executes(activity) {
		System.out.println("End of Activity" + activity.toString());
	}
}
