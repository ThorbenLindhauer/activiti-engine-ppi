package de.unipotsdam.hpi.thorben.ppi.measure.process;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.el.ActivitiElContext;
import org.activiti.engine.impl.javax.el.ExpressionFactory;
import org.activiti.engine.impl.javax.el.ValueExpression;
import org.activiti.engine.impl.juel.ExpressionFactoryImpl;

public class DerivedProcessMeasure implements ProcessMeasure<Number> {

	private String id;
	private String juelFunction;
	private Map<String, ProcessMeasure<?>> variableContext = new HashMap<String, ProcessMeasure<?>>();
	
	public DerivedProcessMeasure(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public Number calculate() {
		ExpressionFactory factory = new ExpressionFactoryImpl();
		DerivedMeasureValueResolver resolver = new DerivedMeasureValueResolver(variableContext);
		ActivitiElContext context = new ActivitiElContext(resolver);
		ValueExpression expression = factory.createValueExpression(context, juelFunction, Number.class);

		return (Number) expression.getValue(context);
	}

	public void setFunction(String juelFunction) {
		this.juelFunction = juelFunction;
		
	}

	public void addDerivableMeasure(String variableName, ProcessMeasure<?> measure) {
		variableContext.put(variableName, measure);
		
	}

}
