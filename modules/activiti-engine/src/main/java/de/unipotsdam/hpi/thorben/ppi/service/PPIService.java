package de.unipotsdam.hpi.thorben.ppi.service;


public interface PPIService {

	Number calculateAggregatedMeasure(String id, final String processDefinitionId);
	
	Number calculatePPI(String id, final String processDefinitionId);
	
	boolean PPIfulfilled(String id, final String processDefinitionId);
}
