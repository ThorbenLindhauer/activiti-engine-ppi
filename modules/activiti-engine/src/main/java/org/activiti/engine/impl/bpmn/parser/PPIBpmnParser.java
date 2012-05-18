package org.activiti.engine.impl.bpmn.parser;

import org.activiti.engine.impl.el.ExpressionManager;

public class PPIBpmnParser extends BpmnParser {

	public PPIBpmnParser(ExpressionManager expressionManager) {
		super(expressionManager);
	}

	public BpmnParse createParse() {
		return new PPIBpmnParse(this);
	}
}
