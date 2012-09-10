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

import java.beans.FeatureDescriptor;
import java.util.Iterator;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.javax.el.ELContext;
import org.activiti.engine.impl.javax.el.ELResolver;

public class DerivedMeasureValueResolver extends ELResolver {

	public static final String EXECUTION_KEY = "execution";
	public static final String TASK_KEY = "task";
	public static final String LOGGED_IN_USER_KEY = "authenticatedUserId";

	protected Map<String, ProcessMeasure<?>> measures;

	public DerivedMeasureValueResolver(Map<String, ProcessMeasure<?>> measures) {
		this.measures = measures;
	}

	public Object getValue(ELContext context, Object base, Object property) {

		if (base == null) {
			String variable = (String) property; // according to javadoc, can
													// only be a String
			ProcessMeasure<?> measure = measures.get(variable);
			if (measure == null) {
				throw new ActivitiException("Measure named " + variable + " was not defined.");
			}
			
			Number result = measure.calculate();
			context.setPropertyResolved(true);
			return result;
		}

		return null;
	}

	public boolean isReadOnly(ELContext context, Object base, Object property) {
		if (base == null) {
			String variable = (String) property;
			return !measures.containsKey(variable);
		}
		return true;
	}

	public void setValue(ELContext context, Object base, Object property,
			Object value) {
		if (base == null) {
			String variable = (String) property;
			if (measures.containsKey(property)) {
				ProcessMeasure<?> measure = (ProcessMeasure<?>) value;
				measures.put(variable, measure);
			}
		}
	}

	public Class<?> getCommonPropertyType(ELContext arg0, Object arg1) {
		return Object.class;
	}

	public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext arg0,
			Object arg1) {
		return null;
	}

	public Class<?> getType(ELContext arg0, Object arg1, Object arg2) {
		return Object.class;
	}

}
