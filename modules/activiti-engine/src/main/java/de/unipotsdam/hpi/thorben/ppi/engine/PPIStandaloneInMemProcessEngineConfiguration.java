package de.unipotsdam.hpi.thorben.ppi.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.activiti.engine.impl.bpmn.deployer.BpmnDeployer;
import org.activiti.engine.impl.bpmn.parser.BpmnParser;
import org.activiti.engine.impl.bpmn.parser.PPIBpmnParser;
import org.activiti.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.activiti.engine.impl.persistence.deploy.Deployer;

public class PPIStandaloneInMemProcessEngineConfiguration extends StandaloneInMemProcessEngineConfiguration {
	
	@Override
	protected Collection< ? extends Deployer> getDefaultDeployers() {
	    List<Deployer> defaultDeployers = new ArrayList<Deployer>();

	    BpmnDeployer bpmnDeployer = new BpmnDeployer();
	    bpmnDeployer.setExpressionManager(expressionManager);
	    bpmnDeployer.setIdGenerator(idGenerator);
	    BpmnParser bpmnParser = new PPIBpmnParser(expressionManager);
	    
	    if(preParseListeners != null) {
	      bpmnParser.getParseListeners().addAll(preParseListeners);
	    }
	    bpmnParser.getParseListeners().addAll(getDefaultBPMNParseListeners());
	    if(postParseListeners != null) {
	      bpmnParser.getParseListeners().addAll(postParseListeners);
	    }
	    
	    bpmnDeployer.setBpmnParser(bpmnParser);
	    
	    defaultDeployers.add(bpmnDeployer);
	    return defaultDeployers;
	  }
}
