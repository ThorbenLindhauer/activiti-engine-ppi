package de.unipotsdam.hpi.thorben.ppi.service;

import de.unipotsdam.hpi.thorben.ppi.measure.query.TimeMeasureValueQuery;

public interface PPIService {

	Number calculateAggregatedMeasure(String name, final String processDefinitionId);

	TimeMeasureValueQuery createTimeMeasureValueQuery();
}
