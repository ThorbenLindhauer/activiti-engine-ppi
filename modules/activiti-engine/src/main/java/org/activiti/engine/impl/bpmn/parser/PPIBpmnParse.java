package org.activiti.engine.impl.bpmn.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.ScopeImpl;
import org.activiti.engine.impl.util.xml.Element;
import org.activiti.engine.repository.ProcessDefinition;

import de.unipotsdam.hpi.thorben.ppi.condition.ActivityEndCondition;
import de.unipotsdam.hpi.thorben.ppi.condition.ActivityStartCondition;
import de.unipotsdam.hpi.thorben.ppi.condition.PPICondition;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.CountMeasure;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.DataMeasure;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.TimeMeasure;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureInstance;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.CountMeasureInstance;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.DataMeasureInstance;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.TimeMeasureInstance;
import de.unipotsdam.hpi.thorben.ppi.measure.process.AggregatedMeasure;
import de.unipotsdam.hpi.thorben.ppi.measure.process.AggregationFunction;
import de.unipotsdam.hpi.thorben.ppi.measure.process.AverageFunction;
import de.unipotsdam.hpi.thorben.ppi.measure.process.DerivedProcessMeasure;
import de.unipotsdam.hpi.thorben.ppi.measure.process.DoubleHelper;
import de.unipotsdam.hpi.thorben.ppi.measure.process.GreaterThanFunction;
import de.unipotsdam.hpi.thorben.ppi.measure.process.IntegerHelper;
import de.unipotsdam.hpi.thorben.ppi.measure.process.LongHelper;
import de.unipotsdam.hpi.thorben.ppi.measure.process.LowerThanFunction;
import de.unipotsdam.hpi.thorben.ppi.measure.process.MaximumFunction;
import de.unipotsdam.hpi.thorben.ppi.measure.process.MinimumFunction;
import de.unipotsdam.hpi.thorben.ppi.measure.process.PPI;
import de.unipotsdam.hpi.thorben.ppi.measure.process.ProcessMeasure;
import de.unipotsdam.hpi.thorben.ppi.measure.process.SumFunction;
import de.unipotsdam.hpi.thorben.ppi.measure.process.TargetFunction;
import de.unipotsdam.hpi.thorben.ppi.measure.process.TypeHelper;

public class PPIBpmnParse extends BpmnParse {

	protected Map<String, TimeMeasure> timeMeasures = new HashMap<String, TimeMeasure>();
	protected Map<String, CountMeasure> countMeasures = new HashMap<String, CountMeasure>();
	protected Map<String, DataMeasure> dataMeasures = new HashMap<String, DataMeasure>();
	protected Map<String, ProcessMeasure<?>> derivableProcessMeasures = new HashMap<String, ProcessMeasure<?>>();
	protected Map<String, DerivedProcessMeasure> derivingMeasures = new HashMap<String, DerivedProcessMeasure>();
	
	protected Map<String, List<Element>> ppisForProcessMeasure = new HashMap<String, List<Element>>();
	
	// DataObjects: Id -> Name
	protected Map<String, String> dataObjects = new HashMap<String, String>();

	PPIBpmnParse(BpmnParser parser) {
		super(parser);
	}

	@Override
	protected void parseProcessDefinitionCustomExtensions(Element scopeElement,
			ProcessDefinition definition) {
		super.parseProcessDefinitionCustomExtensions(scopeElement, definition);
		parsePPIElements(scopeElement, definition);
	}
	
	@Override
	public void parseScope(Element scopeElement, ScopeImpl parentScope) {
		// we have to parse the data objects first, otherwise they are not available when parsing the ppi elements
		parseDataObjects(scopeElement, parentScope);
	    super.parseScope(scopeElement, parentScope);
	   
	    
	  }

	private void parseDataObjects(Element scopeElement, ScopeImpl parentScope) {
		List<Element> dataObjects = scopeElement.elements("dataObject");
		for (Element dataObject : dataObjects) {
			parseDataObject(dataObject);
		}
		
	}

