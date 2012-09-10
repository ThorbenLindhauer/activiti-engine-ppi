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

package de.unipotsdam.hpi.thorben.ppi.measure.query;

import java.util.List;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.AbstractQuery;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.CommandExecutor;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.CountMeasureInstance;

public class CountMeasureInstanceQueryImpl extends AbstractQuery<CountMeasureInstanceQuery, CountMeasureInstance> implements CountMeasureInstanceQuery{

	private static final long serialVersionUID = 1L;
	protected String id;
	protected String measureId;
	protected String processInstanceId;
	protected String processDefinitionId;
	
	public CountMeasureInstanceQueryImpl(CommandExecutor commandExecutor) {
		super(commandExecutor);
	}
	
	public CountMeasureInstanceQuery measureId(String measureId) {
		if (measureId == null) {
		      throw new ActivitiException("Provided measureId is null");
		    }
		this.measureId = measureId;
		return this;
	}

	public CountMeasureInstanceQuery processInstanceId(String processInstanceId) {
		if (processInstanceId == null) {
		      throw new ActivitiException("Provided processInstanceId is null");
		    }
		this.processInstanceId = processInstanceId;
		return this;
	}

	public long executeCount(CommandContext commandContext) {
		checkQueryOk();
	    return commandContext
	      .getBaseMeasureManager()
	      .findCountMeasureInstanceCountByQueryCriteria(this);
	}

	@Override
	public List<CountMeasureInstance> executeList(CommandContext commandContext,
			Page page) {
		checkQueryOk();
	    return commandContext
	      .getBaseMeasureManager()
	      .findCountMeasureInstanceByQueryCriteria(this, page);
	}

	public String getMeasureId() {
		return measureId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public String getId() {
		return id;
	}

	public CountMeasureInstanceQuery processDefinitionId(
			String processDefinitionId) {
		if (processDefinitionId == null) {
		      throw new ActivitiException("Provided processDefinitionId is null");
		    }
		this.processDefinitionId = processDefinitionId;
		return this;
	}
	
	

}
