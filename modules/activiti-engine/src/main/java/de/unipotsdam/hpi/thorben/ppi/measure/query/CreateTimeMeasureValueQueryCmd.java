package de.unipotsdam.hpi.thorben.ppi.measure.query;

import java.io.Serializable;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class CreateTimeMeasureValueQueryCmd implements Command<TimeMeasureValueQuery>, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public TimeMeasureValueQuery execute(CommandContext commandContext) {
		return commandContext
			      .getBaseMeasureManager()
			      .createNewTimeMeasureValueQuery();
	}

}