	private void parseDataObject(Element dataObject) {
		String dataObjectId = dataObject.attribute("id");
		String dataObjectName = dataObject.attribute("name");
		dataObjects.put(dataObjectId, dataObjectName);
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

		List<Element> ppiElements = ppiSetElement.elementsNS(
				BpmnParser.PPI_BPMN_EXTENSIONS_NS, "ppi");
		for (Element ppi : ppiElements) {
			registerPPIElementForProcessMeasure(ppi, definition);
		}
		
		List<Element> aggMeasureElements = ppiSetElement.elementsNS(
				BpmnParser.PPI_BPMN_EXTENSIONS_NS, "aggregatedMeasure");
		for (Element aggMeasure : aggMeasureElements) {
			parseAggMeasure(aggMeasure, definition);
		}
		
		List<Element> derivedMeasureElements = ppiSetElement.elementsNS(
				BpmnParser.PPI_BPMN_EXTENSIONS_NS, "derivedProcessMeasure");
		for (Element derivedMeasure : derivedMeasureElements) {
			parseDerivedMeasure(derivedMeasure, definition);
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
		
		List<Element> usesConnectorElements = ppiSetElement.elementsNS(
				BpmnParser.PPI_BPMN_EXTENSIONS_NS, "uses");
		for (Element usesConnector : usesConnectorElements) {
			parseUsesConnector(usesConnector, definition);
		}

	}


	private void parseDerivedMeasure(Element derivedMeasure,
			ProcessDefinition definition) {
		String measureId = derivedMeasure.attribute("id");
		String juelFunction = derivedMeasure.attribute("function");
		
		DerivedProcessMeasure measure = new DerivedProcessMeasure(measureId);	
		measure.setFunction(juelFunction);
		ProcessDefinitionImpl definitionImpl = (ProcessDefinitionImpl) definition;
		
		definitionImpl.addProcessMeasure(measure);
		derivingMeasures.put(measureId, measure);
		
		List<Element> ppisToRegisterFor = ppisForProcessMeasure.get(measureId);
		if (ppisToRegisterFor != null) {
			for (Element ppiElement : ppisToRegisterFor) {
				parsePPI(ppiElement, new DoubleHelper(), measure, definition);
			}
		}
	}

	private void parseUsesConnector(Element usesConnector,
			ProcessDefinition definition) {
		String sourceMeasureId = usesConnector.attribute("sourceRef");
		DerivedProcessMeasure derivedMeasure = derivingMeasures.get(sourceMeasureId);
		
		String targetMeasureId = usesConnector.attribute("targetRef");
		ProcessMeasure<?> measure = derivableProcessMeasures.get(targetMeasureId);
		
		String variableName = usesConnector.attribute("variable");
		derivedMeasure.addDerivableMeasure(variableName, measure);
	}

	private void parseAggMeasure(Element aggMeasure,
			ProcessDefinition definition) {
		List<Element> baseMeasures = aggMeasure.elements();
		if (aggMeasure.elements().size() == 1) {
			parseBaseMeasure(aggMeasure, baseMeasures.get(0), definition);
		} else if (baseMeasures.size() > 1) {
			throw new ActivitiException(
					"There should be no more than one child base measure.");
		} else if (baseMeasures.size() == 0) {
			throw new ActivitiException(
					"Currently only agg measure supported with base measures as child elements.");
		}
	}

	private void parseBaseMeasure(Element aggregatedMeasure,
			Element baseMeasure, ProcessDefinition definition) {
		if (aggregatedMeasure == null) {
			throw new ActivitiException(
					"Base Measure has no owning aggregated measure.");
		}

		String tagName = baseMeasure.getTagName();
		if (tagName.equals("timeMeasure")) {
			parseTimeMeasure(aggregatedMeasure, baseMeasure, definition);
		} else if (tagName.equals("countMeasure")) {
			parseCountMeasure(aggregatedMeasure, baseMeasure, definition);
		} else if (tagName.equals("dataMeasure")) {
			parseDataMeasure(aggregatedMeasure, baseMeasure, definition);
		}
		else {
			throw new ActivitiException("Unsupported base measure type "
					+ baseMeasure.getTagName());
		}

	}


	private <N extends Number, T extends BaseMeasureInstance> AggregationFunction<N, T> parseAggregationFunction(
			String aggregationFunctionName, TypeHelper<N> typeHelper) {
		if (aggregationFunctionName.equals("Average")) {
			return new AverageFunction<N, T>(typeHelper);
		} else if (aggregationFunctionName.equals("Sum")) {
			return new SumFunction<N, T>(typeHelper);
		} else if (aggregationFunctionName.equals("Maximum")) {
			return new MaximumFunction<N, T>(typeHelper);
		} else if (aggregationFunctionName.equals("Minimum")) {
			return new MinimumFunction<N, T>(typeHelper);
		} else {
			throw new ActivitiException("Unsupported aggregation function type "
					+ aggregationFunctionName);
		}
		
	}
	

	private void parseDataMeasure(Element aggregatedMeasure,
			Element baseMeasure, ProcessDefinition definition) {
		
		// parse double measure
		String dataMeasureId = baseMeasure.attribute("id");
		String dataPropertyToTrack = baseMeasure.attribute("property");
		DataMeasure dataMeasure = new DataMeasure(dataMeasureId, definition);
		dataMeasure.setDataFieldName(dataPropertyToTrack);
		dataMeasures.put(dataMeasureId, dataMeasure);
		
		// parse aggregated measure
		String aggFunction = aggregatedMeasure.attribute("aggregationfunction");
		TypeHelper<Double> doubleHelper = new DoubleHelper();
		AggregationFunction<Double, DataMeasureInstance> function = parseAggregationFunction(
				aggFunction, doubleHelper);
		AggregatedMeasure<DataMeasure, DataMeasureInstance, Double> measure = new AggregatedMeasure<DataMeasure, DataMeasureInstance, Double>();
		
		String aggMeasureId = aggregatedMeasure.attribute("id");
		measure.setId(aggMeasureId);
		measure.setBaseMeasure(dataMeasure);
		measure.setAggregationFunction(function);
		
		ProcessDefinitionImpl definitionImpl = (ProcessDefinitionImpl) definition;
		definitionImpl.addProcessMeasure(measure);
		derivableProcessMeasures.put(aggMeasureId, measure);
		
		List<Element> ppisToRegisterFor = ppisForProcessMeasure.get(aggMeasureId);
		if (ppisToRegisterFor != null) {
			for (Element ppiElement : ppisToRegisterFor) {
				parsePPI(ppiElement, doubleHelper, measure, definition);
			}
		}
	}

	private void parseTimeMeasure(Element aggregatedMeasure,
			Element baseMeasure, ProcessDefinition definition) {

		// parse time measure
		String timeMeasureId = baseMeasure.attribute("id");
		TimeMeasure timeMeasure = new TimeMeasure(timeMeasureId, definition);
		timeMeasures.put(timeMeasureId, timeMeasure);

		// parse aggregated measure
		String aggFunction = aggregatedMeasure.attribute("aggregationfunction");
		TypeHelper<Long> longHelper = new LongHelper();
		AggregationFunction<Long, TimeMeasureInstance> function = parseAggregationFunction(
				aggFunction, longHelper);
		AggregatedMeasure<TimeMeasure, TimeMeasureInstance, Long> measure = new AggregatedMeasure<TimeMeasure, TimeMeasureInstance, Long>();
		
		// TODO tackle duplicated code in this and parseCountMeasure
		String aggMeasureId = aggregatedMeasure.attribute("id");
		measure.setId(aggMeasureId);
		measure.setBaseMeasure(timeMeasure);
		measure.setAggregationFunction(function);

		ProcessDefinitionImpl definitionImpl = (ProcessDefinitionImpl) definition;
		definitionImpl.addProcessMeasure(measure);
		derivableProcessMeasures.put(aggMeasureId, measure);
		
		List<Element> ppisToRegisterFor = ppisForProcessMeasure.get(aggMeasureId);
		if (ppisToRegisterFor != null) {
			for (Element ppiElement : ppisToRegisterFor) {
				parsePPI(ppiElement, longHelper, measure, definition);
			}
		}
		
	}
	
	private <N extends Number> void parsePPI(Element ppiElement, TypeHelper<N> typeHelper, ProcessMeasure<N> measure, ProcessDefinition definition) {
		String targetAttribute = ppiElement.attribute("target");
		TargetFunction targetFunction = null;
		N targetValue = null;
		
		if (targetAttribute != null) {
			String[] target = ppiElement.attribute("target").trim().split(" ");
			targetFunction = parseTargetFunction(target[0], typeHelper);				
			targetValue = typeHelper.parseType(target[1]);
		}

		String ppiId = ppiElement.attribute("id");
		
		PPI ppi = new PPI();
		ppi.setId(ppiId);
		ppi.setTargetFunction(targetFunction);
		ppi.setTargetValue(targetValue);
		ppi.setProcessMeasure(measure);
		
		((ProcessDefinitionImpl) definition).addPPI(ppi);
	}
	
	private <N extends Number> TargetFunction parseTargetFunction(
			String targetFunctionName, TypeHelper<N> typeHelper) {
		if (targetFunctionName.equals("<")) {
			return new LowerThanFunction<N>(typeHelper);
		} else if (targetFunctionName.equals(">")) {
			return new GreaterThanFunction<N>(typeHelper);
		} else {
			throw new ActivitiException("Unsupported target function type "
					+ targetFunctionName);
		}		
	}

//	private PPI parsePPI(Element ppiElement, TargetFunction targetFunction,
//			Number targetValue,
//			ProcessMeasure<?> measure, ProcessDefinition definition) {
//		
//		String ppiId = ppiElement.attribute("id");
//		
//		PPI ppi = new PPI();
//		ppi.setId(ppiId);
//		ppi.setTargetFunction(targetFunction);
//		ppi.setTargetValue(targetValue);
//		ppi.setProcessMeasure(measure);
//		return ppi;
//	}

	private void parseCountMeasure(Element aggregatedMeasure,
			Element baseMeasure, ProcessDefinition definition) {
		// parse count measure
		String countMeasureId = baseMeasure.attribute("id");
		CountMeasure countMeasure = new CountMeasure(countMeasureId, definition);
		countMeasures.put(countMeasureId, countMeasure);

		// parse aggregated measure
		String aggFunction = aggregatedMeasure.attribute("aggregationfunction");
		TypeHelper<Integer> intHelper = new IntegerHelper();
		AggregationFunction<Integer, CountMeasureInstance> function = parseAggregationFunction(
				aggFunction, intHelper);
		AggregatedMeasure<CountMeasure, CountMeasureInstance, Integer> measure = new AggregatedMeasure<CountMeasure, CountMeasureInstance, Integer>();
		String aggMeasureId = aggregatedMeasure.attribute("id");
		measure.setId(aggMeasureId);
		measure.setBaseMeasure(countMeasure);
		measure.setAggregationFunction(function);

		ProcessDefinitionImpl definitionImpl = (ProcessDefinitionImpl) definition;
		definitionImpl.addProcessMeasure(measure);
		derivableProcessMeasures.put(aggMeasureId, measure);

		List<Element> ppisToRegisterFor = ppisForProcessMeasure.get(aggMeasureId);
		if (ppisToRegisterFor != null) {
			for (Element ppiElement : ppisToRegisterFor) {
				parsePPI(ppiElement, intHelper, measure, definition);
			}
		}
	}

	private void registerPPIElementForProcessMeasure(Element ppiElement, ProcessDefinition definition) {
		String measuredBy = ppiElement.attribute("measuredBy");
		
		List<Element> ppis = ppisForProcessMeasure.get(measuredBy);
		if (ppis == null) {
			ppis = new ArrayList<Element>();
			ppisForProcessMeasure.put(measuredBy, ppis);
		}
		ppis.add(ppiElement);		
	}

	private void parseAppliesToActivity(CountMeasure measure, Element appliesToConnector,
			ProcessDefinition definition) {
		
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
					"Unknow content of countatend tag for applies to connector "
							+ appliesToConnector.attribute("id") + ": "
							+ measurePoint);
		}
		measure.setCondition(condition);
		activity.addObserver(measure);
	}
	
	private void parseAppliesToDataObject(DataMeasure measure, Element appliesToConnector,
			ProcessDefinition definition) {
		String dataObjectId = appliesToConnector.attribute("targetRef");
		String dataObjectName = dataObjects.get(dataObjectId);
		
		if (dataObjectName == null) {
			throw new ActivitiException("There is no such data object: " + dataObjectId);
		}
		
		measure.setDataObjectName(dataObjectName);	
		
		ProcessDefinitionImpl definitionImpl = (ProcessDefinitionImpl) definition;
		definitionImpl.addDataMeasure(measure);
	}

	private void parseAppliesToConnector(Element appliesToConnector,
			ProcessDefinition definition) {
		String sourceMeasureId = appliesToConnector.attribute("sourceRef");
		CountMeasure countMeasure = countMeasures.get(sourceMeasureId);
		if (countMeasure != null) {
			parseAppliesToActivity(countMeasure, appliesToConnector, definition);
		} else {
			DataMeasure dataMeasure = dataMeasures.get(sourceMeasureId);
			if (dataMeasure != null) {
				parseAppliesToDataObject(dataMeasure, appliesToConnector, definition);
			}
		}

		
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

	/**
	 * Returns null, if there is no such an activity with this id.
	 * @param activities
	 * @param id
	 * @return
	 */
	private ActivityImpl findActivityById(List<ActivityImpl> activities,
			String id) {
		for (ActivityImpl activity : activities) {
			if (activity.getId().equals(id)) {
				return activity;
			}
		}
		return null;
	}
}
