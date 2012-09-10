/* Copyright 2012 Thorben Lindhauer
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.unipotsdam.hpi.thorben.ppi.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.bpmn.deployer.BpmnDeployer;
import org.activiti.engine.impl.bpmn.parser.BpmnParser;
import org.activiti.engine.impl.bpmn.parser.PPIBpmnParser;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.impl.persistence.deploy.Deployer;

public class PPIStandaloneProcessEngineConfiguration extends
		StandaloneProcessEngineConfiguration {

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
	
	@Override
	public ProcessEngine buildProcessEngine() {
		init();
		return new PPIProcessEngine(this);
	}
}
