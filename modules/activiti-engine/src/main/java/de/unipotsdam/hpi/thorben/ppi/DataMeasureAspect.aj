package de.unipotsdam.hpi.thorben.ppi;

import java.lang.reflect.Field;
import java.util.List;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.DataMeasure;

public aspect DataMeasureAspect {

	pointcut setVariable(ExecutionEntity execution, String variableName, Object value) : target(execution) && call(void *.setVariable(..)) && args(variableName, value);
	before(ExecutionEntity execution, String variableName, Object value) : setVariable(execution, variableName, value) {
		Object currentValue = execution.getVariable(variableName);
		List<DataMeasure> dataMeasuresForVariable = execution.getProcessDefinition()
				.getWatchedFieldsForVariable(variableName);
		if (dataMeasuresForVariable != null) {
			for (DataMeasure dataMeasure : dataMeasuresForVariable) {
				try {

					Class<?> dataObjectClass = value.getClass();
					Field fieldToTrace = dataObjectClass
							.getDeclaredField(dataMeasure.getDataFieldName());
					fieldToTrace.setAccessible(true);
					Object fieldValueAfter = fieldToTrace.get(value);					
					
					if (currentValue == null) {
						dataMeasure.updateDataValue(execution.getProcessInstanceId(), fieldValueAfter);
					} else {
						Object fieldValueBefore = fieldToTrace.get(currentValue);

						if (fieldValueBefore != fieldValueAfter) {
							dataMeasure.updateDataValue(execution.getProcessInstanceId(), fieldValueAfter);
						}						
					}
				} catch (Exception e) {
					throw new ActivitiException("Could not track data property", e);
				} 
			}
		}
	}
}
