package de.unipotsdam.hpi.thorben.ppi.parse;

import java.util.List;

import org.activiti.engine.impl.bpmn.parser.BpmnParseListener;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ScopeImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.impl.util.xml.Element;
import org.activiti.engine.impl.variable.VariableDeclaration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.unipotsdam.hpi.thorben.ppi.measure.BaseMeasure;

public class PPIParseListener implements BpmnParseListener {
	
	private Log log = LogFactory.getLog(PPIParseListener.class);


	public void parseServiceTask(Element serviceTaskElement, ScopeImpl scope,
			ActivityImpl activity) {
		BaseMeasure measure = new BaseMeasure();
		activity.addBaseMeasure(measure);
		log.info("service task was parsed.");

	}

	public void parseProcess(Element processElement,
			ProcessDefinitionEntity processDefinition) {
		// TODO Auto-generated method stub

	}

	public void parseStartEvent(Element startEventElement, ScopeImpl scope,
			ActivityImpl startEventActivity) {
		// TODO Auto-generated method stub

	}

	public void parseExclusiveGateway(Element exclusiveGwElement,
			ScopeImpl scope, ActivityImpl activity) {
		// TODO Auto-generated method stub

	}

	public void parseInclusiveGateway(Element inclusiveGwElement,
			ScopeImpl scope, ActivityImpl activity) {
		// TODO Auto-generated method stub

	}

	public void parseParallelGateway(Element parallelGwElement,
			ScopeImpl scope, ActivityImpl activity) {
		// TODO Auto-generated method stub

	}

	public void parseScriptTask(Element scriptTaskElement, ScopeImpl scope,
			ActivityImpl activity) {
		// TODO Auto-generated method stub

	}

	public void parseBusinessRuleTask(Element businessRuleTaskElement,
			ScopeImpl scope, ActivityImpl activity) {
		// TODO Auto-generated method stub

	}

	public void parseTask(Element taskElement, ScopeImpl scope,
			ActivityImpl activity) {
		// TODO Auto-generated method stub

	}

	public void parseManualTask(Element manualTaskElement, ScopeImpl scope,
			ActivityImpl activity) {
		// TODO Auto-generated method stub

	}

	public void parseUserTask(Element userTaskElement, ScopeImpl scope,
			ActivityImpl activity) {
		// TODO Auto-generated method stub

	}

	public void parseEndEvent(Element endEventElement, ScopeImpl scope,
			ActivityImpl activity) {
		// TODO Auto-generated method stub

	}

	public void parseBoundaryTimerEventDefinition(Element timerEventDefinition,
			boolean interrupting, ActivityImpl timerActivity) {
		// TODO Auto-generated method stub

	}

	public void parseBoundaryErrorEventDefinition(Element errorEventDefinition,
			boolean interrupting, ActivityImpl activity,
			ActivityImpl nestedErrorEventActivity) {
		// TODO Auto-generated method stub

	}

	public void parseSubProcess(Element subProcessElement, ScopeImpl scope,
			ActivityImpl activity) {
		// TODO Auto-generated method stub

	}

	public void parseCallActivity(Element callActivityElement, ScopeImpl scope,
			ActivityImpl activity) {
		// TODO Auto-generated method stub

	}

	public void parseProperty(Element propertyElement,
			VariableDeclaration variableDeclaration, ActivityImpl activity) {
		// TODO Auto-generated method stub

	}

	public void parseSequenceFlow(Element sequenceFlowElement,
			ScopeImpl scopeElement, TransitionImpl transition) {
		// TODO Auto-generated method stub

	}

	public void parseSendTask(Element sendTaskElement, ScopeImpl scope,
			ActivityImpl activity) {
		// TODO Auto-generated method stub

	}

	public void parseMultiInstanceLoopCharacteristics(Element activityElement,
			Element multiInstanceLoopCharacteristicsElement,
			ActivityImpl activity) {
		// TODO Auto-generated method stub

	}

	public void parseIntermediateTimerEventDefinition(
			Element timerEventDefinition, ActivityImpl timerActivity) {
		// TODO Auto-generated method stub

	}

	public void parseRootElement(Element rootElement,
			List<ProcessDefinitionEntity> processDefinitions) {
		// TODO Auto-generated method stub

	}

	public void parseReceiveTask(Element receiveTaskElement, ScopeImpl scope,
			ActivityImpl activity) {
		// TODO Auto-generated method stub

	}

	public void parseIntermediateSignalCatchEventDefinition(
			Element signalEventDefinition, ActivityImpl signalActivity) {
		// TODO Auto-generated method stub

	}

	public void parseBoundarySignalEventDefinition(
			Element signalEventDefinition, boolean interrupting,
			ActivityImpl signalActivity) {
		// TODO Auto-generated method stub

	}

	public void parseEventBasedGateway(Element eventBasedGwElement,
			ScopeImpl scope, ActivityImpl activity) {
		// TODO Auto-generated method stub

	}

	public void parseTransaction(Element transactionElement, ScopeImpl scope,
			ActivityImpl activity) {
		// TODO Auto-generated method stub

	}

	public void parseCompensateEventDefinition(
			Element compensateEventDefinition, ActivityImpl compensationActivity) {
		// TODO Auto-generated method stub

	}

	public void parseIntermediateThrowEvent(Element intermediateEventElement,
			ScopeImpl scope, ActivityImpl activity) {
		// TODO Auto-generated method stub

	}

	public void parseIntermediateCatchEvent(Element intermediateEventElement,
			ScopeImpl scope, ActivityImpl activity) {
		// TODO Auto-generated method stub

	}

	public void parseBoundaryEvent(Element boundaryEventElement,
			ScopeImpl scopeElement, ActivityImpl nestedActivity) {
		// TODO Auto-generated method stub

	}

}
