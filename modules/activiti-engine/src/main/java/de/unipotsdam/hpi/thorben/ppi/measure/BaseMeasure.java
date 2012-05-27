package de.unipotsdam.hpi.thorben.ppi.measure;

import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.pvm.runtime.InterpretableExecution;
import org.apache.log4j.Logger;

public class BaseMeasure {
	private Logger log = Logger.getLogger(BaseMeasure.class);
	protected String id;

	public BaseMeasure(String id) {
		this.id = id;
	}
	
	public void measure(InterpretableExecution execution, CommandContext context) {
		log.info("executed measure");
	}
}
