package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.db.PersistentObject;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;

public class PPIProcessInstanceEntity implements PersistentObject {

	private String id;
	private String processDefinitionId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	} 
	
	public static PPIProcessInstanceEntity fromExecution(ExecutionEntity execution) {
		PPIProcessInstanceEntity entity = new PPIProcessInstanceEntity();
		entity.id = execution.getProcessInstanceId();
		entity.processDefinitionId = execution.getProcessDefinitionId();
		return entity;
	}
	
	@Override
	public Object getPersistentState() {
		Map<String, Object> persistentState = new HashMap<String, Object>();
	    persistentState.put("id", id);
	    persistentState.put("processDefinitionId", processDefinitionId);
	    return persistentState;
	}
}
