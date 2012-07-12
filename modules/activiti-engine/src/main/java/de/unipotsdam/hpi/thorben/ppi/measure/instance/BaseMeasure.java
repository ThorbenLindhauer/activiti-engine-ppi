package de.unipotsdam.hpi.thorben.ppi.measure.instance;

import java.util.List;

import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.pvm.runtime.InterpretableExecution;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.log4j.Logger;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureInstance;

public abstract class BaseMeasure<B extends BaseMeasureInstance> {
	private Logger log = Logger.getLogger(BaseMeasure.class);
	protected String id;
	protected ProcessDefinition processDefinition;

	public BaseMeasure(String id, ProcessDefinition processDefinition) {
		this.id = id;
		this.processDefinition = processDefinition;
	}
	
	protected void measure(InterpretableExecution execution, CommandContext context) {
		log.info("executed measure");
	}

	
	public abstract List<B> getAllValues();

	
}
