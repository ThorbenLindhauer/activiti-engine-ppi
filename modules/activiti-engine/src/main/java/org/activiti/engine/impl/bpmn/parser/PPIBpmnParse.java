package org.activiti.engine.impl.bpmn.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.util.xml.Element;
import org.activiti.engine.repository.ProcessDefinition;

import de.unipotsdam.hpi.thorben.ppi.condition.ActivityEndCondition;
import de.unipotsdam.hpi.thorben.ppi.condition.ActivityStartCondition;
import de.unipotsdam.hpi.thorben.ppi.condition.PPICondition;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.BaseMeasure;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.CountMeasure;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.TimeMeasure;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.TimeMeasureValue;
import de.unipotsdam.hpi.thorben.ppi.measure.process.AggregatedMeasure;
import de.unipotsdam.hpi.thorben.ppi.measure.process.AggregationFunction;
import de.unipotsdam.hpi.thorben.ppi.measure.process.AverageFunction;
import de.unipotsdam.hpi.thorben.ppi.measure.process.LongHelper;
import de.unipotsdam.hpi.thorben.ppi.measure.process.TypeHelper;

public class PPIBpmnParse extends BpmnParse {

	protected Map<String, TimeMeasure> timeMeasures = new HashMap<String, TimeMeasure>();
	protected Map<String, CountMeasure> countMeasures = new HashMap<String, CountMeasure>();

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
		if (extentionsElement != null) {
			List<Element> ppiSetElements = extentionsElement.elementsNS(
					BpmnParser.PPI_BPMN_EXTENSIONS_NS, "ppiset");
			for (Element ppiSetElement : ppiSetElements) {
				parsePPISet(ppiSetElement, definition);
			}
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

		List<Element> appliesToConnectorElements = ppiSetElement.elementsNS(
				BpmnParser.PPI_BPMN_EXTENSIONS_NS, "appliesToConnector");
		for (Element appliesToConnector : appliesToConnectorElements) {
			parseAppliesToConnector(appliesToConnector, definition);
		}

		List<Element> ppiElements = ppiSetElement.elementsNS(
				BpmnParser.PPI_BPMN_EXTENSIONS_NS, "ppi");
		for (Element ppi : ppiElements) {
			parsePPI(ppi, definition);
		}
	}

	private void parseAggMeasure(Element aggMeasure,
			ProcessDefinition definition) {
		// TODO refactor heavily
		
		List<Element> baseMeasures = aggMeasure.elements();
		if (aggMeasure.elements().size() == 1) {
			parseBaseMeasure(baseMeasures.get(0), definition);
			String aggFunction = aggMeasure.attribute("aggregationfunction");
			String baseMeasureId = baseMeasures.get(0).attribute("id");
			String baseMeasureType = baseMeasures.get(0).getTagName();
			
			if (baseMeasureType.equals("timeMeasure")) {
				TimeMeasure baseMeasure = (TimeMeasure) lookUpBaseMeasure(baseMeasureId);
				
				if (aggFunction.equals("Average")) {
					TypeHelper<Long> longHelper = new LongHelper();
					AggregationFunction<Long, TimeMeasureValue> function = new AverageFunction<Long, TimeMeasureValue>(longHelper);
					AggregatedMeasure<TimeMeasure, TimeMeasureValue, Long> measure = new AggregatedMeasure<TimeMeasure, TimeMeasureValue, Long>();
					measure.setBaseMeasure(baseMeasure);
					measure.setAggregationFunction(function);
					
					ProcessDefinitionImpl definitionImpl = (ProcessDefinitionImpl) definition;
					definitionImpl.addMeasure(measure);
				}
			}			
			
		} else if (baseMeasures.size() > 1) {
			throw new ActivitiException("There should be no more than one child base measure.");
		} else if(baseMeasures.size() == 0) {
			throw new ActivitiException("Currently only agg measure supported with base measures as child elements.");
		}
	}

	private void parseBaseMeasure(Element baseMeasure,
			ProcessDefinition definition) {
		String tagName = baseMeasure.getTagName();
		if (tagName.equals("timeMeasure")) {
			parseTimeMeasure(baseMeasure, definition);
		} else if (tagName.equals("countMeasure")) {
			parseCountMeasure(baseMeasure, definition);
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

	private void parseCountMeasure(Element baseMeasure,
			ProcessDefinition definition) {
		String countMeasureId = baseMeasure.attribute("id");
		CountMeasure countMeasure = new CountMeasure(countMeasureId);
		countMeasures.put(countMeasureId, countMeasure);

	}

	private void parsePPI(Element ppi, ProcessDefinition definition) {
		// TODO Auto-generated method stub

	}

	private void parseAppliesToConnector(Element appliesToConnector,
			ProcessDefinition definition) {
		String sourceMeasureId = appliesToConnector.attribute("sourceRef");
		CountMeasure countMeasure = (CountMeasure) countMeasures
				.get(sourceMeasureId);

		ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity) definition;
		String targetActivityId = appliesToConnector.attribute("targetRef");
		ActivityImpl activity = findActivityById(
				definitionEntity.getActivities(), targetActivityId);

		PPICondition condition;
		String measurePoint = appliesToConnector.attribute("counatend");
		if (measurePoint.equals("Start")) {
			condition = new ActivityStartCondition(activity);
		} else if (measurePoint.equals("End")) {
			condition = new ActivityEndCondition(activity);
		} else {
			throw new ActivitiException(
					"Unknow content of countatend tag for time connector "
							+ appliesToConnector.attribute("id") + ": "
							+ measurePoint);
		}
		countMeasure.setCondition(condition);
		activity.addObserver(countMeasure);
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

	private BaseMeasure<?> lookUpBaseMeasure(String id) {
		BaseMeasure<?> measure = timeMeasures.get(id);
		if (measure == null) {
			measure = countMeasures.get(id);
		}

		return measure;
	}
}
