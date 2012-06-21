package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class InsertOrUpdateTimeValueCommand implements Command<Void> {

	private TimeMeasureValue timeMeasureValue;
	private SingleTimeMeasureValue singleValue;

	public InsertOrUpdateTimeValueCommand(TimeMeasureValue timeMeasureValue,
			SingleTimeMeasureValue singleValue) {
		this.timeMeasureValue = timeMeasureValue;
		this.singleValue = singleValue;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		TimeMeasureValue persistedValue = getPersistedTimeMeasureValue(commandContext);

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

	private TimeMeasureValue getPersistedTimeMeasureValue(
			CommandContext commandContext) {
		String measureId = timeMeasureValue.getMeasureId();
		String processInstanceId = timeMeasureValue.getProcessInstanceId();
		TimeMeasureValue persistedValue = commandContext
				.getBaseMeasureManager().findTimeMeasureValue(measureId,
						processInstanceId);
		if (persistedValue == null) {
			commandContext.getBaseMeasureManager().insertTimeMeasureValue(
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
			TimeMeasureValue persistedValue, CommandContext commandContext) {
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
