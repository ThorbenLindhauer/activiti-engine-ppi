package de.unipotsdam.hpi.thorben.ppi.measure.instance;

import java.util.List;

import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.pvm.runtime.InterpretableExecution;
import org.apache.log4j.Logger;

import de.unipotsdam.hpi.thorben.observer.Observer;
import de.unipotsdam.hpi.thorben.ppi.condition.event.ConditionEvent;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureValue;

public abstract class BaseMeasure<B extends BaseMeasureValue> implements Observer {
	private Logger log = Logger.getLogger(BaseMeasure.class);
	protected String id;

	public BaseMeasure(String id) {
		this.id = id;
	}
	
	protected void measure(InterpretableExecution execution, CommandContext context) {
		log.info("executed measure");
	}

	@Override
	public abstract void update(ConditionEvent event);
	
	public abstract List<B> getAllValues();

	
}
