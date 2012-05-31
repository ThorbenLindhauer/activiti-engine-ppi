package de.unipotsdam.hpi.thorben.ppi.measure.query;

import java.util.List;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.AbstractQuery;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.interceptor.CommandContext;

import de.unipotsdam.hpi.thorben.ppi.measure.entity.TimeMeasureValue;

public class TimeMeasureValueQueryImpl extends AbstractQuery<TimeMeasureValueQuery, TimeMeasureValue> implements TimeMeasureValueQuery {

	private static final long serialVersionUID = -6573102800086133707L;
	protected String id;
	protected String measureId;
	protected String processInstanceId;
	
	
	@Override
	public TimeMeasureValueQuery measureId(String measureId) {
		if (measureId == null) {
		      throw new ActivitiException("Provided measureId is null");
		    }
		this.measureId = measureId;
		return this;
	}

	@Override
	public TimeMeasureValueQuery processInstanceId(String processInstanceId) {
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
	      .findTimeMeasureValueCountByQueryCriteria(this);
	}

	@Override
	public List<TimeMeasureValue> executeList(CommandContext commandContext,
			Page page) {
		checkQueryOk();
	    return commandContext
	      .getBaseMeasureManager()
	      .findTimeMeasureValueByQueryCriteria(this, page);
	}
}
