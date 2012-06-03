package de.unipotsdam.hpi.thorben.ppi.engine;

import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;

import de.unipotsdam.hpi.thorben.ppi.service.PPIService;
import de.unipotsdam.hpi.thorben.ppi.service.PPIServiceImpl;

public class PPIProcessEngine extends ProcessEngineImpl {

	protected PPIService ppiService;
	public PPIProcessEngine(
			ProcessEngineConfigurationImpl processEngineConfiguration) {
		super(processEngineConfiguration);
		PPIServiceImpl service = new PPIServiceImpl();
		service.setCommandExecutor(commandExecutor);
		ppiService = service;
	}
	
	public PPIService getPPIService() {
		return ppiService;
	}
}
