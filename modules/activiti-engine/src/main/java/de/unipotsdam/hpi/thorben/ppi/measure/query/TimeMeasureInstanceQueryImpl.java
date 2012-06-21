package de.unipotsdam.hpi.thorben.ppi.measure.query;

import java.util.List;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.AbstractQuery;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.CommandExecutor;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.TimeMeasureInstance;

public class TimeMeasureInstanceQueryImpl extends AbstractQuery<TimeMeasureInstanceQuery, TimeMeasureInstance> implements TimeMeasureInstanceQuery {

	private static final long serialVersionUID = -6573102800086133707L;
	protected String id;
	protected String measureId;
	protected String processInstanceId;
	
	public TimeMeasureInstanceQueryImpl(CommandExecutor commandExecutor) {
		super(commandExecutor);
	}
	
	@Override
	public TimeMeasureInstanceQuery measureId(String measureId) {
		if (measureId == null) {
		      throw new ActivitiException("Provided measureId is null");
		    }
		this.measureId = measureId;
		return this;
	}

	@Override
	public TimeMeasureInstanceQuery processInstanceId(String processInstanceId) {
		if (processInstanceId == null) {
		      throw new ActivitiException("Provided processInstanceId is null");
		    }
		this.processInstanceId = processInstanceId;
		return this;
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

	@Override
	public long executeCount(CommandContext commandContext) {
		checkQueryOk();
	    return commandContext
	      .getBaseMeasureManager()
	      .findTimeMeasureInstanceCountByQueryCriteria(this);
	}

	@Override
	public List<TimeMeasureInstance> executeList(CommandContext commandContext,
			Page page) {
		checkQueryOk();
	    return commandContext
	      .getBaseMeasureManager()
	      .findTimeMeasureInstanceByQueryCriteria(this, page);
	}
}
