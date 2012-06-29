package de.unipotsdam.hpi.thorben.ppi.measure.query;

import java.util.List;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.AbstractQuery;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.CommandExecutor;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.CountMeasureInstance;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.DataMeasureInstance;

public class DataMeasureInstanceQueryImpl extends AbstractQuery<DataMeasureInstanceQuery, DataMeasureInstance> implements DataMeasureInstanceQuery {

	private static final long serialVersionUID = 1L;
	protected String id;
	protected String measureId;
	protected String processInstanceId;
	
	public DataMeasureInstanceQueryImpl(
			CommandExecutor commandExecutorTxRequired) {
		super(commandExecutorTxRequired);
	}

	@Override
	public DataMeasureInstanceQuery measureId(String measureId) {
		if (measureId == null) {
		      throw new ActivitiException("Provided measureId is null");
		    }
		this.measureId = measureId;
		return this;
	}
	
	@Override
	public DataMeasureInstanceQuery processInstanceId(String processInstanceId) {
		if (processInstanceId == null) {
		      throw new ActivitiException("Provided processInstanceId is null");
		    }
		this.processInstanceId = processInstanceId;
		return this;
	}
	
	@Override
	public long executeCount(CommandContext commandContext) {
		checkQueryOk();
	    return commandContext
	      .getBaseMeasureManager()
	      .findDataMeasureInstanceCountByQueryCriteria(this);
	}
	
	@Override
	public List<DataMeasureInstance> executeList(CommandContext commandContext,
			Page page) {
		checkQueryOk();
	    return commandContext
	      .getBaseMeasureManager()
	      .findDataMeasureInstanceByQueryCriteria(this, page);
	}

}
