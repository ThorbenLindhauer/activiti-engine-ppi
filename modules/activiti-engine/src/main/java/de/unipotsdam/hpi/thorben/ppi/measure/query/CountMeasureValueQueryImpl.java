package de.unipotsdam.hpi.thorben.ppi.measure.query;

import java.util.List;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.AbstractQuery;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.CommandExecutor;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.CountMeasureValue;

public class CountMeasureValueQueryImpl extends AbstractQuery<CountMeasureValueQuery, CountMeasureValue> implements CountMeasureValueQuery{

	private static final long serialVersionUID = 1L;
	protected String id;
	protected String measureId;
	protected String processInstanceId;
	
	public CountMeasureValueQueryImpl(CommandExecutor commandExecutor) {
		super(commandExecutor);
	}
	
	@Override
	public CountMeasureValueQuery measureId(String measureId) {
		if (measureId == null) {
		      throw new ActivitiException("Provided measureId is null");
		    }
		this.measureId = measureId;
		return this;
	}

	@Override
	public CountMeasureValueQuery processInstanceId(String processInstanceId) {
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
	      .findCountMeasureValueCountByQueryCriteria(this);
	}

	@Override
	public List<CountMeasureValue> executeList(CommandContext commandContext,
			Page page) {
		checkQueryOk();
	    return commandContext
	      .getBaseMeasureManager()
	      .findCountMeasureValueByQueryCriteria(this, page);
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
	
	

}
