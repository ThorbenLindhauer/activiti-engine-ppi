package de.unipotsdam.hpi.thorben.ppi.service;

import de.unipotsdam.hpi.thorben.ppi.measure.query.TimeMeasureInstanceQuery;

public interface PPIService {

	Number calculateAggregatedMeasure(String name, final String processDefinitionId);
}
