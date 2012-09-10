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

package de.unipotsdam.hpi.thorben.ppi.measure.process;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.el.ActivitiElContext;
import org.activiti.engine.impl.javax.el.ExpressionFactory;
import org.activiti.engine.impl.javax.el.ValueExpression;
import org.activiti.engine.impl.juel.ExpressionFactoryImpl;

public class DerivedProcessMeasure implements ProcessMeasure<Double> {

	private String id;
	private String juelFunction;
	private Map<String, ProcessMeasure<?>> variableContext = new HashMap<String, ProcessMeasure<?>>();
	
	public DerivedProcessMeasure(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public Double calculate() {
		ExpressionFactory factory = new ExpressionFactoryImpl();
		DerivedMeasureValueResolver resolver = new DerivedMeasureValueResolver(variableContext);
		ActivitiElContext context = new ActivitiElContext(resolver);
		ValueExpression expression = factory.createValueExpression(context, juelFunction, Double.class);

		return (Double) expression.getValue(context);
	}

	public void setFunction(String juelFunction) {
		this.juelFunction = juelFunction;
		
	}

	public void addDerivableMeasure(String variableName, ProcessMeasure<?> measure) {
		variableContext.put(variableName, measure);
		
	}

}
