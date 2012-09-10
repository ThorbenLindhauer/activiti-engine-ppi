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

import java.util.List;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.BaseMeasure;
import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.BaseMeasureInstance;

public class AggregatedMeasure<M extends BaseMeasure<V>, V extends BaseMeasureInstance, N extends Number> implements ProcessMeasure<N> {

	private String id;
	private M baseMeasure;
	private AggregationFunction<N, V> aggregationFunction;
	
	public N calculate() {
		List<V> values = baseMeasure.getAllValues();
		return aggregationFunction.calculate(values);
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public M getBaseMeasure() {
		return baseMeasure;
	}

	public void setBaseMeasure(M baseMeasure) {
		this.baseMeasure = baseMeasure;
	}

	public void setAggregationFunction(
			AggregationFunction<N, V> function) {
		this.aggregationFunction = function;
		
	}
}
