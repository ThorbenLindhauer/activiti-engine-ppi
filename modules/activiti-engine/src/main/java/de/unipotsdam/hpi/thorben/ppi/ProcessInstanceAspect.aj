package de.unipotsdam.hpi.thorben.ppi;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.PPIProcessInstanceEntity;

public aspect ProcessInstanceAspect {

	pointcut newInstance(ProcessDefinitionEntity processDefinition) : target(processDefinition) && call(ExecutionEntity ProcessDefinitionEntity.createProcessInstance(String, ActivityImpl));
	after(ProcessDefinitionEntity processDefinition) returning (ExecutionEntity processInstance): newInstance(processDefinition) {
		PPIProcessInstanceEntity instanceEntity = PPIProcessInstanceEntity.fromExecution(processInstance);
		CommandContext commandContext = Context.getCommandContext();
		commandContext
        .getDbSqlSession()
        .insert(instanceEntity);
	}
}
