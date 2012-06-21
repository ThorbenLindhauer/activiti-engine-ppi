package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class InsertOrUpdateTimeInstanceCommand implements Command<Void> {

	private TimeMeasureInstance timeMeasureValue;
	private SingleTimeMeasureValue singleValue;

	public InsertOrUpdateTimeInstanceCommand(TimeMeasureInstance timeMeasureValue,
			SingleTimeMeasureValue singleValue) {
		this.timeMeasureValue = timeMeasureValue;
		this.singleValue = singleValue;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		TimeMeasureInstance persistedValue = getPersistedTimeMeasureValue(commandContext);

		singleValue.setTimeMeasureId(persistedValue.getId());
		if (singleValue.getFrom() != null) {
			insertNewSingleTimeMeasureValue(commandContext);
		} else if (singleValue.getTo() != null) {
			updateExistingSingleTimeMeasureValue(persistedValue, commandContext);
		}

		// commit this stuff immediately
		commandContext.getDbSqlSession().flush();
		return null;
	}

	private TimeMeasureInstance getPersistedTimeMeasureValue(
			CommandContext commandContext) {
		String measureId = timeMeasureValue.getMeasureId();
		String processInstanceId = timeMeasureValue.getProcessInstanceId();
		TimeMeasureInstance persistedValue = commandContext
				.getBaseMeasureManager().findTimeMeasureInstance(measureId,
						processInstanceId);
		if (persistedValue == null) {
			commandContext.getBaseMeasureManager().insertTimeMeasureInstance(
					timeMeasureValue);
			persistedValue = timeMeasureValue;
		}

		return persistedValue;
	}

	private void insertNewSingleTimeMeasureValue(CommandContext commandContext) {
		commandContext.getBaseMeasureManager().insertSingleTimeMeasureValue(
				singleValue);
	}

	/**
	 * Updates the first single value that has no to-value yet, as we cannot
	 * distinguish between several parallel executions.
	 * 
	 * @param persistedValue
	 * @param commandContext
	 */
	private void updateExistingSingleTimeMeasureValue(
			TimeMeasureInstance persistedValue, CommandContext commandContext) {
		for (SingleTimeMeasureValue existingSingleValue : persistedValue
				.getSingleValues()) {
			if (existingSingleValue.getTo() == null) {
				SingleTimeMeasureValue persistedSingleValue = commandContext
						.getBaseMeasureManager().findSingleTimeMeasureValue(
								existingSingleValue.getId());
				persistedSingleValue.update(singleValue);
				break;
			}
		}
	}

}
