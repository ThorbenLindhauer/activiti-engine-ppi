package de.unipotsdam.hpi.thorben.ppi.measure;

import java.util.Observable;
import java.util.Observer;

import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.pvm.runtime.InterpretableExecution;
import org.apache.log4j.Logger;

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
	public void update(Observable processElement, Object event) {
		// do nothing here
	}
}
