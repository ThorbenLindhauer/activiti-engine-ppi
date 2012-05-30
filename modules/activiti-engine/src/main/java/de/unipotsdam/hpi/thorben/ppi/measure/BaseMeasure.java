package de.unipotsdam.hpi.thorben.ppi.measure;

import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.pvm.runtime.InterpretableExecution;
import org.apache.log4j.Logger;

import de.unipotsdam.hpi.thorben.observer.Observer;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ConditionEvent;

public class BaseMeasure implements Observer {
	private Logger log = Logger.getLogger(BaseMeasure.class);
	protected String id;

	public BaseMeasure(String id) {
		this.id = id;
	}
	
	protected void measure(InterpretableExecution execution, CommandContext context) {
		log.info("executed measure");
	}

	@Override
	public void update(ConditionEvent event) {
		// do nothing here, override in subclasses that are interested in some kind of events.
	}

	
}
