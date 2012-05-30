package org.activiti.engine.impl.bpmn.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.util.xml.Element;
import org.activiti.engine.repository.ProcessDefinition;

import de.unipotsdam.hpi.thorben.ppi.condition.ActivityEndCondition;
import de.unipotsdam.hpi.thorben.ppi.condition.ActivityStartCondition;
import de.unipotsdam.hpi.thorben.ppi.condition.PPICondition;
import de.unipotsdam.hpi.thorben.ppi.measure.TimeMeasure;

public class PPIBpmnParse extends BpmnParse {

	protected Map<String, TimeMeasure> timeMeasures = new HashMap<String, TimeMeasure>();

	PPIBpmnParse(BpmnParser parser) {
		super(parser);
	}

	@Override
	protected void parseProcessDefinitionCustomExtensions(Element scopeElement,
			ProcessDefinition definition) {
		super.parseProcessDefinitionCustomExtensions(scopeElement, definition);
		parsePPIElements(scopeElement, definition);
	}

	protected void parsePPIElements(Element scopeElement,
			ProcessDefinition definition) {
		Element extentionsElement = scopeElement.element("extensionElements");
		List<Element> ppiSetElements = extentionsElement.elementsNS(
				BpmnParser.PPI_BPMN_EXTENSIONS_NS, "ppiset");
		for (Element ppiSetElement : ppiSetElements) {
			parsePPISet(ppiSetElement, definition);
		}
	}

	protected void parsePPISet(Element ppiSetElement,
			ProcessDefinition definition) {
		// TODO: parse all necessary elements, as are: aggregatedMeasure,
		// TimeConnector, ppi (in the first example)
		List<Element> aggMeasureElements = ppiSetElement.elementsNS(
				BpmnParser.PPI_BPMN_EXTENSIONS_NS, "aggregatedMeasure");
		for (Element aggMeasure : aggMeasureElements) {
			parseAggMeasure(aggMeasure, definition);
		}

		List<Element> timeConnectorElements = ppiSetElement.elementsNS(
				BpmnParser.PPI_BPMN_EXTENSIONS_NS, "TimeConnector");
		for (Element timeConnector : timeConnectorElements) {
			parseTimeConnector(timeConnector, definition);
		}

		List<Element> ppiElements = ppiSetElement.elementsNS(
				BpmnParser.PPI_BPMN_EXTENSIONS_NS, "ppi");
		for (Element ppi : ppiElements) {
			parsePPI(ppi, definition);
		}
	}

	private void parseAggMeasure(Element aggMeasure,
			ProcessDefinition definition) {
		List<Element> baseMeasures = aggMeasure.elements();
		for (Element baseMeasure : baseMeasures) {
			parseBaseMeasure(baseMeasure, definition);
		}

	}

	private void parseBaseMeasure(Element baseMeasure,
			ProcessDefinition definition) {
		if (baseMeasure.getTagName().equals("timeMeasure")) {
			parseTimeMeasure(baseMeasure, definition);
		} else {
			throw new ActivitiException("Unsupported base measure type "
					+ baseMeasure.getTagName());
		}

	}

	private void parseTimeMeasure(Element baseMeasure,
			ProcessDefinition definition) {
		String timeMeasureId = baseMeasure.attribute("id");
		TimeMeasure timeMeasure = new TimeMeasure(timeMeasureId);
		timeMeasures.put(timeMeasureId, timeMeasure);
	}

	private void parsePPI(Element ppi, ProcessDefinition definition) {
		// TODO Auto-generated method stub

	}

	private void parseTimeConnector(Element timeConnector,
			ProcessDefinition definition) {

		String sourceMeasureId = timeConnector.attribute("sourceRef");
		TimeMeasure timeMeasure = (TimeMeasure) timeMeasures
				.get(sourceMeasureId);

		ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity) definition;
		String targetActivityId = timeConnector.attribute("targetRef");
		ActivityImpl activity = findActivityById(
				definitionEntity.getActivities(), targetActivityId);

		String conditionType = timeConnector.attribute("conditiontype");

		PPICondition condition;
		String measurePoint = timeConnector.attribute("counatend");
		if (measurePoint.equals("Start")) {
			condition = new ActivityStartCondition(activity);
		} else if (measurePoint.equals("End")) {
			condition = new ActivityEndCondition(activity);
		} else {
			throw new ActivitiException(
					"Unknow content of countatend tag for time connector "
							+ timeConnector.attribute("id") + ": "
							+ measurePoint);
		}
		
		if (conditionType.equals("From")) {
			timeMeasure.setFromCondition(condition);
		} else if (conditionType.equals("To")) {
			timeMeasure.setToCondition(condition);
		} else {
			throw new ActivitiException(
					"Unknow content of condition type tag for time connector "
							+ timeConnector.attribute("id") + ": "
							+ conditionType);
		}
		
		activity.addObserver(timeMeasure);

	}

	private ActivityImpl findActivityById(List<ActivityImpl> activities,
			String id) {
		for (ActivityImpl activity : activities) {
			if (activity.getId().equals(id)) {
				return activity;
			}
		}
		throw new ActivitiException("Could not find Activity with id " + id);
	}
}
