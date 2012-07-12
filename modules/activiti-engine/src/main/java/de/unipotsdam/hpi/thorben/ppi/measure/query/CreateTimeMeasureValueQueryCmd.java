package de.unipotsdam.hpi.thorben.ppi.measure.query;

import java.io.Serializable;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class CreateTimeMeasureValueQueryCmd implements Command<TimeMeasureInstanceQuery>, Serializable {

	private static final long serialVersionUID = 1L;

	public TimeMeasureInstanceQuery execute(CommandContext commandContext) {
		return commandContext
			      .getBaseMeasureManager()
			      .createNewTimeMeasureInstanceQuery();
	}

}
