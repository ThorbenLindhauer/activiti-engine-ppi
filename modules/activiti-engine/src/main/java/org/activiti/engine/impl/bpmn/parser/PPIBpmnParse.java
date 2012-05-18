package org.activiti.engine.impl.bpmn.parser;

import java.util.List;

import org.activiti.engine.impl.util.xml.Element;
import org.activiti.engine.repository.ProcessDefinition;

public class PPIBpmnParse extends BpmnParse {

	PPIBpmnParse(BpmnParser parser) {
		super(parser);
	}
	
	@Override
	protected void parseProcessDefinitionCustomExtensions(Element scopeElement, ProcessDefinition definition) {
		super.parseProcessDefinitionCustomExtensions(scopeElement, definition);
	    parsePPIElements(scopeElement, definition);
	  }
	  
	  protected void parsePPIElements(Element scopeElement, ProcessDefinition definition) {
		  Element extentionsElement = scopeElement.element("extensionElements");
		  List<Element> ppiSetElements = extentionsElement.elementsNS(BpmnParser.PPI_BPMN_EXTENSIONS_NS, "ppiset");
		  for (Element ppiSetElement : ppiSetElements) {
			  parsePPISet(ppiSetElement, definition);
		  }
	  }

	  protected void parsePPISet(Element ppiSetElement, ProcessDefinition definition) {
		  // TODO: parse all necessary elements, as are: aggregatedMeasure, TimeConnector, ppi (in the first example)
		  List<Element> aggMeasureElements = ppiSetElement.elementsNS(BpmnParser.PPI_BPMN_EXTENSIONS_NS, "aggregatedMeasure");
		  for (Element aggMeasure : aggMeasureElements) {
			  parseAggMeasure(aggMeasure, definition);
		  }
		  
		  List<Element> timeConnectorElements = ppiSetElement.elementsNS(BpmnParser.PPI_BPMN_EXTENSIONS_NS, "TimeConnector");
		  for (Element timeConnector : timeConnectorElements) {
			  parseTimeConnector(timeConnector, definition);
		  }
		  
		  List<Element> ppiElements = ppiSetElement.elementsNS(BpmnParser.PPI_BPMN_EXTENSIONS_NS, "ppi");
		  for (Element ppi : ppiElements) {
			  parsePPI(ppi, definition);
		  }
	  }

	private void parsePPI(Element ppi, ProcessDefinition definition) {
		// TODO Auto-generated method stub
		
	}

	private void parseTimeConnector(Element timeConnector,
			ProcessDefinition definition) {
		// TODO Auto-generated method stub
		
	}

	private void parseAggMeasure(Element aggMeasure,
			ProcessDefinition definition) {
		// TODO Auto-generated method stub
		
	}

}
